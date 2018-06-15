package com.wenguoyi.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wenguoyi.Activity.PayActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.OrderListsBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DateUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.CommomDialog;
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
    private ArrayList<OrderListsBean.MsgBean> datas = new ArrayList();
    private Dialog dialog;

    public ArrayList<OrderListsBean.MsgBean> getDatas() {
        return datas;
    }


    public MyOrderAdapter(Context context, List<OrderListsBean.MsgBean> list) {
        this.datas.addAll(list);
        this.mContext = context;
    }

    public void setDatas(List<OrderListsBean.MsgBean> datas) {
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
        holder.tv_order_content.setText("共" + datas.get(position).getAmount() + "件商品 合计:￥" + datas.get(position).getTotalprice());
        holder.ll_oreders.removeAllViews();
        final String stu = datas.get(position).getStatus();
        if ("1".equals(stu)) {
            holder.tv_order_type.setText("待付款");
            holder.btn_pay_order.setVisibility(View.VISIBLE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.VISIBLE);
        } else if ("2".equals(stu)) {
            holder.tv_order_type.setText("待发货");
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
        } else if ("3".equals(stu)) {
            holder.btn_delete_order.setVisibility(View.GONE);
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.VISIBLE);
            holder.tv_order_type.setText("待收货");
        } else if ("4".equals(stu)) {
            holder.tv_order_type.setText("已完成");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
        } else {
            holder.tv_order_type.setText("已取消");
            holder.btn_pay_order.setVisibility(View.GONE);
            holder.btn_isget_order.setVisibility(View.GONE);
            holder.btn_delete_order.setVisibility(View.GONE);
        }

        for (int i = 0; i < datas.get(position).getRes().size(); i++) {
            View item_oreder_layout = View.inflate(mContext, R.layout.item_oreder_layout, null);
            SimpleDraweeView SimpleDraweeView = (com.facebook.drawee.view.SimpleDraweeView) item_oreder_layout.findViewById(R.id.SimpleDraweeView);
            SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getRes().get(i).getImg());
            TextView tv_title = (TextView) item_oreder_layout.findViewById(R.id.tv_title);
            tv_title.setText(datas.get(position).getRes().get(i).getGname());
            TextView tv_classify = (TextView) item_oreder_layout.findViewById(R.id.tv_classify);
            tv_classify.setText("￥" + datas.get(position).getRes().get(i).getPrice());
            TextView tv_size = (TextView) item_oreder_layout.findViewById(R.id.tv_size);
            tv_size.setText("×" + datas.get(position).getRes().get(i).getAmount());
            TextView tv_type = (TextView) item_oreder_layout.findViewById(R.id.tv_type);
            if (!TextUtils.isEmpty(datas.get(position).getVal())) {
                tv_type.setText(datas.get(position).getVal());
            }
            holder.ll_oreders.addView(item_oreder_layout);
        }

        holder.btn_pay_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = Utils.showLoadingDialog(mContext);
                dialog.show();
                orderZhifu(datas.get(position).getId());

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
                            datas.get(position).setStatus("-1");
                            notifyDataSetChanged();
                        }
                    }
                }).setTitle("提示").show();
            }
        });


        holder.btn_isget_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.get(position).setStatus("4");
                notifyItemChanged(position);
                orderReceipt(datas.get(position).getId());
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
        public Button btn_pay_order;
        public Button btn_isget_order;
        public LinearLayout ll_oreders;
        public Button btn_delete_order;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.btn_delete_order = (Button) rootView.findViewById(R.id.btn_delete_order);
            this.tv_order_form_time = (TextView) rootView.findViewById(R.id.tv_order_form_time);
            this.tv_order_type = (TextView) rootView.findViewById(R.id.tv_order_type);
            this.tv_order_content = (TextView) rootView.findViewById(R.id.tv_order_content);
            this.btn_pay_order = (Button) rootView.findViewById(R.id.btn_pay_order);
            this.btn_isget_order = (Button) rootView.findViewById(R.id.btn_isget_order);
            this.ll_oreders = (LinearLayout) rootView.findViewById(R.id.ll_oreders);
        }
    }

    /**
     * 订单取消
     */
    private void orderCancel(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/billdel", "order/billdel", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean orderCancelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(orderCancelBean.getStatus()))) {
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
     * 确认收货
     */
    private void orderReceipt(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        Log.e("MyOrderAdapter", "params:" + params);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/confirmbill", "order/confirmbill", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    CodeBean suckleCartDelBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(suckleCartDelBean.getStatus()))) {
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

    /**
     * 立即支付
     */
    private void orderZhifu(String id) {
        HashMap<String, String> params = new HashMap<>(3);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        params.put("id", id);
        Log.e("MyOrderAdapter", "params:" + params);
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "order/zhifu", "order/zhifu", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MyOrderAdapter", result);
                try {
                    dialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        mContext.startActivity(new Intent(mContext, PayActivity.class)
                                .putExtra("orderid", codeBean.getMsg())
                        );
                    } else {
                        EasyToast.showShort(mContext, codeBean.getMsg());
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(mContext, mContext.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
