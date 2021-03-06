package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wenguoyi.Adapter.CaiWuMingXiAdapter;
import com.wenguoyi.Adapter.ChongZhiJiLuAdapter;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.UserCzmxBean;
import com.wenguoyi.Bean.UserLineBean;
import com.wenguoyi.Bean.UserMoneyBean;
import com.wenguoyi.Bean.UserShengJiBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.Constants;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

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
    @BindView(R.id.btn_submit)
    Button btn_submit;
    private int czjlp = 1;
    private int cwmxp = 1;


    private SakuraLinearLayoutManager line;
    private SakuraLinearLayoutManager line2;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_mychongzhi_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reChongzhijilu.setLayoutManager(line);
        reChongzhijilu.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reChongzhijilu.setFootLoadingView(progressView);
        reChongzhijilu.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                czjlp = czjlp + 1;
                userMoney();
            }
        });

        line2 = new SakuraLinearLayoutManager(context);
        line2.setOrientation(LinearLayoutManager.VERTICAL);
        re_caiwumingxi.setLayoutManager(line2);
        re_caiwumingxi.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView2 = new ProgressView(context);
        progressView2.setIndicatorId(ProgressView.BallRotate);
        progressView2.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        re_caiwumingxi.setFootLoadingView(progressView2);
        re_caiwumingxi.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                cwmxp = cwmxp + 1;
                userCzmx();
            }
        });
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(MyChongZhiActivity.this)) {
            EventBus.getDefault().register(MyChongZhiActivity.this);
        }
    }

    @Override
    protected void initListener() {
        btn_submit.setOnClickListener(this);
        llChongzhi.setOnClickListener(this);
        llCaiwumingxi.setOnClickListener(this);
        llChongzhijilu.setOnClickListener(this);
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
            userMoney();
            userCzmx();
        } else {
            EasyToast.showShort(context, R.string.Networkexception);
        }


    }

    @Override
    public void onClick(View view) {
        czjlp = 1;
        cwmxp = 1;
        switch (view.getId()) {
            case R.id.btn_submit:
                if (TextUtils.isEmpty(etChongzhijine.getText().toString().trim())) {
                    EasyToast.showShort(context, "请输入充值金额");
                } else {
                    userDopay();
                }
                break;
            case R.id.ll_chongzhi:
                tvChongzhi.setTextColor(getResources().getColor(R.color.bgtitle));
                tvChongzhijilu.setTextColor(getResources().getColor(R.color.text666));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.text666));
                lLCChongzhi.setVisibility(View.VISIBLE);
                llCChongzhijilu.setVisibility(View.GONE);
                llCCaiwumingxi.setVisibility(View.GONE);
                userJine();
                break;
            case R.id.ll_chongzhijilu:
                tvChongzhi.setTextColor(getResources().getColor(R.color.text666));
                tvChongzhijilu.setTextColor(getResources().getColor(R.color.bgtitle));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.text666));
                lLCChongzhi.setVisibility(View.GONE);
                llCChongzhijilu.setVisibility(View.VISIBLE);
                llCCaiwumingxi.setVisibility(View.GONE);
                userMoney();
                break;
            case R.id.ll_caiwumingxi:
                tvChongzhi.setTextColor(getResources().getColor(R.color.text666));
                tvChongzhijilu.setTextColor(getResources().getColor(R.color.text666));
                tvCaiwumingxi.setTextColor(getResources().getColor(R.color.bgtitle));
                lLCChongzhi.setVisibility(View.GONE);
                llCChongzhijilu.setVisibility(View.GONE);
                llCCaiwumingxi.setVisibility(View.VISIBLE);
                userCzmx();
                break;
            default:

                break;
        }
    }


    /**
     * 我要充值
     */
    private void userDopay() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("money", etChongzhijine.getText().toString().trim());
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MyChongZhiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/dopay", "user/dopay", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyChongZhiActivity", result);
                try {
                    dialog.dismiss();
                    if (result.contains("wx90ea24410608f881")) {
                        UserShengJiBean userShengJiBean = new Gson().fromJson(result, UserShengJiBean.class);
                        IWXAPI api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
                        api.registerApp(Constants.APP_ID);
                        if (api != null) {
                            PayReq req = new PayReq();
                            req.appId = Constants.APP_ID;
                            req.partnerId = userShengJiBean.getMsg().getMch_id();
                            req.prepayId = userShengJiBean.getMsg().getPrepay_id();
                            req.packageValue = "Sign=WXPay";
                            req.nonceStr = userShengJiBean.getMsg().getNonceStr();
                            req.timeStamp = userShengJiBean.getMsg().getTimeStamp();
                            req.sign = "aaaaa";
                            api.sendReq(req);
                        }
                    } else {
                        CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                        EasyToast.showShort(context, codeBean.getMsg());
                        startActivity(new Intent(context, MyMessageActivity2.class));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
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
                    dialog.dismiss();
                    UserLineBean userLineBean = new Gson().fromJson(result, UserLineBean.class);
                    if (1 == userLineBean.getStatus()) {
                        tvYue.setText("￥" + userLineBean.getAmount());
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
    }
    private ChongZhiJiLuAdapter chongZhiJiLuAdapter;

    /**
     * 充值记录
     */
    private void userMoney() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(czjlp));
        Log.e("MyChongZhiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/money", "user/money", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                Log.e("MyChongZhiActivity", result);
                try {
                    dialog.dismiss();
                    UserMoneyBean userMoneyBean = new Gson().fromJson(result, UserMoneyBean.class);
                    if (1 == userMoneyBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == czjlp) {
                            chongZhiJiLuAdapter = new ChongZhiJiLuAdapter(MyChongZhiActivity.this, userMoneyBean.getMsg());
                            reChongzhijilu.setAdapter(chongZhiJiLuAdapter);
                        } else {
                            reChongzhijilu.loadMoreComplete();
                            chongZhiJiLuAdapter.setDatas(userMoneyBean.getMsg());
                        }
                        if (0 == userMoneyBean.getFy()) {
                            reChongzhijilu.loadMoreEnd();
                            reChongzhijilu.setCanloadMore(false);
                        } else {
                            reChongzhijilu.setCanloadMore(true);
                        }
                    } else {
                        if (czjlp == 1) {
                            LLEmpty.setVisibility(View.VISIBLE);
                            llCChongzhijilu.setVisibility(View.GONE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
    }

    private CaiWuMingXiAdapter caiWuMingXiAdapter;

    /**
     * 我的充值-财务明细
     */
    private void userCzmx() {
        HashMap<String, String> params = new HashMap<>(2);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(cwmxp));
        Log.e("MyChongZhiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/czmx", "user/czmx", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                Log.e("MyChongZhiActivity", result);
                try {
                    dialog.dismiss();
                    UserCzmxBean userCzmxBean = new Gson().fromJson(result, UserCzmxBean.class);
                    if (1 == userCzmxBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == cwmxp) {
                            caiWuMingXiAdapter = new CaiWuMingXiAdapter(MyChongZhiActivity.this, userCzmxBean.getMsg());
                            re_caiwumingxi.setAdapter(caiWuMingXiAdapter);
                        } else {
                            re_caiwumingxi.loadMoreComplete();
                            caiWuMingXiAdapter.setDatas(userCzmxBean.getMsg());
                        }
                        if (0 == userCzmxBean.getFy()) {
                            re_caiwumingxi.loadMoreEnd();
                            re_caiwumingxi.setCanloadMore(false);
                        } else {
                            re_caiwumingxi.setCanloadMore(true);
                        }
                    } else {
                        if (1 == cwmxp) {
                            LLEmpty.setVisibility(View.VISIBLE);
                            llCCaiwumingxi.setVisibility(View.GONE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BankEvent event) {
        if (!TextUtils.isEmpty(event.getmType())) {
            if ("pay".equals(event.getmType())) {
                if ("good".equals(event.getMsg())) {
                    dialog.show();
                    etChongzhijine.setText("");
                    userJine();
                } else if ("bad".equals(event.getMsg())) {


                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("user/jine");
        //反注册EventBus
        EventBus.getDefault().unregister(MyChongZhiActivity.this);
        System.gc();
    }


}
