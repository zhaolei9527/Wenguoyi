package com.wenguoyi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.UserLineBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.View.WenguoyiRecycleView;
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
public class MyChongZhiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_chongzhi)
    TextView tvChongzhi;
    @BindView(R.id.ll_chongzhi)
    LinearLayout llChongzhi;
    @BindView(R.id.tv_chongzhijilu)
    TextView tvChongzhijilu;
    @BindView(R.id.ll_chongzhijilu)
    LinearLayout llChongzhijilu;
    @BindView(R.id.tv_caiwumingxi)
    TextView tvCaiwumingxi;
    @BindView(R.id.ll_caiwumingxi)
    LinearLayout llCaiwumingxi;
    @BindView(R.id.et_chongzhijine)
    EditText etChongzhijine;
    @BindView(R.id.lL_c_chongzhi)
    LinearLayout lLCChongzhi;
    @BindView(R.id.re_caiwumingxi)
    WenguoyiRecycleView re_caiwumingxi;
    @BindView(R.id.ll_c_chongzhijilu)
    LinearLayout llCChongzhijilu;
    @BindView(R.id.re_chongzhijilu)
    WenguoyiRecycleView reChongzhijilu;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.ll_c_caiwumingxi)
    LinearLayout llCCaiwumingxi;

    @Override
    protected int setthislayout() {
        return R.layout.activity_mychongzhi_layout;
    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initListener() {
        llChongzhi.setOnClickListener(this);
        llCaiwumingxi.setOnClickListener(this);
        llChongzhijilu.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        userJine();
    }

    /**
     * 余额获取
     */
    private void userJine() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("type", "1");
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyChongZhiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/jine", "user/jine", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyChongZhiActivity", result);
                try {
                    UserLineBean userLineBean = new Gson().fromJson(result, UserLineBean.class);
                    if (1 == userLineBean.getStatus()) {
                        tvYue.setText("￥" + userLineBean.getAmount());
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_chongzhi:
                tvChongzhi.setTextColor(getResources().getColor(R.color.bgtitle));
                tvChongzhijilu.setTextColor(getResources().getColor(R.color.text666));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.text666));
                lLCChongzhi.setVisibility(View.VISIBLE);
                llCChongzhijilu.setVisibility(View.GONE);
                llCCaiwumingxi.setVisibility(View.GONE);
                break;
            case R.id.ll_chongzhijilu:
                tvChongzhi.setTextColor(getResources().getColor(R.color.text666));
                tvChongzhijilu.setTextColor(getResources().getColor(R.color.bgtitle));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.text666));
                lLCChongzhi.setVisibility(View.GONE);
                llCChongzhijilu.setVisibility(View.VISIBLE);
                llCCaiwumingxi.setVisibility(View.GONE);
                break;
            case R.id.ll_caiwumingxi:
                tvChongzhi.setTextColor(getResources().getColor(R.color.text666));
                tvChongzhijilu.setTextColor(getResources().getColor(R.color.text666));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.bgtitle));
                lLCChongzhi.setVisibility(View.GONE);
                llCChongzhijilu.setVisibility(View.GONE);
                llCCaiwumingxi.setVisibility(View.VISIBLE);
                break;
            default:

                break;
        }
    }
}
