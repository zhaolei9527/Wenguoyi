package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.UserDetailBean;
import com.wenguoyi.Bean.UserShengJiBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/29
 * 功能描述：
 */
public class HuiYuanSJDetailsActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.forum_context)
    WebView forumContext;
    @BindView(R.id.tv_shengji)
    TextView tvShengji;
    @BindView(R.id.tv_dismiss)
    TextView tvDismiss;
    @BindView(R.id.tv_xiaofei)
    TextView tvXiaofei;
    @BindView(R.id.tv_dingqi)
    TextView tvDingqi;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_dismiss)
    LinearLayout llDismiss;
    @BindView(R.id.tv_xuzhifu)
    TextView tvXuzhifu;
    private Dialog dialog;
    private String id;
    private UserDetailBean userDetailBean;
    private String fs = "1";

    @Override
    protected int setthislayout() {
        return R.layout.activity_huiyuansjdetails_layout;
    }

    @Override
    protected void initview() {

        id = getIntent().getStringExtra("id");

        if (TextUtils.isEmpty(id)) {
            EasyToast.showShort(context, getString(R.string.hasError));
            finish();
        }

        dialog = Utils.showLoadingDialog(context);
        if (!dialog.isShowing()) {
            dialog.show();
        }

        UserDetail();


        //注册EventBus
        if (!EventBus.getDefault().isRegistered(HuiYuanSJDetailsActivity.this)) {
            EventBus.getDefault().register(HuiYuanSJDetailsActivity.this);
        }

        IX5WebViewExtension ix5 = forumContext.getX5WebViewExtension();
        if (null != ix5) {
            ix5.setScrollBarFadingEnabled(false);
        }
        // 开启 localStorage
        forumContext.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        forumContext.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        forumContext.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        forumContext.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 启动缓存
        forumContext.getSettings().setAppCacheEnabled(true);
        forumContext.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        forumContext.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                // forumContext.loadUrl("javascript:(" + readJS() + ")()");
                dialog.dismiss();
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                //重新测量
                webView.measure(w, h);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                Toast.makeText(context, getString(R.string.hasError), Toast.LENGTH_SHORT).show();

            }
        });

        forumContext.loadUrl(UrlUtils.BASE_URL + "/danye/type/id/" + id);

    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvShengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDismiss.setVisibility(View.VISIBLE);
            }
        });

        tvXiaofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvXiaofei.setBackground(getResources().getDrawable(R.drawable.bg_shengji_check));
                tvDingqi.setBackground(getResources().getDrawable(R.drawable.bg_shengji_nomore));
                tvXuzhifu.setText("需要支付金额：￥" + userDetailBean.getMsg().getPrice());
                fs = "1";
            }
        });

        tvDingqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvXiaofei.setBackground(getResources().getDrawable(R.drawable.bg_shengji_nomore));
                tvDingqi.setBackground(getResources().getDrawable(R.drawable.bg_shengji_check));
                tvXuzhifu.setText("需要支付金额：￥" + userDetailBean.getMsg().getRugujin());
                fs = "2";
            }
        });

        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDismiss.setVisibility(View.GONE);
            }
        });

        llDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llDismiss.setVisibility(View.GONE);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                userShengji();
            }
        });


    }

    /**
     * 会员升级支付
     */
    private void userShengji() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("id", id);
        params.put("fs", fs);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("HuiYuanSJDetails", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/shengji", "user/shengji", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("HuiYuanSJDetails", result);
                try {
                    dialog.dismiss();
                    if (result.contains("wx82670e4f447fbb75")) {
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
     * 会员升级支付
     */
    private void UserDetail() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("id", id);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("HuiYuanSJDetails", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/user_detail", "user/user_detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("HuiYuanSJDetails", result);
                try {
                    userDetailBean = new Gson().fromJson(result, UserDetailBean.class);
                    if (1 == userDetailBean.getStatus()) {
                        tvXiaofei.setText("存入金额￥" + userDetailBean.getMsg().getPrice() + "（只能消费）");
                        tvDingqi.setText("存入入股金￥" + userDetailBean.getMsg().getRugujin() + "(定期分红)");
                        tvXuzhifu.setText("需要支付金额：￥" + userDetailBean.getMsg().getPrice());
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

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BankEvent event) {
        if (!TextUtils.isEmpty(event.getmType())) {
            if ("pay".equals(event.getmType())) {
                if ("good".equals(event.getMsg())) {
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                } else if ("bad".equals(event.getMsg())) {
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(HuiYuanSJDetailsActivity.this);
        System.gc();
    }


}
