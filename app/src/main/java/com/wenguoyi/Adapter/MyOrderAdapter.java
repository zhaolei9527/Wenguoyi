package com.wenguoyi.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wenguoyi.Activity.MyOrderDetailsActivity;
import com.wenguoyi.Activity.PayActivity;
import com.wenguoyi.Bean.OrderCancelBean;
import com.wenguoyi.Bean.OrderListsBean;
import com.wenguoyi.Bean.SuckleCartDelBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DateUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.CommomDialog;
import com.wenguoyi.View.OrderContentFrameLayout;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<OrderListsBean.ResBean> datas = new ArrayList();

    public ArrayList<OrderListsBean.ResBean> getDatas() {
        return datas;
    }

    public OrderContentFrameLayout Ordercontentframelayout;

    RelativeLayout ll_empty;

    public MyOrderAdapter(List<OrderListsBean.ResBean> list, Context context, OrderContentFrameLayout OrderContentFrameLayout, RelativeLayout ll_empty) {
        this.datas = (ArrayList<OrderListsBean.ResBean>) list;
        this.mContext = context;
        this.Ordercontentframelayout = OrderContentFrameLayout;
        this.ll_empty = ll_empty;
    }

    public void setDatas(ArrayList<OrderListsBean.ResBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_form_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_order_form_time.setText(DateUtils.getMillon(Long.parseLong(datas.get(position).getAddtime()) * 1000));
        final String stu = datas.get(position).getStu();
        if ("1".equals(stu)) {
            holder.tv_order_type.setText("待付款");
            holder.btn_pay_order.setVisibility(View.VISIBLE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.VISIBLE);
            holder.btn_shanchu_order.setVisibility(View.GONE);
            holder.btn_change_order.setVisibility(View.GONE);
        } else if ("2".equals(stu)) {
            holder.tv_order_type.setText("待发货");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_shanchu_order.setVisibility(View.GONE);
            holder.btn_change_order.setVisibility(View.VISIBLE);
            holder.btn_change_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MyOrderDetailsActivity.class)
                            .putExtra("order", datas.get(position).getOrderid())
                            .putExtra("orderid", datas.get(position).getId())
                    );
                }
            });
        } else if ("3".equals(stu)) {
            holder.btn_pay_order.setVisibility(View.GONE);
            if ("1".equals(String.valueOf(datas.get(position).getShops().get(0).getType()))) {
                holder.btn_isget_order.setVisibility(View.VISIBLE);
                holder.tv_order_type.setText("待收货");
            } else {
                holder.btn_isget_order.setVisibility(View.GONE);
                holder.tv_order_type.setText("待消费");
            }
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_shanchu_order.setVisibility(View.GONE);
            holder.btn_change_order.setVisibility(View.GONE);
        } else if ("4".equals(stu)) {
            holder.tv_order_type.setText("待评价");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_shanchu_order.setVisibility(View.GONE);
            holder.btn_change_order.setVisibility(View.GONE);
        } else if ("5".equals(stu)) {
            holder.tv_order_type.setText("已完成");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_shanchu_order.setVisibility(View.GONE);
            holder.btn_change_order.setVisibility(View.GONE);
        } else if ("-1".equals(stu)) {
            holder.tv_order_type.setText("已取消");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_shanchu_order.setVisibility(View.VISIBLE);
            holder.btn_change_order.setVisibility(View.GONE);
        } else if ("99".equals(stu)) {
            holder.tv_order_type.setText("退换货");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_shanchu_order.setVisibility(View.GONE);
            holder.btn_change_order.setVisibility(View.GONE);
        }

        double v = Double.parseDouble(datas.get(position).getTotalprice()) + Double.parseDouble(datas.get(position).getYunfei());
        holder.tv_order_content.setText("共" + datas.get(position).getNumber() + "件商品 合计:￥" + String.valueOf(v) + "(含运费￥" + datas.get(position).getYunfei() + ")");
        holder.ll_oreders.removeAllViews();
        for (int i = 0; i < datas.get(position).getShops().size(); i++) {
            View item_oreder_layout = View.inflate(mContext, R.layout.item_oreder_layout, null);
            SimpleDraweeView SimpleDraweeView = (com.facebook.drawee.view.SimpleDraweeView) item_oreder_layout.findViewById(R.id.SimpleDraweeView);
            SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getShops().get(i).getImg());
            TextView tv_title = (TextView) item_oreder_layout.findViewById(R.id.tv_title);
            tv_title.setText(datas.get(position).getShops().get(i).getTitle());
            TextView tv_classify = (TextView) item_oreder_layout.findViewById(R.id.tv_classify);
            tv_classify.setText("￥" + datas.get(position).getShops().get(i).getPrice());
            TextView tv_size = (TextView) item_oreder_layout.findViewById(R.id.tv_size);
            tv_size.setText("×" + datas.get(position).getShops().get(i).getNumber());
            holder.ll_oreders.addView(item_oreder_layout);
        }

        holder.btn_pay_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PayActivity.class)
                        .putExtra("order", datas.get(position).getOrderid())
                        .putExtra("orderid", datas.get(position).getId())
                );
            }
        });
        holder.btn_delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommomDialog(mContext, R.style.dialog, "确定取消订单吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            orderCancel(datas.get(position).getId());
                            datas.get(position).setStu("-1");
                            notifyDataSetChanged();
                        }
                    }
                }).setTitle("提示").show();
            }
        });

        holder.btn_shanchu_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommomDialog(mContext, R.style.dialog, "确定删除订单吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            try {
                                orderOrderDel(datas.get(position).getId());
                                datas.remove(position);
                                notifyDataSetChanged();
                                if (datas.isEmpty()) {
                                    Dialog dialog1 = Utils.showLoadingDialog(mContext);
                                    dialog1.show();
                                    mContext.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", String.valueOf(Ordercontentframelayout.getTag())));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).setTitle("提示").show();
            }
        });

        holder.btn_isget_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    orderReceipt(datas.get(position).getId());
                    datas.get(position).setStu("4");
                    notifyDataSetChanged();
                    if (datas.isEmpty()) {
                        ll_empty.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_order_form_time;
        public TextView tv_order_type;
        public TextView tv_order_content;
        public Button btn_delete_order;
        public Button btn_pay_order;
        public Button btn_isget_order;
        public Button btn_shanchu_order;
        public Button btn_change_order;
        public LinearLayout ll_oreders;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.tv_order_form_time = (TextView) rootView.findViewById(R.id.tv_order_form_time);
            this.tv_order_type = (TextView) rootView.findViewById(R.id.tv_order_type);
            this.tv_order_content = (TextView) rootView.findViewById(R.id.tv_order_content);
            this.btn_delete_order = (Button) rootView.findViewById(R.id.btn_delete_order);
            this.btn_pay_order = (Button) rootView.findViewById(R.id.btn_pay_order);
            this.btn_isget_order = (Button) rootView.findViewById(R.id.btn_isget_order);
            this.btn_shanchu_order = (Button) rootView.findViewById(R.id.btn_shanchu_order);
            this.btn_change_order = (Button) rootView.findViewById(R.id.btn_change_order);
            this.ll_oreders = (LinearLayout) rootView.findViewById(R.id.ll_oreders);
        }
    }

    /**
     * 订单取消
     */
    private void orderCancel(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/cancel", "order/cancel", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    OrderCancelBean orderCancelBean = new Gson().fromJson(result, OrderCancelBean.class);
                    if ("1".equals(String.valueOf(orderCancelBean.getCode()))) {
                        Toast.makeText(mContext, "取消成功", Toast.LENGTH_SHORT).show();
                    } else {
                        EasyToast.showShort(mContext, orderCancelBean.getMsg());
                    }
                    result = null;
                    orderCancelBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 订单删除
     */
    private void orderOrderDel(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/order_del", "order/order_del", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    OrderCancelBean orderCancelBean = new Gson().fromJson(result, OrderCancelBean.class);
                    if ("1".equals(String.valueOf(orderCancelBean.getCode()))) {
                        Toast.makeText(mContext, "订单已删除", Toast.LENGTH_SHORT).show();
                    } else {
                        EasyToast.showShort(mContext, orderCancelBean.getMsg());
                    }
                    result = null;
                    orderCancelBean = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 确认收货
     */
    private void orderReceipt(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("key", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        Log.e("MyOrderAdapter", "params:" + params);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/receipt", "order/receipt", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    SuckleCartDelBean suckleCartDelBean = new Gson().fromJson(result, SuckleCartDelBean.class);
                    if ("1".equals(String.valueOf(suckleCartDelBean.getStu()))) {
                        EasyToast.showShort(mContext, "确认收货成功");
                    } else {
                        EasyToast.showShort(mContext, "确认收货失败");
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
