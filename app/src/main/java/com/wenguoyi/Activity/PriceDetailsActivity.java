package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jude.rollviewpager.hintview.IconHintView;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wenguoyi.Adapter.LoopAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.GoodsCangBean;
import com.wenguoyi.Bean.GoodsDetailBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DensityUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.FlowLayout;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

public class PriceDetailsActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mRlBack;
    private RelativeLayout mRlShoppingcart;
    private com.jude.rollviewpager.RollPagerView RollPagerView;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private SimpleDraweeView mSdvJilu;
    private TextView mTvJiluName;
    private TextView mTvJiluMax;
    private TextView mTvJiluTime;
    private Button mBtnAlljilu;
    private WebView mWb;
    private LinearLayout mLlHasJilv;
    private LinearLayout mLlShoucang;
    private TextView mTvAddshop;
    private TextView mShopnow;
    private Dialog dialog;
    private TextView mtvCountCart;
    private ImageView mImgShouCang;
    private GoodsDetailBean goodsDetailBean;
    private FlowLayout mFlowLayout;
    private LayoutInflater mInflater;

    private String uid;

    private String[] mkeshiVals = new String[]{"全部科室", "妇产科", "儿科 ", "综合门诊",
            "内科", "外壳", "中医科", "皮肤性病科", "康复医学", "重症医学科", "其他科室"};


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_price_details;
    }

    @Override
    protected void initview() {
        mFlowLayout = findViewById(R.id.id_flowlayout);
        mRlBack = (FrameLayout) findViewById(R.id.rl_back);
        mImgShouCang = (ImageView) findViewById(R.id.img_shoucang);
        mRlShoppingcart = (RelativeLayout) findViewById(R.id.rl_shoppingcart);
        RollPagerView = (com.jude.rollviewpager.RollPagerView) findViewById(R.id.RollPagerView);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mWb = (WebView) findViewById(R.id.wb);
        mLlShoucang = (LinearLayout) findViewById(R.id.ll_shoucang);
        mTvAddshop = (TextView) findViewById(R.id.tv_addshop);
        mShopnow = (TextView) findViewById(R.id.shopnow);
        String countCart = getIntent().getStringExtra("CountCart");
        if (!TextUtils.isEmpty(countCart)) {
            mtvCountCart.setText(countCart);
        }
        uid = (String) SpUtil.get(context, "uid", "");
        mInflater = LayoutInflater.from(this);
        /**
         * 找到搜索标签的控件
         */
        for (int i = 0; i < mkeshiVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(
                    R.layout.label_tv_keshi_layout, mFlowLayout, false);
            tv.setText(mkeshiVals[i]);
            final String str = tv.getText().toString();
            //点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mFlowLayout.addView(tv);//添加到父View
        }

    }

    @Override
    protected void initListener() {
        RollPagerView.setHintView(new IconHintView(context, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(context, getResources().getDimension(R.dimen.x7))));
        RollPagerView.setPlayDelay(30000);
        mRlBack.setOnClickListener(this);
        mLlShoucang.setOnClickListener(this);
        mTvAddshop.setOnClickListener(this);
        mRlShoppingcart.setOnClickListener(this);
        mShopnow.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        String id = getIntent().getStringExtra("id");

        if (TextUtils.isEmpty(id)) {
            EasyToast.showShort(context, R.string.hasError);
            finish();
        }

        // 开启 localStorage
        mWb.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        mWb.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        mWb.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        mWb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //使用自定义的WebViewClient
        mWb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                // forumContext.loadUrl("javascript:(" + readJS() + ")()");
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
        dialog = Utils.showLoadingDialog(context);
        dialog.show();
        RollPagerView.setAdapter(new LoopAdapter(RollPagerView));
        mWb.loadUrl(UrlUtils.BASE_URL + "danye/goods/id/" + id);
        goodsDetail();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopnow:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_shoucang:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                if ("0".equals(String.valueOf(goodsDetailBean.getGoods().getIs_coll()))) {
                    goodsCang();
                    mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc2));
                    EasyToast.showShort(context, "收藏成功");
                    goodsDetailBean.getGoods().setIs_coll(1);
                } else {
                    goodsOnCang();
                    mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc1));
                    EasyToast.showShort(context, "取消收藏");
                    goodsDetailBean.getGoods().setIs_coll(0);
                }
                break;
            case R.id.tv_addshop:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                cartJoinCart();
                break;
            case R.id.rl_shoppingcart:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                // startActivity(new Intent(context, ShopCarActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = (String) SpUtil.get(context, "uid", "");
        if (TextUtils.isEmpty(uid)) {
            return;
        }
    }

    /**
     * 产品详情获取
     */
    private void goodsDetail() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("id", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/details", "goods/details", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    dialog.dismiss();
                    goodsDetailBean = new Gson().fromJson(result, GoodsDetailBean.class);

                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                    EasyToast.showShort(context, R.string.Abnormalserver);
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                EasyToast.showShort(context, R.string.Abnormalserver);
            }
        });
    }

    /**
     * 收藏产品
     */
    private void goodsCang() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("key", UrlUtils.KEY);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/cang", "goods/cang", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    if ("307".equals(String.valueOf(goodsCangBean.getCode()))) {
                    } else {
                        goodsDetailBean.getGoods().setIs_coll(0);
                        mImgShouCang.setBackgroundResource(R.mipmap.new_sc1);
                        EasyToast.showShort(context, "收藏失败");
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
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
     * 取消收藏
     */
    private void goodsOnCang() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("key", UrlUtils.KEY);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/nocang", "goods/nocang", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    if ("310".equals(String.valueOf(goodsCangBean.getCode()))) {
                    } else {
                        goodsDetailBean.getGoods().setIs_coll(1);
                        mImgShouCang.setBackgroundResource(R.mipmap.new_sc2);
                        EasyToast.showShort(context, "取消失败");
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    dialog.dismiss();
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
     * 加入购物车
     */
    private void cartJoinCart() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("key", UrlUtils.KEY);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("number", "1");
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "cart/join_cart", "cart/join_cart", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("RegisterActivity", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                String s = mtvCountCart.getText().toString();
                int i = Integer.parseInt(s);
                mtvCountCart.setText(String.valueOf(i - 1));
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

