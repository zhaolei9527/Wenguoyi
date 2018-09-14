package com.wenguoyi.Activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class ForgetActivity extends BaseActivity implements View.OnClickListener {


    private ImageView img;
    private ImageView img_user;
    private EditText et_account;
    private ImageView imageView;
    private ImageView img_yanzheng;
    private EditText et_phonecode;
    private Button btn_getSMScode;
    private ImageView img_lock;
    private EditText et_password;
    private ImageView img_lock2;
    private EditText et_passwordagain;
    private Button btn_ForGet;
    private TextView tv_login;
    private Timer timer;
    private TimerTask task;
    private int time = 100;
    private String account;
    private String phonecode;
    private String password;
    private String passwordagain;

    @Override
    protected void ready() {
        super.ready();
           /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initview() {
        initView();
    }

    @Override
    protected void initListener() {
        btn_getSMScode.setOnClickListener(this);
        btn_ForGet.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        img_user = (ImageView) findViewById(R.id.img_user);
        et_account = (EditText) findViewById(R.id.et_account);
        imageView = (ImageView) findViewById(R.id.imageView);
        img_yanzheng = (ImageView) findViewById(R.id.img_yanzheng);
        et_phonecode = (EditText) findViewById(R.id.et_phonecode);
        btn_getSMScode = (Button) findViewById(R.id.btn_getSMScode);
        img_lock = (ImageView) findViewById(R.id.img_lock);
        et_password = (EditText) findViewById(R.id.et_password);
        img_lock2 = (ImageView) findViewById(R.id.img_lock2);
        et_passwordagain = (EditText) findViewById(R.id.et_passwordagain);
        btn_ForGet = (Button) findViewById(R.id.btn_ForGet);
        tv_login = (TextView) findViewById(R.id.tv_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                if (time == 100) {
                    getcaptcha(et_account.getText().toString());
                }
                break;
            case R.id.btn_ForGet:
                submit();
                break;
            case R.id.tv_login:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 校验时间
     */
    private void getcaptcha(String phone) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        btn_getSMScode.setText(String.valueOf(time));
                        if (time < 0) {
                            if (timer != null) {
                                timer.cancel();
                            }
                            btn_getSMScode.setText("重获验证码");
                            btn_getSMScode.setEnabled(true);
                            time = 100;
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
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/pwdcodesend", "login/pwdcodesend", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                String decode = result;
                Log.e("RegisterActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    Toast.makeText(context, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {

                    } else {
                        time = 0;
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    time = 0;
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    /**
     * 找回密码数据
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

        phonecode = et_phonecode.getText().toString().trim();
        if (TextUtils.isEmpty(phonecode)) {
            Toast.makeText(this, "短信验证码", Toast.LENGTH_SHORT).show();
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

        // TODO validate success, do something
        getFindPWD(account, phonecode, password);
    }

    /**
     * 找回密码
     */
    private void getFindPWD(String phone, String code, String password) {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("key", UrlUtils.KEY);
        params.put("tel", phone);
        params.put("phone_code", code);
        params.put("password", Utils.md5(password));
        params.put("cfmpwd", Utils.md5(password));
        Log.e("ForgetActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "login/password", "login/password", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                time = 0;
                String decode = result;
                Log.e("ForgetActivity", decode);
                try {
                    CodeBean codeBean = new Gson().fromJson(decode, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        Toast.makeText(context, "找回成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(context, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    decode = null;
                    codeBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                time = 0;
            }
        });
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
        App.getQueues().cancelAll("login/pwdcodesend");
        App.getQueues().cancelAll("login/password");
        task = null;
        if (timer != null) {
            timer = null;
        }
        account = null;
        phonecode = null;
        password = null;
        passwordagain = null;
        System.gc();
    }
}
