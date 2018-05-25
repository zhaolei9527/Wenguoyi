package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.OrderDetailBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DateUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;


public class MyOrderDetailsActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout rl_back;
    private RelativeLayout rl_title;
    private ImageView img_dizhi;
    private TextView tv_check_address;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_dizhi;
    private TextView tv_add_dizhi;
    private RelativeLayout rl_change_dizhi;
    private LinearLayout ll_orders;
    private TextView tv_brand_price;
    private TextView tv_yunfei;
    private TextView tv_price_total;
    private TextView tv_jifen;
    private TextView tv_yue;
    private ImageView img_weixin;
    private TextView tv_weixinpay;
    private RelativeLayout rl_weixinpay;
    private ImageView img_alipay;
    private TextView tv_alipay;
    private RelativeLayout rl_alipay;
    private Button btn_pay_order;
    private TextView textView3;
    private TextView tv_fanjifen;
    private TextView tv_bianhao;
    private TextView tv_order_time;
    private String orderid;
    private String order;
    private Dialog dialog;
    private ImageView img_checkaddress;
    private RelativeLayout rl_jifen;
    private RelativeLayout rl_yue;
    private Button btn_delete_order;
    private Button btn_delete_order_info;
    private TextView tv_order_exp;
    private TextView tv_order_expnum;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("order/detail");
        System.gc();
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_my_order_details;
    }

    @Override
    protected void initview() {
        initView();
        orderid = getIntent().getStringExtra("orderid");
        order = getIntent().getStringExtra("order");
    }

    @Override
    protected void initListener() {
        rl_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        isf = true;
    }

    private void initView() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        img_dizhi = (ImageView) findViewById(R.id.img_dizhi);
        tv_check_address = (TextView) findViewById(R.id.tv_check_address);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_dizhi = (TextView) findViewById(R.id.tv_dizhi);
        tv_add_dizhi = (TextView) findViewById(R.id.tv_add_dizhi);
        rl_change_dizhi = (RelativeLayout) findViewById(R.id.rl_change_dizhi);
        ll_orders = (LinearLayout) findViewById(R.id.ll_orders);
        tv_brand_price = (TextView) findViewById(R.id.tv_brand_price);
        tv_yunfei = (TextView) findViewById(R.id.tv_yunfei);
        tv_price_total = (TextView) findViewById(R.id.tv_price_total);
        tv_jifen = (TextView) findViewById(R.id.tv_jifen);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        img_weixin = (ImageView) findViewById(R.id.img_weixin);
        tv_weixinpay = (TextView) findViewById(R.id.tv_weixinpay);
        rl_weixinpay = (RelativeLayout) findViewById(R.id.rl_weixinpay);
        img_alipay = (ImageView) findViewById(R.id.img_alipay);
        tv_alipay = (TextView) findViewById(R.id.tv_alipay);
        rl_alipay = (RelativeLayout) findViewById(R.id.rl_alipay);
        btn_pay_order = (Button) findViewById(R.id.btn_pay_order);
        btn_delete_order = (Button) findViewById(R.id.btn_delete_order);
        btn_delete_order_info = (Button) findViewById(R.id.btn_delete_order_info);

        btn_pay_order.setVisibility(View.GONE);
        btn_delete_order.setVisibility(View.GONE);
        btn_delete_order_info.setVisibility(View.GONE);

        textView3 = (TextView) findViewById(R.id.textView3);
        tv_fanjifen = (TextView) findViewById(R.id.tv_fanjifen);
        tv_bianhao = (TextView) findViewById(R.id.tv_bianhao);
        tv_order_time = (TextView) findViewById(R.id.tv_order_time);
        img_checkaddress = (ImageView) findViewById(R.id.img_checkaddress);
        img_checkaddress.setVisibility(View.GONE);
        rl_jifen = (RelativeLayout) findViewById(R.id.rl_jifen);
        rl_yue = (RelativeLayout) findViewById(R.id.rl_yue);
        tv_order_exp = (TextView) findViewById(R.id.tv_order_exp);
        tv_order_expnum = (TextView) findViewById(R.id.tv_order_expnum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * 订单详情获取
     */
    private void orderDetail() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", orderid);
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/detail", "order/detail", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("RegisterActivity", result);
                try {
                    final OrderDetailBean orderDetailBean = new Gson().fromJson(result, OrderDetailBean.class);
                    tv_name.setText(orderDetailBean.getOrder().getName());
                    tv_phone.setText(orderDetailBean.getOrder().getTel());
                    tv_dizhi.setText(orderDetailBean.getOrder().getSheng() + orderDetailBean.getOrder().getShi() + orderDetailBean.getOrder().getXian() + orderDetailBean.getOrder().getAddress());

                    if (!TextUtils.isEmpty(orderDetailBean.getOrder().getExp()) && !TextUtils.isEmpty(orderDetailBean.getOrder().getExpnum())) {
                        tv_order_exp.setText("快递公司：" + orderDetailBean.getOrder().getExp());
                        tv_order_expnum.setText("快递单号：" + orderDetailBean.getOrder().getExpnum());
                    }

                    if (TextUtils.isEmpty(orderDetailBean.getOrder().getTotalprice())) {
                        tv_brand_price.setText("￥0.00");
                    } else {
                        tv_brand_price.setText("￥" + orderDetailBean.getOrder().getTotalprice());
                    }
                    tv_yunfei.setText("￥" + orderDetailBean.getOrder().getYunfei());
                    if ("1".equals(String.valueOf(orderDetailBean.getGoods().get(0).getType()))) {
                        double v = Double.parseDouble(orderDetailBean.getOrder().getTotalprice()) + Double.parseDouble(orderDetailBean.getOrder().getYunfei());
                        tv_price_total.setText("￥" + String.valueOf(v));
                    } else {
                        if (TextUtils.isEmpty(orderDetailBean.getOrder().getTotalprice())) {
                            tv_price_total.setText("￥0.00");
                        } else {
                            tv_price_total.setText("￥" + orderDetailBean.getOrder().getTotalprice());
                        }
                    }
                    String payment = orderDetailBean.getOrder().getPayment();
                    if ("1".equals(payment)) {
                        rl_alipay.setVisibility(View.VISIBLE);
                        tv_alipay.setText("￥" + orderDetailBean.getOrder().getSfmoney());
                    } else if ("2".equals(payment)) {
                        rl_weixinpay.setVisibility(View.VISIBLE);
                        tv_weixinpay.setText("￥" + orderDetailBean.getOrder().getSfmoney());
                    }
                    String paytype = orderDetailBean.getOrder().getPaytype();
                    if ("1".equals(paytype)) {
                        rl_yue.setVisibility(View.VISIBLE);
                        tv_yue.setText("￥" + orderDetailBean.getOrder().getYemoney());
                    } else if ("2".equals(paytype)) {
                        rl_jifen.setVisibility(View.GONE);
                        tv_jifen.setText(orderDetailBean.getOrder().getScore());
                    }
                    if (TextUtils.isEmpty(orderDetailBean.getOrder().getFh_jifen())) {
                        tv_fanjifen.setText("0");
                    } else {
                        tv_fanjifen.setText(orderDetailBean.getOrder().getFh_jifen());
                    }
                    tv_bianhao.setText("订单编号：" + orderDetailBean.getOrder().getOrderid());
                    tv_order_time.setText("下单时间：" + DateUtils.getMillon(Long.parseLong(orderDetailBean.getOrder().getAddtime()) * 1000));
                    String stu = orderDetailBean.getOrder().getStu();

                    for (int i = 0; i < orderDetailBean.getGoods().size(); i++) {
                        final View item_oreder_details_layout = View.inflate(context, R.layout.item_oreder_details_layout, null);
                        item_oreder_details_layout.setTag(orderDetailBean.getGoods().get(i).getGid());
                        SimpleDraweeView SimpleDraweeView = (com.facebook.drawee.view.SimpleDraweeView) item_oreder_details_layout.findViewById(R.id.SimpleDraweeView);
                        SimpleDraweeView.setImageURI(UrlUtils.URL + orderDetailBean.getGoods().get(i).getImg());
                        final TextView tv_title = (TextView) item_oreder_details_layout.findViewById(R.id.tv_title);
                        tv_title.setText(orderDetailBean.getGoods().get(i).getTitle());
                        TextView tv_classify = (TextView) item_oreder_details_layout.findViewById(R.id.tv_classify);
                        tv_classify.setText("￥" + orderDetailBean.getGoods().get(i).getPrice());
                        TextView tv_size = (TextView) item_oreder_details_layout.findViewById(R.id.tv_size);
                        tv_size.setText("×" + orderDetailBean.getGoods().get(i).getNumber());
                        TextView tv_type = (TextView) item_oreder_details_layout.findViewById(R.id.tv_type);
                        final Button btn_isget_order = (Button) item_oreder_details_layout.findViewById(R.id.btn_isget_order);
                        btn_isget_order.setTag(orderDetailBean.getGoods().get(i).getId());
                        String type = orderDetailBean.getGoods().get(i).getType();
                        item_oreder_details_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, PriceDetailsActivity.class);
                                int tag = Integer.parseInt(item_oreder_details_layout.getTag().toString());
                                intent.putExtra("id", String.valueOf(tag));
                                startActivity(intent);
                            }
                        });
                        ll_orders.addView(item_oreder_details_layout);
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

    public static boolean isf = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isf) {
            boolean connected = Utils.isConnected(context);
            if (connected) {
                dialog = Utils.showLoadingDialog(context);
                dialog.show();
                orderDetail();
            } else {
                EasyToast.showShort(context, "网络未连接");
                finish();
            }
            isf = !isf;
        }
    }
}
