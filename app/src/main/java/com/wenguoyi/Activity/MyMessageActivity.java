package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.UserDoInfoBean;
import com.wenguoyi.Bean.UserResetinforBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Utils.Validator;
import com.wenguoyi.View.NeutralDialogFragment;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPickUtils;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/26
 * 功能描述：
 */
public class MyMessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    TextView etPhone;
    @BindView(R.id.et_shenfenzheng)
    EditText etShenfenzheng;
    @BindView(R.id.ll_zhengmian)
    LinearLayout llZhengmian;
    @BindView(R.id.ll_fanmian)
    LinearLayout llFanmian;
    @BindView(R.id.ll_shouchi)
    LinearLayout llShouchi;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.SimpleDraweeView1)
    SimpleDraweeView SimpleDraweeView1;
    @BindView(R.id.SimpleDraweeView2)
    SimpleDraweeView SimpleDraweeView2;
    @BindView(R.id.SimpleDraweeView3)
    SimpleDraweeView SimpleDraweeView3;

    private String pic = "";
    private String pic2 = "";
    private String pic3 = "";
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_mymessage_layout;
    }

    @Override
    protected void initview() {
        etPhone.setText("" + SpUtil.get(context, "tel", ""));
    }

    @Override
    protected void initListener() {
        llZhengmian.setOnClickListener(this);
        llFanmian.setOnClickListener(this);
        llShouchi.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            userResetinfor();
        } else {
            EasyToast.showShort(context, R.string.Networkexception);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 个人资料获取
     */
    private void userResetinfor() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("MyMessageActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/resetinfor", "user/resetinfor", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyMessageActivity", result);
                try {
                    dialog.dismiss();
                    UserResetinforBean userResetinforBean = new Gson().fromJson(result, UserResetinforBean.class);
                    if (1 == userResetinforBean.getStatus()) {
                        if ("0".equals(userResetinforBean.getMsg().getIs_rz())) {


                        } else if ("1".equals(userResetinforBean.getMsg().getIs_rz())) {
                            btnSubmit.setVisibility(View.GONE);
                            llZhengmian.setClickable(false);
                            llFanmian.setClickable(false);
                            llShouchi.setClickable(false);

                            etName.setFocusable(false);
                            etShenfenzheng.setFocusable(false);
                            etName.setText(userResetinforBean.getMsg().getName());
                            etShenfenzheng.setText(userResetinforBean.getMsg().getCard_no());
                            SimpleDraweeView1.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic());
                            SimpleDraweeView2.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic2());
                            SimpleDraweeView3.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic3());


                        } else if ("-1".equals(userResetinforBean.getMsg().getIs_rz())) {
                            btnSubmit.setText("审核中..");
                            btnSubmit.setClickable(false);
                            llZhengmian.setClickable(false);
                            llFanmian.setClickable(false);
                            llShouchi.setClickable(false);

                            etName.setFocusable(false);
                            etShenfenzheng.setFocusable(false);
                            etName.setText(userResetinforBean.getMsg().getName());
                            etShenfenzheng.setText(userResetinforBean.getMsg().getCard_no());
                            SimpleDraweeView1.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic());
                            SimpleDraweeView2.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic2());
                            SimpleDraweeView3.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic3());

                        } else if ("-2".equals(userResetinforBean.getMsg().getIs_rz())) {
                            btnSubmit.setText("重新提交");
                            NeutralDialogFragment neutralDialogFragment = new NeutralDialogFragment();
                            neutralDialogFragment.show("审核失败", userResetinforBean.getMsg().getCause(), "我已知晓~", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }, getSupportFragmentManager());

                            etName.setFocusable(false);
                            etShenfenzheng.setFocusable(false);
                            etName.setText(userResetinforBean.getMsg().getName());
                            etShenfenzheng.setText(userResetinforBean.getMsg().getCard_no());
                            SimpleDraweeView1.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic());
                            SimpleDraweeView2.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic2());
                            SimpleDraweeView3.setImageURI(UrlUtils.URL + userResetinforBean.getMsg().getPic3());

                        }
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 资料保存
     */
    private void userDoinfo(List<String> imgnames, List<File> imgs) {
        final HashMap<String, String> params = new HashMap<>(6);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("name", etName.getText().toString().trim());
        params.put("card_no", etShenfenzheng.getText().toString().trim());
        Utils.uploadMultipart(context, UrlUtils.BASE_URL + "user/doinfo", imgnames, imgs, params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("SubmitReturnPriceActivi", result);
                try {
                    UserDoInfoBean userDoInfoBean = new Gson().fromJson(result, UserDoInfoBean.class);
                    if (1 == userDoInfoBean.getStatus()) {
                        EasyToast.showShort(context, userDoInfoBean.getMsg());
                        btnSubmit.setText("审核中..");
                        btnSubmit.setClickable(false);
                        llZhengmian.setClickable(false);
                        llFanmian.setClickable(false);
                        llShouchi.setClickable(false);
                    } else {
                        EasyToast.showShort(context, userDoInfoBean.getMsg());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    EasyToast.showShort(context, getString(R.string.Abnormalserver));
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                EasyToast.showShort(context, getString(R.string.Abnormalserver));
                error.printStackTrace();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zhengmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 1);
                break;
            case R.id.ll_fanmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 2);
                break;
            case R.id.ll_shouchi:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity.this, 3);
                break;
            case R.id.btn_submit:

                String Name = etName.getText().toString().trim();

                String Phone = etPhone.getText().toString().trim();

                String Shenfenzheng = etShenfenzheng.getText().toString().trim();


                if (TextUtils.isEmpty(Name)) {
                    EasyToast.showShort(context, "请输入姓名");
                    return;
                }

                if (!Validator.isChinese(Name)) {
                    EasyToast.showShort(context, "请输入有效真实姓名");
                    return;
                }


                if (TextUtils.isEmpty(Phone)) {
                    EasyToast.showShort(context, "请输入电话");
                    return;
                }

                if (!Utils.isCellphone(Phone)) {
                    EasyToast.showShort(context, "请输入正确的电话号码");
                    return;
                }

                if (TextUtils.isEmpty(Shenfenzheng)) {
                    EasyToast.showShort(context, "请输入身份证号码");
                    return;
                }

                if (!Validator.isIDCard(Shenfenzheng)) {
                    EasyToast.showShort(context, "请输入正确的身份证号码");
                    return;
                }

                List<File> imgfiles = new ArrayList<>();
                List<String> imgnames = new ArrayList<>();


                if (!"重新提交".equals(btnSubmit.getText().toString())) {
                    if (TextUtils.isEmpty(pic)) {
                        EasyToast.showShort(context, "请选择正面照");
                        return;
                    }

                    if (TextUtils.isEmpty(pic2)) {
                        EasyToast.showShort(context, "请选择反面照");
                        return;
                    }

                    if (TextUtils.isEmpty(pic3)) {
                        EasyToast.showShort(context, "请选择手持照");
                        return;
                    }
                    imgfiles.add(new File(pic));
                    imgfiles.add(new File(pic2));
                    imgfiles.add(new File(pic3));

                    imgnames.add("pic");
                    imgnames.add("pic2");
                    imgnames.add("pic3");
                }

                dialog.show();

                userDoinfo(imgnames, imgfiles);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoPickUtils.onActivityResult(requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
            @Override
            public void onPickSuccess(ArrayList<String> photos, int requestCode) {
                final Bitmap mbitmap = BitmapFactory.decodeFile(photos.get(0));
                switch (requestCode) {
                    case 1:
                        pic = photos.get(0);
                        SimpleDraweeView1.setBackground(new BitmapDrawable(mbitmap));
                        break;
                    case 2:
                        pic2 = photos.get(0);
                        SimpleDraweeView2.setBackground(new BitmapDrawable(mbitmap));
                        break;
                    case 3:
                        pic3 = photos.get(0);
                        SimpleDraweeView3.setBackground(new BitmapDrawable(mbitmap));
                        break;
                    default:
                        break;
                }
                Log.e("MyMessageActivity", photos.get(0));
            }

            @Override
            public void onPreviewBack(ArrayList<String> photos, int requestCode) {
                Log.e("MyMessageActivity", photos.get(0));
            }

            @Override
            public void onPickFail(String error, int requestCode) {
                EasyToast.showShort(context, error);
            }

            @Override
            public void onPickCancle(int requestCode) {
                EasyToast.showShort(context, "取消选择");
            }

        });
    }
}