package com.wenguoyi.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Activity.OrderActivity;
import com.wenguoyi.Activity.PriceDetailsActivity;
import com.wenguoyi.Adapter.ShopCarListAdapter;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.OrderOrderBean;
import com.wenguoyi.Bean.SuckleCartBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class ShopCarFragment extends BaseLazyFragment {
    private Context context;
    private TextView tv_bianji;
    private WenguoyiRecycleView rv_purchaserecord;
    private CheckBox btnIsChoosed;
    private TextView shopnow;
    private TextView tv_money;
    private SakuraLinearLayoutManager line;
    private int p = 1;
    private ShopCarListAdapter shopCarListAdapter;
    private RelativeLayout rl_buy;
    private Button btn_delete;
    private RelativeLayout rl_bianji;
    private boolean isbianji = false;
    private CheckBox btnChoosed;
    private RelativeLayout ll_empty;
    private LinearLayout ll_content;
    private BroadcastReceiver receiver;
    private BroadcastReceiver broadcastReceiver;
    private BroadcastReceiver broadcastReceiver2;


    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {

        /**
         * 编辑
         * */
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                rl_bianji.setVisibility(View.VISIBLE);
                rl_buy.setVisibility(View.GONE);
            }
        };

        /**
         * 完成
         * */
        broadcastReceiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                rl_bianji.setVisibility(View.GONE);
                rl_buy.setVisibility(View.VISIBLE);
            }
        };

        /**
         * 下单
         * */
        shopnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder id = new StringBuilder();
                StringBuilder cid = new StringBuilder();
                StringBuilder amount = new StringBuilder();

                for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                    if (shopCarListAdapter.getDatas().get(i).isCheck()) {
                        if (id.length() == 0) {
                            id.append(shopCarListAdapter.getDatas().get(i).getGid());
                            cid.append(shopCarListAdapter.getDatas().get(i).getId());
                            amount.append(shopCarListAdapter.getDatas().get(i).getNum());
                        } else {
                            id.append("," + shopCarListAdapter.getDatas().get(i).getGid());
                            cid.append("," + shopCarListAdapter.getDatas().get(i).getId());
                            amount.append("," + shopCarListAdapter.getDatas().get(i).getNum());
                        }
                    }
                }

                if (!TextUtils.isEmpty(id.toString())) {
                    orderOrder(id.toString(), cid.toString(), amount.toString());
                } else {
                    EasyToast.showShort(context, "请选择商品");
                }
            }
        });


        context.registerReceiver(this.broadcastReceiver, new IntentFilter("gouwuchebianji"));
        context.registerReceiver(this.broadcastReceiver2, new IntentFilter("gouwuchebianjiwancheng"));

    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.shop_car_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ll_empty = (RelativeLayout) view.findViewById(R.id.LL_empty);
        ll_content = (LinearLayout) view.findViewById(R.id.ll_content);
        rl_buy = (RelativeLayout) view.findViewById(R.id.rl_buy);
        rl_bianji = (RelativeLayout) view.findViewById(R.id.rl_bianji);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        tv_bianji = (TextView) view.findViewById(R.id.tv_bianji);
        rv_purchaserecord = (WenguoyiRecycleView) view.findViewById(R.id.rv_purchaserecord);
        btnIsChoosed = (CheckBox) view.findViewById(R.id.btnIsChoosed);
        btnChoosed = (CheckBox) view.findViewById(R.id.btnChoosed);
        shopnow = (TextView) view.findViewById(R.id.shopnow);
        tv_money = (TextView) view.findViewById(R.id.tv_money);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_purchaserecord.setLayoutManager(line);
        rv_purchaserecord.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_purchaserecord.setFootLoadingView(progressView);
        TextView textView = new TextView(context);
        textView.setText("-没有更多了-");
        rv_purchaserecord.setFootEndView(textView);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean choosed = intent.getBooleanExtra("Choosed", false);
                if (choosed) {
                    btnIsChoosed.setChecked(true);
                    btnChoosed.setChecked(true);
                } else {
                    btnIsChoosed.setChecked(false);
                    btnChoosed.setChecked(false);
                }
            }
        };

        mContext.registerReceiver(receiver, new IntentFilter("shopCarChoosedAll"));

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                    if (shopCarListAdapter.getDatas().get(i).isCheck()) {
                        if (i == 0) {
                            stringBuilder.append(shopCarListAdapter.getDatas().get(i).getId());
                        } else {
                            stringBuilder.append("," + shopCarListAdapter.getDatas().get(i).getId());
                        }
                    }
                }
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    suckleCartDel(stringBuilder.toString());
                } else {
                    EasyToast.showShort(context, "请选择商品");
                }
            }
        });


        btnChoosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnChoosed.isChecked()) {
                    for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                        shopCarListAdapter.getDatas().get(i).setCheck(false);
                    }
                    shopCarListAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                        shopCarListAdapter.getDatas().get(i).setCheck(true);
                    }
                    shopCarListAdapter.notifyDataSetChanged();
                }
            }
        });

        btnIsChoosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnIsChoosed.isChecked()) {
                    for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                        shopCarListAdapter.getDatas().get(i).setCheck(false);
                    }
                    shopCarListAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < shopCarListAdapter.getDatas().size(); i++) {
                        shopCarListAdapter.getDatas().get(i).setCheck(true);
                    }
                    shopCarListAdapter.notifyDataSetChanged();
                }
            }
        });


        String shopCarActivity = (String) SpUtil.get(context, "ShopCarActivity", "");
        if (!TextUtils.isEmpty(shopCarActivity)) {
            SuckleCartBean suckleCartBean = new Gson().fromJson(shopCarActivity, SuckleCartBean.class);
            ll_empty.setVisibility(View.GONE);
            if (rv_purchaserecord != null) {
                rv_purchaserecord.setEnabled(true);
                rv_purchaserecord.loadMoreComplete();
            }
            if (p == 1) {
                shopCarListAdapter = new ShopCarListAdapter(suckleCartBean.getCart(), context, tv_money);
                rv_purchaserecord.setAdapter(shopCarListAdapter);
                rv_purchaserecord.setCanloadMore(false);
                rv_purchaserecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, PriceDetailsActivity.class);
                        intent.putExtra("id", shopCarListAdapter.getDatas().get(position).getGid());
                        startActivity(intent);
                    }
                });
            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(receiver);
        mContext.unregisterReceiver(broadcastReceiver);
        mContext.unregisterReceiver(broadcastReceiver2);
    }

    public void getData() {
        btnChoosed.setChecked(false);
        btnIsChoosed.setChecked(false);
        if (TextUtils.isEmpty(String.valueOf(SpUtil.get(context, "uid", "")))) {
            return;
        }
        suckleCart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    /**
     * 购物车获取
     */
    private void suckleCart() {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("ShopCarActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/read_cart", "goods/read_cart", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("ShopCarActivity", result);
                try {
                    SuckleCartBean suckleCartBean = new Gson().fromJson(result, SuckleCartBean.class);
                    tv_money.setText("￥0.00");
                    if ("1".equals(String.valueOf(suckleCartBean.getStatus()))) {
                        SpUtil.putAndApply(context, "ShopCarActivity", result);
                        ll_empty.setVisibility(View.GONE);
                        rl_buy.setVisibility(View.VISIBLE);
                        rl_bianji.setVisibility(View.GONE);
                        if (rv_purchaserecord != null) {
                            rv_purchaserecord.setEnabled(true);
                            rv_purchaserecord.loadMoreComplete();
                        }
                        if (p == 1) {
                            shopCarListAdapter = new ShopCarListAdapter(suckleCartBean.getCart(), context, tv_money);
                            rv_purchaserecord.setAdapter(shopCarListAdapter);
                            rv_purchaserecord.setCanloadMore(false);
                            rv_purchaserecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(context, PriceDetailsActivity.class);
                                    intent.putExtra("id", shopCarListAdapter.getDatas().get(position).getGid());
                                    startActivity(intent);
                                }
                            });
                        } else {
                            // shopCarListAdapter.setDatas((ArrayList<SuckleCartBean.ResBean>) suckleCartBean.getRes());
                        }
                    } else {
                        ll_empty.setVisibility(View.VISIBLE);
                        rl_buy.setVisibility(View.GONE);
                        rl_bianji.setVisibility(View.GONE);
                        SpUtil.putAndApply(context, "ShopCarActivity", "");
                    }
                    suckleCartBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                ll_empty.setVisibility(View.VISIBLE);
                error.printStackTrace();
            }
        });
    }

    /**
     * 清除商品
     */
    private void suckleCartDel(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", id);
        Log.e("RegisterActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/cartdel", "goods/cartdel", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {
                        EasyToast.showShort(context, "删除成功");
                        mContext.sendBroadcast(new Intent("indexCatr"));
                        suckleCart();
                    } else {
                        EasyToast.showShort(context, "删除失败");
                    }
                    result = null;
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

    /**
     * 购物车下单
     */
    private void orderOrder(String id, final String cid, final String amount) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", id);
        params.put("cid", cid);
        params.put("amount", amount);
        Log.e("ShopCarFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "order/order", "order/order", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("ShopCarFragment", result);
                try {
                    OrderOrderBean orderOrderBean = new Gson().fromJson(result, OrderOrderBean.class);
                    if (1 == orderOrderBean.getStatus()) {
                        context.startActivity(new Intent(context, OrderActivity.class)
                                .putExtra("order", result)
                                .putExtra("cart_id", cid)
                                .putExtra("amount_list", amount)
                        );
                    } else {
                    }
                    result = null;
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
}
