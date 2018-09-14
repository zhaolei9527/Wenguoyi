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
    @BindView(R.id.tv_titleSSS)
    TextView tvTitleSSS;
    @BindView(R.id.tv_moneySSS)
    TextView tvMoneySSS;
    @BindView(R.id.tv_contentSSS)
    TextView tvContentSSS;
    @BindView(R.id.ll_SSS)
    LinearLayout llSSS;
    @BindView(R.id.SimpleDraweeViewSSS)
    SimpleDraweeView SimpleDraweeViewSSS;

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
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/usertype", "user/usertype", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("HuiYuanSJActivity", result);
                try {
                    final UserUserBean userUserBean = new Gson().fromJson(result, UserUserBean.class);
                    if (1 == userUserBean.getStatus()) {
                        tvDengji.setText("您当前的会员等级：" + userUserBean.getLevel());

                        if (userUserBean.getList().size() > 3) {
                            SimpleDraweeViewSSS.setImageURI(UrlUtils.URL + userUserBean.getList().get(3).getImg());
                            tvMoneySSS.setText("￥" + userUserBean.getList().get(3).getPrice());
                            tvContentSSS.setText(userUserBean.getList().get(3).getContent());
                            llSSS.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(3).getId()));
                                }
                            });
                            llSSS.setVisibility(View.VISIBLE);
                        }

                        if (userUserBean.getList().size() > 2) {
                            SimpleDraweeViewC.setImageURI(UrlUtils.URL + userUserBean.getList().get(2).getImg());
                            tvMoneyC.setText("￥" + userUserBean.getList().get(2).getPrice());
                            tvContentC.setText(userUserBean.getList().get(2).getContent());
                            llC.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(2).getId()));
                                }
                            });
                            llC.setVisibility(View.VISIBLE);
                        }

                        if (userUserBean.getList().size() > 1) {
                            SimpleDraweeViewB.setImageURI(UrlUtils.URL + userUserBean.getList().get(1).getImg());
                            tvContentB.setText(userUserBean.getList().get(1).getContent());
                            tvMoneyB.setText("￥" + userUserBean.getList().get(1).getPrice());
                            llB.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(1).getId()));
                                }
                            });
                            llB.setVisibility(View.VISIBLE);
                        }

                        if (userUserBean.getList().size() > 0) {
                            SimpleDraweeViewA.setImageURI(UrlUtils.URL + userUserBean.getList().get(0).getImg());
                            tvContentA.setText(userUserBean.getList().get(0).getContent());
                            tvMoneyA.setText("￥" + userUserBean.getList().get(0).getPrice());
                            llA.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(context, HuiYuanSJDetailsActivity.class).putExtra("id", userUserBean.getList().get(0).getId()));
                                }
                            });
                            llA.setVisibility(View.VISIBLE);
                        }

                    } else {
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
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
