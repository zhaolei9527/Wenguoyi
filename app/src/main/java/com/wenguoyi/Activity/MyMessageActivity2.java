package com.wenguoyi.Activity;

import android.app.Dialog;
import android.graphics.Color;
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
import com.bigkoo.pickerview.OptionsPickerView;
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
public class MyMessageActivity2 extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_xueli)
    EditText etXueli;
    @BindView(R.id.et_zhiye)
    EditText etZhiye;
    @BindView(R.id.et_youxiang)
    EditText etYouxiang;
    @BindView(R.id.et_yinhang)
    EditText etYinhang;
    @BindView(R.id.et_yinhangzhanghao)
    EditText etYinhangzhanghao;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.SimpleDraweeView1)
    SimpleDraweeView SimpleDraweeView1;
    @BindView(R.id.rl_zhengmian)
    RelativeLayout rlZhengmian;
    @BindView(R.id.ll_zhengmian)
    LinearLayout llZhengmian;
    @BindView(R.id.SimpleDraweeView2)
    SimpleDraweeView SimpleDraweeView2;
    @BindView(R.id.rl_fanmian)
    RelativeLayout rlFanmian;
    @BindView(R.id.ll_fanmian)
    LinearLayout llFanmian;
    @BindView(R.id.SimpleDraweeView3)
    SimpleDraweeView SimpleDraweeView3;
    @BindView(R.id.rl_shouchi)
    RelativeLayout rlShouchi;
    @BindView(R.id.ll_shouchi)
    LinearLayout llShouchi;
    @BindView(R.id.et_yinhang_name)
    EditText etYinhangName;
    private Dialog dialog;
    private String pic = "";
    private String pic2 = "";
    private String pic3 = "";
    private ArrayList<String> sexs = new ArrayList<>();

    private String issexs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void ShowPickerView_babysex(String TITLE) {// 弹出选择器
        if (!sexs.isEmpty()) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    tvSex.setText(sexs.get(options1));
                    issexs = String.valueOf(options1 + 1);
                }
            })
                    .setTitleBgColor(getResources().getColor(R.color.bgtitle))
                    .setCancelColor(getResources().getColor(R.color.text))
                    .setSubmitColor(getResources().getColor(R.color.text))
                    .setTitleText(TITLE)
                    .setTitleColor(getResources().getColor(R.color.text))
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();
            pvOptions.setPicker(sexs);//三级选择器
            pvOptions.show();
        }
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_mymessage2_layout;
    }

    @Override
    protected void initview() {
        etPhone.setText("" + SpUtil.get(context, "tel", ""));
        sexs.add("男");
        sexs.add("女");
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

        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPickerView_babysex("请选择性别");
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
                            btnSubmit.setVisibility(View.VISIBLE);

                            if ("1".equals(userResetinforBean.getMsg().getSex())) {
                                tvSex.setText("男");
                            } else {
                                tvSex.setText("女");
                            }

                            etZhiye.setText(userResetinforBean.getMsg().getZhiye());
                            etName.setText(userResetinforBean.getMsg().getName());
                            etShenfenzheng.setText(userResetinforBean.getMsg().getCard_no());
                            etAddress.setText(userResetinforBean.getMsg().getAddress());
                            etXueli.setText(userResetinforBean.getMsg().getXueli());
                            etYinhang.setText(userResetinforBean.getMsg().getBank());
                            etYinhangzhanghao.setText(userResetinforBean.getMsg().getBno());
                            etYouxiang.setText(userResetinforBean.getMsg().getEmail());
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
        final HashMap<String, String> params = new HashMap<>(11);

        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("name", etName.getText().toString().trim());

        if (tvSex.getText().toString().equals("男")) {
            params.put("sex", "1");
        } else if (tvSex.getText().toString().equals("女")) {
            params.put("sex", "2");
        } else {
            params.put("sex", "3");
        }

        params.put("sex", tvSex.getText().toString().trim());
        params.put("xueli", etXueli.getText().toString().trim());
        params.put("zhiye", etZhiye.getText().toString().trim());
        params.put("email", etYouxiang.getText().toString().trim());
        params.put("bank", etYinhang.getText().toString().trim());
        params.put("bno", etYinhangzhanghao.getText().toString().trim());
        params.put("bname", etYinhangName.getText().toString().trim());
        params.put("address", etAddress.getText().toString().trim());
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
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity2.this, 1);
                break;
            case R.id.ll_fanmian:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity2.this, 2);
                break;
            case R.id.ll_shouchi:
                PhotoPickUtils.startPick().setShowCamera(true).setShowGif(false).setPhotoCount(1).start(MyMessageActivity2.this, 3);
                break;
            case R.id.btn_submit:

                String Name = etName.getText().toString().trim();

                String Phone = etPhone.getText().toString().trim();

                String Shenfenzheng = etShenfenzheng.getText().toString().trim();

                String sex = tvSex.getText().toString().trim();

                String xueLi = etXueli.getText().toString().trim();

                String zhiYe = etZhiye.getText().toString().trim();

                String youXiang = etYouxiang.getText().toString().trim();

                String yinHang = etYinhang.getText().toString().trim();

                String yinHangZhangHao = etYinhangzhanghao.getText().toString().trim();

                String yinHangName = etYinhangName.getText().toString().trim();


                String address = etAddress.getText().toString().trim();

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

                if (TextUtils.isEmpty(sex)) {
                    EasyToast.showShort(context, "请选择性别");
                    return;
                }

                if (TextUtils.isEmpty(xueLi)) {
                    EasyToast.showShort(context, "请输入您的学历");
                    return;
                }

                if (TextUtils.isEmpty(zhiYe)) {
                    EasyToast.showShort(context, "请输入您的职业");
                    return;
                }

                if (TextUtils.isEmpty(youXiang)) {
                    EasyToast.showShort(context, "请输入您的邮箱");
                    return;
                }

                if (TextUtils.isEmpty(yinHang)) {
                    EasyToast.showShort(context, "请输入您的开户银行");
                    return;
                }

                if (TextUtils.isEmpty(yinHangName)) {
                    EasyToast.showShort(context, "请输入您的开户名称");
                    return;
                }


                if (TextUtils.isEmpty(yinHangZhangHao)) {
                    EasyToast.showShort(context, "请输入您的银行帐号");
                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    EasyToast.showShort(context, "请输入您的地址");
                    return;
                }

                List<File> imgfiles = new ArrayList<>();
                List<String> imgnames = new ArrayList<>();

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

                dialog.show();
                userDoinfo(imgnames, imgfiles);
                break;
            default:
                break;
        }
    }
}