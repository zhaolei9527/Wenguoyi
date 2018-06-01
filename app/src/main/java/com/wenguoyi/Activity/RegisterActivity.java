package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.App;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.WXBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.CodeUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_account;
    private ImageView image;
    private EditText et_phonecode;
    private EditText et_password;
    private EditText et_passwordagain;
    private String code;
    private Button btn_getSMScode;
    private Button btn_register;
    private CodeUtils codeUtils;
    private Bitmap bitmap;
    private Timer timer;
    private TimerTask task;
    private int time = 60;
    private Context context;
    private String account;
    private String imgCode;
    private String phonecode;
    private String password;
    private String passwordagain;
    private String tuijian;
    private String openid = "";
    private String type = "";
    private Dialog dialog;
    private RelativeLayout rl4;
    private LinearLayout rl5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;
        initView();
        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("login/regsend");
        App.getQueues().cancelAll("login/xieyi");
        App.getQueues().cancelAll("login/sheng");
        App.getQueues().cancelAll("login/regist");
        codeUtils = null;
        code = null;
        bitmap = null;
        task = null;
        if (timer != null) {
            timer = null;
        }
        account = null;
        imgCode = null;
        phonecode = null;
        password = null;
        passwordagain = null;
        tuijian = null;
        System.gc();
    }

    private void initData() {
    }

    private void initView() {
        rl4 = (RelativeLayout) findViewById(R.id.rl4);
        rl5 = (LinearLayout) findViewById(R.id.rl5);
        et_account = (EditText) findViewById(R.id.et_account);
        image = (ImageView) findViewById(R.id.image);
        et_phonecode = (EditText) findViewById(R.id.et_phonecode);
        et_password = (EditText) findViewById(R.id.et_password);
        et_passwordagain = (EditText) findViewById(R.id.et_passwordagain);
        codeUtils = CodeUtils.getInstance();
        bitmap = codeUtils.createBitmap();
        code = codeUtils.getCode();
        image.setImageBitmap(bitmap);
        btn_getSMScode = (Button) findViewById(R.id.btn_getSMScode);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_getSMScode.setOnClickListener(this);
        dialog = Utils.showLoadingDialog(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_weixin:
                dialog.show();
                Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weChat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        String s = arg0.getDb().exportData();
                        Log.e("LoginActivity", s);
                        WXBean wxBean = new Gson().fromJson(s, WXBean.class);
                        openid = wxBean.getUnionid();
                        type = "2";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                rl4.setVisibility(View.GONE);
                                rl5.setVisibility(View.GONE);
                                dialog.dismiss();
                                btn_register.setText("微信授权注册");
                            }
                        });
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                EasyToast.showShort(context, "授权取消");
                            }
                        });
                    }
                });
                weChat.showUser(null);//授权并获取用户信息
                break;
            case R.id.btn_register:
                submit();
                break;
            case R.id.btn_getSMScode:
                account = et_account.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Utils.isCellphone(account)) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!this.code.equals(imgCode)) {
                    Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (time == 60) {
                    getcaptcha(et_account.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    private void getcaptcha(String phone) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        btn_getSMScode.setText("" + time);
                        if (time < 0) {
                            timer.cancel();
                            btn_getSMScode.setText("获取验证码");
                            btn_getSMScode.setEnabled(true);
                            time = 60;
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);
        //// TODO: 2017/5/18  发送验证码
        if (Utils.isConnected(context)) {
            getUserPlace(phone);
        } else {
            Toast.makeText(context, getString(R.string.Networkexception), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 发送验证码
     */
    private void getUserPlace(String phone) {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("key", UrlUtils.KEY);
        params.put("tel", phone);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/regsend", "login/regsend", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    Toast.makeText(RegisterActivity.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {

                    } else {
                        time = 0;
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    time = 0;
                    Toast.makeText(RegisterActivity.this, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                time = 0;
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 注册提交
     */
    private void submit() {
        // validate
        account = et_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Utils.isCellphone(account)) {
            Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!this.code.equals(imgCode)) {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
            return;
        }

        phonecode = et_phonecode.getText().toString().trim();
        if (TextUtils.isEmpty(phonecode)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        passwordagain = et_passwordagain.getText().toString().trim();
        if (TextUtils.isEmpty(passwordagain)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(passwordagain)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        getRegister(account, phonecode, password, tuijian, openid, type);

    }

    /**
     * 注册id
     */
    private void getRegister(String phone, String code, String password, String tel2, String openid, String type) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("key", UrlUtils.KEY);
        params.put("tel", phone);
        params.put("ecode", code);
        params.put("password", password);
        params.put("cfmpwd", password);
        params.put("openid", openid);
        params.put("type", type);
        if (!TextUtils.isEmpty(tel2)) {
            params.put("tel2", tel2);
        }
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/regist", "login/regist", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                time = 0;
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                time = 0;
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
