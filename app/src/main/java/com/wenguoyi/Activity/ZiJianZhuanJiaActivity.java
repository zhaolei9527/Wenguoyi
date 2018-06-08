package com.wenguoyi.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Validator;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/29
 * 功能描述：
 */
public class ZiJianZhuanJiaActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_Content)
    EditText etContent;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String name;
    private String phone;
    private String content;

    @Override
    protected int setthislayout() {
        return R.layout.activity_zijianzhuanjia;
    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etName.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                content = etContent.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    EasyToast.showShort(context, "请输入姓名");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    EasyToast.showShort(context, "请输入手机号");
                    return;
                }

                if (!Validator.isMobile(phone)) {
                    EasyToast.showShort(context, "请输入有效手机号");
                    return;
                }

                if (TextUtils.isEmpty(content)) {
                    EasyToast.showShort(context, "请输入相关描述内容");
                    return;
                }
                userDozhuanjia();
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 我要提现
     */
    private void userDozhuanjia() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("name", name);
        params.put("tel", phone);
        params.put("content", content);
        Log.e("MyQianBaoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/dozhuanjia", "user/dozhuanjia", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyQianBaoActivity", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        etName.setText("");
                        etContent.setText("");
                        etPhone.setText("");
                        EasyToast.showShort(context, "提交成功");
                    } else {
                        EasyToast.showShort(context, "提交失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
