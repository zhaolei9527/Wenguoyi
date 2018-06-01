package com.wenguoyi.Activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.text.Html;
import android.text.Spanned;
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
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.hintview.IconHintView;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wenguoyi.Adapter.LoopAdapter;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CountCartBean;
import com.wenguoyi.Bean.GoodsCangBean;
import com.wenguoyi.Bean.GoodsDetailBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DensityUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.PixelUtils;
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
        App.getQueues().cancelAll("goods/detail");
        App.getQueues().cancelAll("goods/cang");
        App.getQueues().cancelAll("goods/oncang");
        App.getQueues().cancelAll("cart/join_cart");
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
        RollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        RollPagerView.setPlayDelay(30000);
//        ViewGroup.LayoutParams layoutParams = RollPagerView.getLayoutParams();
//        layoutParams.height = (int) (WindowUtil.getScreenWidth(context) * 0.5);
//        RollPagerView.setLayoutParams(layoutParams);
        mRlBack.setOnClickListener(this);
        mLlShoucang.setOnClickListener(this);
        mTvAddshop.setOnClickListener(this);
        mRlShoppingcart.setOnClickListener(this);
        mShopnow.setOnClickListener(this);
    }

    @Override
    protected void initData() {
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
        //dialog = Utils.showLoadingDialog(context);
        //dialog.show();
        RollPagerView.setAdapter(new LoopAdapter(RollPagerView));
        //mWb.loadUrl("http://www.baidu.com");
        //goodsDetailCache();
        //goodsDetail();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopnow:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    //  startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_shoucang:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    //  startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                if ("0".equals(String.valueOf(goodsDetailBean.getIs_cang()))) {
                    goodsCang();
                    mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc2));
                    EasyToast.showShort(context, "收藏成功");
                    goodsDetailBean.setIs_cang("1");
                } else {
                    goodsOnCang();
                    mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc1));
                    EasyToast.showShort(context, "取消收藏");
                    goodsDetailBean.setIs_cang("0");
                }
                break;
            case R.id.tv_addshop:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    // startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                String kucun = goodsDetailBean.getGoods().getKucun();
                int kucuni = Integer.parseInt(kucun);
                if (kucuni > 1) {
                    goodsDetailBean.getGoods().setKucun(String.valueOf(kucuni - 1));
                    dialog.show();
                    cartJoinCart();
                } else {
                    EasyToast.showShort(context, "库存不足");
                }
                break;
            case R.id.rl_shoppingcart:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    //   startActivity(new Intent(context, LoginActivity.class));
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
        //countCart();
    }

    /**
     * 购物车数量获取
     */
    private void countCart() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "cart/count_cart", "cart/count_cart", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CountCartBean countCartBean = new Gson().fromJson(result, CountCartBean.class);
                    if ("1".equals(String.valueOf(countCartBean.getStatus()))) {
                        mtvCountCart.setText(countCartBean.getRes());
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
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 产品缓存获取
     */
    private void goodsDetailCache() {
        String goodsDetail = (String) SpUtil.get(context, "goodsDetail" + String.valueOf(getIntent().getStringExtra("id")), "");
        if (!TextUtils.isEmpty(goodsDetail)) {
            try {
                GoodsDetailBean goodsDetailBean = new Gson().fromJson(goodsDetail, GoodsDetailBean.class);
                if ("310".equals(goodsDetailBean.getCode())) {
                    mTvTitle.setText(goodsDetailBean.getGoods().getTitle());
                    mTvPrice.setText("￥" + goodsDetailBean.getGoods().getPrice());
                    RollPagerView.setAdapter(new LoopAdapter(RollPagerView));
                    if ("0".equals(String.valueOf(goodsDetailBean.getIs_cang()))) {
                        mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc1));
                    } else {
                        mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc2));
                    }
                } else {
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
                goodsDetail = null;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }

    /**
     * 产品详情获取
     */
    private void goodsDetail() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("key", UrlUtils.KEY);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/detail", "goods/detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    goodsDetailBean = new Gson().fromJson(result, GoodsDetailBean.class);
                    if ("310".equals(goodsDetailBean.getCode())) {
                        SpUtil.putAndApply(context, "goodsDetail" + String.valueOf(getIntent().getStringExtra("id")), result);
                        mTvTitle.setText(goodsDetailBean.getGoods().getTitle());
                        mTvPrice.setText("￥" + goodsDetailBean.getGoods().getPrice());
                        RollPagerView.setAdapter(new LoopAdapter(RollPagerView));
                        Spanned spanned = Html.fromHtml(Utils.decode(goodsDetailBean.getGoods().getContent()));
                        Utils.inSetWebView(spanned.toString(), mWb, context);
                        if ("0".equals(String.valueOf(goodsDetailBean.getPj().getCount()))) {
                        } else {
                            String addtime = goodsDetailBean.getPj().getAddtime();
                            String xing = goodsDetailBean.getPj().getXing();
                            Double i = Double.parseDouble(xing);
                            for (int i1 = 0; i1 < 5; i1++) {
                                ImageView imageView = new ImageView(context);
                                imageView.setPadding(PixelUtils.dip2px(context, 3), 0, 0, 0);
                                if (i1 < i) {
                                    imageView.setBackgroundResource(R.mipmap.new_sc2);
                                } else {
                                    imageView.setBackgroundResource(R.mipmap.new_sc1);
                                }
                            }
                        }
                        if ("0".equals(String.valueOf(goodsDetailBean.getIs_cang()))) {
                            mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc1));
                        } else {
                            mImgShouCang.setBackground(getResources().getDrawable(R.mipmap.new_sc2));
                        }
                    } else {
                        Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
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
                        goodsDetailBean.setIs_cang("0");
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
                        goodsDetailBean.setIs_cang("1");
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
                    if ("323".equals(String.valueOf(goodsCangBean.getCode()))) {
                        EasyToast.showShort(context, "已成功加入购物车");
                        if (!"1".equals(String.valueOf(goodsCangBean.getIs_have()))) {
                            String s = mtvCountCart.getText().toString();
                            int i = Integer.parseInt(s);
                            mtvCountCart.setText(String.valueOf(i + 1));
                        }
                    } else if ("327".equals(String.valueOf(goodsCangBean.getCode()))) {
                        EasyToast.showShort(context, "加入失败，库存不足");
                        String s = mtvCountCart.getText().toString();
                        int i = Integer.parseInt(s);
                        mtvCountCart.setText(String.valueOf(i - 1));
                    } else {
                        EasyToast.showShort(context, "加入购物车失败");
                        String kucun = goodsDetailBean.getGoods().getKucun();
                        int kucuni = Integer.parseInt(kucun);
                        goodsDetailBean.getGoods().setKucun(String.valueOf(kucuni + 1));
                    }
                    goodsCangBean = null;
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

