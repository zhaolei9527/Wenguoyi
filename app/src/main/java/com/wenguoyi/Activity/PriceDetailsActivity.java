package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jude.rollviewpager.hintview.IconHintView;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wenguoyi.Adapter.GoodLoopAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.GoodsCangBean;
import com.wenguoyi.Bean.GoodsChangePriceBean;
import com.wenguoyi.Bean.GoodsDetailBean;
import com.wenguoyi.Bean.OrderBuyBean;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wenguoyi.R.id.btn_jia;
import static com.wenguoyi.R.id.btn_shuliang;

public class PriceDetailsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_countCart)
    TextView tvCountCart;
    @BindView(R.id.rl_shoppingcart)
    RelativeLayout rlShoppingcart;
    @BindView(R.id.RollPagerView)
    com.jude.rollviewpager.RollPagerView RollPagerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.wb)
    WebView wb;
    @BindView(R.id.ll_gohome)
    LinearLayout llGohome;
    @BindView(R.id.img_shoucang)
    ImageView imgShoucang;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.tv_addshop)
    TextView tvAddshop;
    @BindView(R.id.shopnow)
    TextView shopnow;
    @BindView(R.id.tv_type_title)
    TextView tvTypeTitle;
    @BindView(R.id.tv_type_money)
    TextView tvTypeMoney;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.btn_jian)
    Button btnJian;
    @BindView(btn_shuliang)
    TextView btnShuliang;
    @BindView(btn_jia)
    Button btnJia;
    @BindView(R.id.ll_checkmax)
    LinearLayout llCheckmax;
    @BindView(R.id.ll_typecontent)
    LinearLayout llTypecontent;
    @BindView(R.id.tv_type_addshop)
    TextView tvTypeAddshop;
    @BindView(R.id.type_shopnow)
    TextView typeShopnow;
    @BindView(R.id.ll_type_check)
    LinearLayout llTypeCheck;
    @BindView(R.id.tv_otherPrice)
    TextView tvOtherPrice;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    private Dialog dialog;
    private String uid;
    private GoodsDetailBean goodsDetailBean;
    private LayoutInflater mInflater;
    private HashMap<String, String> typeMap;

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
        uid = (String) SpUtil.get(context, "uid", "");
        mInflater = LayoutInflater.from(this);
    }

    @Override
    protected void initListener() {
        RollPagerView.setHintView(new IconHintView(context, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(context, getResources().getDimension(R.dimen.x7))));
        RollPagerView.setPlayDelay(30000);
        rlBack.setOnClickListener(this);
        llShoucang.setOnClickListener(this);
        rlShoppingcart.setOnClickListener(this);
        shopnow.setOnClickListener(this);
        tvAddshop.setOnClickListener(this);
        llGohome.setOnClickListener(this);
        typeShopnow.setOnClickListener(this);
        tvTypeAddshop.setOnClickListener(this);

        btnJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(btnShuliang.getText().toString());
                i = i + 1;
                btnShuliang.setText(String.valueOf(i));
            }
        });

        btnJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(btnShuliang.getText().toString());
                if (i > 1) {
                    i = i - 1;
                }
                btnShuliang.setText(String.valueOf(i));
            }
        });

    }

    @Override
    protected void initData() {

        String id = getIntent().getStringExtra("id");

        if (TextUtils.isEmpty(id)) {
            EasyToast.showShort(context, R.string.hasError);
            finish();
        }

        // 开启 localStorage
        wb.getSettings().setDomStorageEnabled(true);
        // 设置支持javascript
        wb.getSettings().setJavaScriptEnabled(true);
        // 启动缓存
        wb.getSettings().setAppCacheEnabled(true);
        // 设置缓存模式
        wb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //使用自定义的WebViewClient
        wb.setWebViewClient(new WebViewClient() {
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
        wb.loadUrl(UrlUtils.BASE_URL + "danye/goods/id/" + id);
        goodsDetail();
    }


    private boolean typeshow = false;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopnow:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                typeshow = true;
                llTypeCheck.setVisibility(View.VISIBLE);


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
                //  if ("0".equals(String.valueOf(goodsDetailBean.getGoods().getIs_coll()))) {
                goodsCang();
                imgShoucang.setBackground(getResources().getDrawable(R.mipmap.new_sc2));
                EasyToast.showShort(context, "收藏成功");
                goodsDetailBean.getGoods().setIs_coll(1);
                //} else {
                //  goodsOnCang();
                // imgShoucang.setBackground(getResources().getDrawable(R.mipmap.new_sc1));
                // EasyToast.showShort(context, "取消收藏");
                // goodsDetailBean.getGoods().setIs_coll(0);
                //}
                break;
            case R.id.tv_addshop:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                typeshow = true;
                llTypeCheck.setVisibility(View.VISIBLE);
//                cartJoinCart();
                break;
            case R.id.rl_shoppingcart:
                if (TextUtils.isEmpty(uid)) {
                    EasyToast.showShort(context, "请先登录");
                    startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(context, MyShopCarActivity.class));
                break;
            case R.id.ll_gohome:
                finish();
                break;
            case R.id.type_shopnow:
                if (Utils.isConnected(context)) {
                    if (typeMap != null) {
                        if (typeMap.containsValue("!!!")) {
                            EasyToast.showShort(context, "请选择商品规格");
                            break;
                        }
                    }
                    dialog.show();
                    orderBuy();
                } else {
                    EasyToast.showShort(context, R.string.Networkexception);
                }
                break;
            case R.id.tv_type_addshop:
                if (Utils.isConnected(context)) {
                    if (typeMap != null) {
                        if (typeMap.containsValue("!!!")) {
                            EasyToast.showShort(context, "请选择商品规格");
                            break;
                        }
                    }
                    dialog.show();
                    cartJoinCart();
                } else {
                    EasyToast.showShort(context, R.string.Networkexception);
                }
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
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/details", "goods/details", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    dialog.dismiss();
                    goodsDetailBean = new Gson().fromJson(result, GoodsDetailBean.class);
                    RollPagerView.setAdapter(new GoodLoopAdapter(RollPagerView, goodsDetailBean.getImgs()));
                    tvTitle.setText(goodsDetailBean.getGoods().getGname());
                    tvTypeTitle.setText(goodsDetailBean.getGoods().getGname());
                    tvTypeMoney.setText(goodsDetailBean.getGoods().getPrice());
                    SimpleDraweeView.setImageURI(UrlUtils.URL + goodsDetailBean.getImgs().get(0));

                    if (1 == goodsDetailBean.getGoods().getIs_coll()) {
                        imgShoucang.setBackground(getResources().getDrawable(R.mipmap.new_sc2));
                    }

                    /**
                     字段usertype：
                     1：当前价格为零售价，不显示其他价格
                     2：当前价格为批发价，在价格下方展示零售价（price1）
                     3：当前价格为代理价，在价格下方展示零售价（price1）、批发价（price2）
                     4和5：当前价格为加盟价，在价格下方展示零售价（price1）、批发价（price2）、代理价（price3）
                     * */
                    if ("1".equals(String.valueOf(goodsDetailBean.getUsertype()))) {
                        tvPrice.setText("零售价：" + goodsDetailBean.getGoods().getPrice());
                        tvOtherPrice.setVisibility(View.GONE);
                    } else if ("2".equals(String.valueOf(goodsDetailBean.getUsertype()))) {
                        tvPrice.setText("批发价：" + goodsDetailBean.getGoods().getPrice());
                        tvOtherPrice.setText("零售价：" + goodsDetailBean.getGoods().getPrice1());
                    } else if ("3".equals(String.valueOf(goodsDetailBean.getUsertype()))) {
                        tvPrice.setText("代理价：" + goodsDetailBean.getGoods().getPrice());
                        tvOtherPrice.setText("零售价:" + goodsDetailBean.getGoods().getPrice1() + "  批发价：" + goodsDetailBean.getGoods().getPrice2());
                    } else if ("4".equals(String.valueOf(goodsDetailBean.getUsertype()))) {
                        tvPrice.setText("代理价：" + goodsDetailBean.getGoods().getPrice());
                        tvOtherPrice.setText("零售价：" + goodsDetailBean.getGoods().getPrice1() + "  批发价：" + goodsDetailBean.getGoods().getPrice2() + "  代理价：" + goodsDetailBean.getGoods().getPrice3());
                    } else if ("5".equals(String.valueOf(goodsDetailBean.getUsertype()))) {
                        tvPrice.setText("代理价：" + goodsDetailBean.getGoods().getPrice());
                        tvOtherPrice.setText("零售价：" + goodsDetailBean.getGoods().getPrice1() + "  批发价：" + goodsDetailBean.getGoods().getPrice2() + "  代理价：" + goodsDetailBean.getGoods().getPrice3());
                    }

                    /**
                     * 规格标签
                     */
                    if ("1".equals(goodsDetailBean.getGoods().getIs_norm())) {
                        typeMap = new HashMap<>();
                        for (int i = 0; i < goodsDetailBean.getNormname().size(); i++) {
                            View goodtype_layout = View.inflate(context, R.layout.good_details_goodtype_layout, null);
                            TextView tv_type_title = goodtype_layout.findViewById(R.id.tv_type_title);
                            tv_type_title.setText(goodsDetailBean.getNormname().get(i).getTitle());
                            final FlowLayout mFlowLayout = goodtype_layout.findViewById(R.id.id_flowlayout);
                            typeMap.put(String.valueOf(i), "!!!");
                            for (int j = 0; j < goodsDetailBean.getNormname().get(i).getTerm().size(); j++) {
                                final CheckedTextView tv = (CheckedTextView) mInflater.inflate(
                                        R.layout.label_tv_keshi_layout, mFlowLayout, false);
                                tv.setText(goodsDetailBean.getNormname().get(i).getTerm().get(j).getTitle());
                                tv.setTag(goodsDetailBean.getNormname().get(i).getTerm().get(j).getTid());
                                final String str = tv.getText().toString();
                                //点击事件
                                final int finalI = i;
                                final int finalJ = j;
                                tv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        for (int i1 = 0; i1 < mFlowLayout.getChildCount(); i1++) {
                                            CheckedTextView childAt = (CheckedTextView) mFlowLayout.getChildAt(i1);
                                            if (tv.getTag().equals(childAt.getTag())) {
                                                typeMap.put(String.valueOf(finalI), String.valueOf(childAt.getTag()));
                                                childAt.setChecked(true);
                                                childAt.setBackground(getResources().getDrawable(R.drawable.bg_keshi_lable_check));
                                            } else {
                                                childAt.setChecked(false);
                                                childAt.setBackground(getResources().getDrawable(R.drawable.bg_keshi_lable_nocheck));
                                            }
                                        }
                                        if (!typeMap.containsValue("!!!")) {
                                            StringBuilder stringBuilder = new StringBuilder();
                                            for (int i1 = 0; i1 < typeMap.size(); i1++) {
                                                if (i1 == 0) {
                                                    stringBuilder.append(typeMap.get(String.valueOf(i1)));
                                                } else {
                                                    stringBuilder.append("|" + typeMap.get(String.valueOf(i1)));
                                                }
                                            }

                                            if (Utils.isConnected(context)) {
                                                getypeprice(stringBuilder.toString());
                                            } else {
                                                EasyToast.showShort(context, R.string.Networkexception);
                                            }

                                        }
                                    }
                                });
                                mFlowLayout.addView(tv);//添加到父View
                            }
                            llTypecontent.addView(goodtype_layout);
                        }
                    }
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
     * 获取商品规格价格
     */
    private void getypeprice(String str) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("str", str);
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/change_price", "goods/change_price", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    GoodsChangePriceBean goodsChangePriceBean = new Gson().fromJson(result, GoodsChangePriceBean.class);
                    if (1 == goodsChangePriceBean.getStatus()) {
                        tvKucun.setText("库存:" + goodsChangePriceBean.getNums());
                        tvTypeMoney.setText("￥" + goodsChangePriceBean.getPrice());
                    }
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
        params.put("pwd", UrlUtils.KEY);
        params.put("id", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/coll", "goods/coll", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {

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
                        imgShoucang.setBackgroundResource(R.mipmap.new_sc2);
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
        params.put("pwd", UrlUtils.KEY);
        params.put("gid", String.valueOf(getIntent().getStringExtra("id")));
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("gnum", btnShuliang.getText().toString());
        if ("1".equals(goodsDetailBean.getGoods().getIs_norm())) {
            params.put("is_norm", "1");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i1 = 0; i1 < typeMap.size(); i1++) {
                if (i1 == 0) {
                    stringBuilder.append(typeMap.get(String.valueOf(i1)));
                } else {
                    stringBuilder.append("|" + typeMap.get(String.valueOf(i1)));
                }
            }
            params.put("str", stringBuilder.toString());
        } else {
            params.put("is_norm", "-1");
        }
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/cart", "goods/cart", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    dialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        llTypeCheck.setVisibility(View.GONE);
                    }
                    EasyToast.showShort(context, codeBean.getMsg());
                    codeBean = null;
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
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 确认订单（立即购买）
     */
    private void orderBuy() {
        final HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", String.valueOf(getIntent().getStringExtra("id")));
        params.put("amount", btnShuliang.getText().toString());
        if ("1".equals(goodsDetailBean.getGoods().getIs_norm())) {
            params.put("is_norm", "1");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i1 = 0; i1 < typeMap.size(); i1++) {
                if (i1 == 0) {
                    stringBuilder.append(typeMap.get(String.valueOf(i1)));
                } else {
                    stringBuilder.append("|" + typeMap.get(String.valueOf(i1)));
                }
            }
            params.put("normstr", stringBuilder.toString());
        } else {
            params.put("is_norm", "-1");
        }
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/buy", "order/buy", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("PriceDetailsActivity", result);
                try {
                    dialog.dismiss();
                    OrderBuyBean orderBuyBean = new Gson().fromJson(result, OrderBuyBean.class);
                    if (1 == orderBuyBean.getStatus()) {
                        context.startActivity(new Intent(context, OrderBuyActivity.class)
                                .putExtra("order", result)
                                .putExtra("gid", String.valueOf(getIntent().getStringExtra("id")))
                                .putExtra("is_norm", params.get("is_norm"))
                                .putExtra("norm", params.get("normstr"))
                                .putExtra("amount", params.get("amount"))
                                .putExtra("val", orderBuyBean.getGoods().getVal())
                        );
                        finish();
                    } else {
                        EasyToast.showShort(context, R.string.Abnormalserver);
                    }
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


    @Override
    public void onBackPressed() {
        if (typeshow) {
            llTypeCheck.setVisibility(View.GONE);
            typeshow = false;
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

