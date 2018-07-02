package com.wenguoyi.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.YiShengDetailsBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/25
 * 功能描述：
 */
public class YiShengDetailsActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_zhicheng)
    TextView tvZhicheng;
    @BindView(R.id.tv_shanchang)
    TextView tvShanchang;
    @BindView(R.id.tv_jianjie)
    TextView tvJianjie;
    @BindView(R.id.tv_yiyuan)
    TextView tvYiyuan;
    private String id;

    @Override
    protected int setthislayout() {
        return R.layout.yishengdetails_activity_layout;
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
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) {
            EasyToast.showShort(context, R.string.hasError);
            finish();
        } else {
            if (Utils.isConnected(context)) {
                getData();
            } else {
                EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
                finish();
            }
        }
    }

    private void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("id", id);
        Log.e("YiShengDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "yisheng/detail", "yisheng/detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("YiShengDetailsActivity", result);
                try {
                    YiShengDetailsBean yiShengDetailsBean = new Gson().fromJson(result, YiShengDetailsBean.class);
                    if (1 == yiShengDetailsBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + yiShengDetailsBean.getMsg().getHead());
                        tvZhicheng.setText(yiShengDetailsBean.getMsg().getKeshi() + "|" + yiShengDetailsBean.getMsg().getZhicheng());
                        tvName.setText(yiShengDetailsBean.getMsg().getName());
                        tvJianjie.setText("      " + yiShengDetailsBean.getMsg().getInfo());
                        tvShanchang.setText("      " + yiShengDetailsBean.getMsg().getShanchang());
                        tvYiyuan.setText(yiShengDetailsBean.getMsg().getYiyuan());
                    } else {
                        EasyToast.showShort(context, R.string.hasError);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
