package com.wenguoyi.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import com.google.gson.Gson;
import com.wenguoyi.Adapter.CaiWuMingXiAdapter;
import com.wenguoyi.Adapter.TiXianJiLuAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.UserCzmxBean;
import com.wenguoyi.Bean.UserLineBean;
import com.wenguoyi.Bean.UserTxRecordBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

import static com.wenguoyi.R.id.re_caiwumingxi;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/29
 * 功能描述：
 */
public class MyQianBaoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_wyaotixian)
    TextView tvWyaotixian;
    @BindView(R.id.ll_wyaotixian)
    LinearLayout llWyaotixian;
    @BindView(R.id.ll_tixianjilu)
    LinearLayout llTixianjilu;
    @BindView(R.id.tv_caiwumingxi)
    TextView tvCaiwumingxi;
    @BindView(R.id.ll_caiwumingxi)
    LinearLayout llCaiwumingxi;
    @BindView(R.id.et_tixianmoney)
    EditText etTixianmoney;
    @BindView(R.id.tv_ketixian)
    TextView tvKetixian;
    @BindView(R.id.ll_c_wyaotixian)
    LinearLayout llCWyaotixian;
    @BindView(R.id.re_tixianjilu)
    WenguoyiRecycleView reTixianjilu;
    @BindView(R.id.ll_c_tixianjilu)
    LinearLayout llCTixianjilu;
    @BindView(re_caiwumingxi)
    WenguoyiRecycleView reCaiwumingxi;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.ll_c_caiwumingxi)
    LinearLayout llCCaiwumingxi;
    @BindView(R.id.tv_tjilu)
    TextView tvTixianjilu;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private SakuraLinearLayoutManager line;
    private SakuraLinearLayoutManager line2;
    private int txjlp = 1;
    private int cwmxp = 1;
    private Dialog dialog;


    @Override
    protected int setthislayout() {
        return R.layout.activity_myqianbao_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reTixianjilu.setLayoutManager(line);
        reTixianjilu.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reTixianjilu.setFootLoadingView(progressView);
        reTixianjilu.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                txjlp = txjlp + 1;
                userTx_record();
            }
        });

        line2 = new SakuraLinearLayoutManager(context);
        line2.setOrientation(LinearLayoutManager.VERTICAL);
        reCaiwumingxi.setLayoutManager(line2);
        reCaiwumingxi.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView2 = new ProgressView(context);
        progressView2.setIndicatorId(ProgressView.BallRotate);
        progressView2.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reCaiwumingxi.setFootLoadingView(progressView2);
        reCaiwumingxi.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                cwmxp = cwmxp + 1;
                userTxmx();
            }
        });
    }

    @Override
    protected void initListener() {
        llWyaotixian.setOnClickListener(this);
        llCaiwumingxi.setOnClickListener(this);
        llTixianjilu.setOnClickListener(this);
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
            userJine();
            userTx_record();
            userTxmx();
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

    @Override
    public void onClick(View view) {
        txjlp = 1;
        cwmxp = 1;
        switch (view.getId()) {
            case R.id.ll_wyaotixian:
                tvWyaotixian.setTextColor(getResources().getColor(R.color.bgtitle));
                tvTixianjilu.setTextColor(getResources().getColor(R.color.text666));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.text666));
                llCWyaotixian.setVisibility(View.VISIBLE);
                llCTixianjilu.setVisibility(View.GONE);
                llCCaiwumingxi.setVisibility(View.GONE);
                userJine();
                break;
            case R.id.ll_tixianjilu:
                tvWyaotixian.setTextColor(getResources().getColor(R.color.text666));
                tvTixianjilu.setTextColor(getResources().getColor(R.color.bgtitle));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.text666));
                llCWyaotixian.setVisibility(View.GONE);
                llCTixianjilu.setVisibility(View.VISIBLE);
                llCCaiwumingxi.setVisibility(View.GONE);
                userTx_record();
                break;
            case R.id.ll_caiwumingxi:
                tvWyaotixian.setTextColor(getResources().getColor(R.color.text666));
                tvTixianjilu.setTextColor(getResources().getColor(R.color.text666));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.bgtitle));
                llCWyaotixian.setVisibility(View.GONE);
                llCTixianjilu.setVisibility(View.GONE);
                llCCaiwumingxi.setVisibility(View.VISIBLE);
                userTxmx();
                break;
            case R.id.btn_submit:
                String tixianmoney = etTixianmoney.getText().toString().trim();
                if (TextUtils.isEmpty(tixianmoney)) {
                    EasyToast.showShort(context, "请输入提现金额");
                    return;
                }

                if (Utils.isConnected(context)) {
                    userDotxsq();
                } else {
                    EasyToast.showShort(context, R.string.Networkexception);
                }

                break;
            default:

                break;
        }
    }

    /**
     * 余额获取
     */
    private void userJine() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("type", "2");
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyQianBaoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/jine", "user/jine", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyQianBaoActivity", result);
                try {
                    dialog.dismiss();
                    UserLineBean userLineBean = new Gson().fromJson(result, UserLineBean.class);
                    if (1 == userLineBean.getStatus()) {
                        tvYue.setText("￥" + userLineBean.getAmount());
                        tvKetixian.setText("可提现金额：" + userLineBean.getAmount());
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
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 我要提现
     */
    private void userDotxsq() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("money", etTixianmoney.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyQianBaoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/dotxsq", "user/dotxsq", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyQianBaoActivity", result);
                try {
                    dialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        etTixianmoney.setText("");
                        EasyToast.showShort(context, codeBean.getMsg());
                        dialog.show();
                        userJine();
                    } else if ("-1".equals(String.valueOf(codeBean.getStatus()))) {
                        EasyToast.showShort(context, codeBean.getMsg());
                    }
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
     * 我的钱包-提现记录
     */
    private void userTx_record() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(txjlp));
        Log.e("MyQianBaoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/tx_record", "user/tx_record", params, new VolleyInterface(context) {
            private TiXianJiLuAdapter tiXianJiLuAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("MyQianBaoActivity", result);
                try {
                    UserTxRecordBean userTxRecordBean = new Gson().fromJson(result, UserTxRecordBean.class);
                    if (1 == userTxRecordBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == txjlp) {
                            tiXianJiLuAdapter = new TiXianJiLuAdapter(MyQianBaoActivity.this, userTxRecordBean.getMsg());
                            reTixianjilu.setAdapter(tiXianJiLuAdapter);
                        } else {
                            reTixianjilu.loadMoreComplete();
                            tiXianJiLuAdapter.setDatas(userTxRecordBean.getMsg());
                        }
                        if (0 == userTxRecordBean.getFy()) {
                            reTixianjilu.loadMoreEnd();
                            reTixianjilu.setCanloadMore(false);
                        } else {
                            reTixianjilu.setCanloadMore(true);
                        }
                    } else {
                        if (1 == txjlp) {
                            LLEmpty.setVisibility(View.VISIBLE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MyQianBaoActivity.this, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 我的充值-财务明细
     */
    private void userTxmx() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(cwmxp));
        Log.e("MyQianBaoActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/txmx", "user/txmx", params, new VolleyInterface(context) {
            private CaiWuMingXiAdapter caiWuMingXiAdapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("MyQianBaoActivity", result);
                try {
                    UserCzmxBean userCzmxBean = new Gson().fromJson(result, UserCzmxBean.class);
                    if (1 == userCzmxBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == cwmxp) {
                            caiWuMingXiAdapter = new CaiWuMingXiAdapter(MyQianBaoActivity.this, userCzmxBean.getMsg());
                            reCaiwumingxi.setAdapter(caiWuMingXiAdapter);
                        } else {
                            reCaiwumingxi.loadMoreComplete();
                            caiWuMingXiAdapter.setDatas(userCzmxBean.getMsg());
                        }
                        if (0 == userCzmxBean.getFy()) {
                            reCaiwumingxi.loadMoreEnd();
                            reCaiwumingxi.setCanloadMore(false);
                        } else {
                            reCaiwumingxi.setCanloadMore(true);
                        }
                    } else {
                        if (1 == cwmxp) {
                            LLEmpty.setVisibility(View.VISIBLE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MyQianBaoActivity.this, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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
