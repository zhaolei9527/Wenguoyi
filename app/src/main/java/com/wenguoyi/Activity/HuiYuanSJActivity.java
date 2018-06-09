package com.wenguoyi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.UserUserBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
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
 * @date 2018/5/29
 * 功能描述：
 */
public class HuiYuanSJActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_dengji)
    TextView tvDengji;
    @BindView(R.id.SimpleDraweeViewA)
    SimpleDraweeView SimpleDraweeViewA;
    @BindView(R.id.tv_titleA)
    TextView tvTitleA;
    @BindView(R.id.tv_moneyA)
    TextView tvMoneyA;
    @BindView(R.id.tv_contentA)
    TextView tvContentA;
    @BindView(R.id.SimpleDraweeViewB)
    SimpleDraweeView SimpleDraweeViewB;
    @BindView(R.id.tv_titleB)
    TextView tvTitleB;
    @BindView(R.id.tv_moneyB)
    TextView tvMoneyB;
    @BindView(R.id.tv_contentB)
    TextView tvContentB;
    @BindView(R.id.SimpleDraweeViewC)
    SimpleDraweeView SimpleDraweeViewC;
    @BindView(R.id.tv_titleC)
    TextView tvTitleC;
    @BindView(R.id.tv_moneyC)
    TextView tvMoneyC;
    @BindView(R.id.tv_contentC)
    TextView tvContentC;
    @BindView(R.id.ll_A)
    LinearLayout llA;
    @BindView(R.id.ll_B)
    LinearLayout llB;
    @BindView(R.id.ll_C)
    LinearLayout llC;

    @Override
    protected int setthislayout() {
        return R.layout.activity_huiyuan_shengji_layout;
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
        if (Utils.isConnected(context)) {
            userUser();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }


    /**
     * 会员升级
     */
    private void userUser() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("HuiYuanSJActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/user", "user/user", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("HuiYuanSJActivity", result);
                try {
                    final UserUserBean userUserBean = new Gson().fromJson(result, UserUserBean.class);
                    if (1 == userUserBean.getStatus()) {
                        tvDengji.setText("您当前的会员等级：" + userUserBean.getLevel());
                        SimpleDraweeViewA.setImageURI(UrlUtils.URL + userUserBean.getList().get(0).getImg());
                        SimpleDraweeViewB.setImageURI(UrlUtils.URL + userUserBean.getList().get(1).getImg());
                        SimpleDraweeViewC.setImageURI(UrlUtils.URL + userUserBean.getList().get(2).getImg());
                        tvContentA.setText(userUserBean.getList().get(0).getContent());
                        tvContentB.setText(userUserBean.getList().get(1).getContent());
                        tvContentC.setText(userUserBean.getList().get(2).getContent());
                        tvMoneyA.setText("￥" + userUserBean.getList().get(0).getPrice());
                        tvMoneyB.setText("￥" + userUserBean.getList().get(1).getPrice());
                        tvMoneyC.setText("￥" + userUserBean.getList().get(2).getPrice());
                        llA.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(0).getId()));
                            }
                        });
                        llB.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(1).getId()));
                            }
                        });
                        llC.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(2).getId()));
                            }
                        });


                    } else {
                        EasyToast.showShort(context, R.string.Abnormalserver);
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
        App.getQueues().cancelAll("user/user");
        ButterKnife.bind(this);
    }
}
