package com.wenguoyi.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.OrderPay;
import com.wenguoyi.Bean.OrderWxpayBean;
import com.wenguoyi.Bean.PayResult;
import com.wenguoyi.R;
import com.wenguoyi.Utils.Constants;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;


public class PayActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout rl_back;
    private TextView tv_ordernumber;
    private TextView tv_totalmoney;
    private ImageView img_weixin;
    private CheckBox Choosedweixin;
    private Button btn_paynow;
    private String orderid;
    private String order;
    private Dialog dialog;
    private IWXAPI api;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Log.e("PayActivity", resultInfo.toString());
                    String resultStatus = payResult.getResultStatus();
                    Log.e("PayActivity", resultStatus.toString());
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        EasyToast.showShort(context, "支付成功");
                        EventBus.getDefault().post(
                                new BankEvent("good", "pay"));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        EasyToast.showShort(context, "支付失败，请重试");
                        EventBus.getDefault().post(
                                new BankEvent("bad", "pay"));
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (isfinish) {
            isfinish = !isfinish;
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/pay");
        //反注册EventBus
        EventBus.getDefault().unregister(PayActivity.this);
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initview() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        tv_ordernumber = (TextView) findViewById(R.id.tv_ordernumber);
        tv_totalmoney = (TextView) findViewById(R.id.tv_totalmoney);
        img_weixin = (ImageView) findViewById(R.id.img_weixin);
        Choosedweixin = (CheckBox) findViewById(R.id.Choosedweixin);
        btn_paynow = (Button) findViewById(R.id.btn_paynow);
        orderid = getIntent().getStringExtra("orderid");
        order = getIntent().getStringExtra("order");
        if (!TextUtils.isEmpty(order)) {
            tv_ordernumber.setText(order);
        }

        //注册EventBus
        if (!EventBus.getDefault().isRegistered(PayActivity.this)) {
            EventBus.getDefault().register(PayActivity.this);
        }

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        dialog = Utils.showLoadingDialog(context);
    }

    @Override
    protected void initListener() {
        btn_paynow.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            orderPay();
        } else {
            finish();
            EasyToast.showShort(context, "网络未连接");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_paynow:
                if (Utils.isConnected(context)) {
                    if (!dialog.isShowing()) {
                        dialog.show();
                    }
                    orderWxpay();
                } else {
                    EasyToast.showShort(context, "网络未连接");
                }
                break;
            case R.id.rl_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 订单支付
     */
    private void orderPay() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("id", orderid);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("RegisterActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/pay", "order/pay", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("RegisterActivity", result);
                try {
                    OrderPay orderPay = new Gson().fromJson(result, OrderPay.class);
                    if ("1".equals(String.valueOf(orderPay.getStatus()))) {
                        tv_totalmoney.setText("￥" + orderPay.getMsg().getMoney());
                        tv_ordernumber.setText(String.valueOf(orderPay.getMsg().getOrderid()));
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
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
     * 订单支付
     */
    private void orderWxpay() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("id", orderid);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("orderWxpay", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/dopay", "order/dopay", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("orderWxpay", result);
                try {
                    OrderWxpayBean orderWxpayBean = new Gson().fromJson(result, OrderWxpayBean.class);
                    if (api != null) {
                        PayReq req = new PayReq();
                        req.appId = Constants.APP_ID;
                        req.partnerId = orderWxpayBean.getMsg().getMch_id();
                        req.prepayId = orderWxpayBean.getMsg().getPrepay_id();
                        req.packageValue = "Sign=WXPay";
                        req.nonceStr = orderWxpayBean.getMsg().getNonceStr();
                        req.timeStamp = orderWxpayBean.getMsg().getTimeStamp();
                        req.sign = "aaaaa";
                        api.sendReq(req);
                    }
                    orderWxpayBean = null;
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

    public static boolean isfinish = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BankEvent event) {
        if (!TextUtils.isEmpty(event.getmType())) {
            if ("pay".equals(event.getmType())) {
                if ("good".equals(event.getMsg())) {
                    startActivity(new Intent(context, GoodPayActivity.class)
                            .putExtra("type", "good")
                            .putExtra("order", order)
                            .putExtra("orderid", orderid));
                    finish();
                } else if ("bad".equals(event.getMsg())) {
                    startActivity(new Intent(context, GoodPayActivity.class)
                            .putExtra("order", order)
                            .putExtra("orderid", orderid));
                    finish();
                }
            }
        }
    }

}
