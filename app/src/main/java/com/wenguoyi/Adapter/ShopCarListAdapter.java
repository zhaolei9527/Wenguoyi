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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wenguoyi.Bean.GoodsCangBean;
import com.wenguoyi.Bean.SuckleCartBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 赵磊 on 2017/9/20.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter<ShopCarListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SuckleCartBean.ResBean> datas = new ArrayList();
    private TextView tvMoney;
    private final Dialog dialog;

    public ArrayList<SuckleCartBean.ResBean> getDatas() {
        return datas;
    }

    public ShopCarListAdapter(List<SuckleCartBean.ResBean> list, Context context, TextView tvMoney) {
        this.datas = (ArrayList<SuckleCartBean.ResBean>) list;
        this.mContext = context;
        this.tvMoney = tvMoney;
        dialog = Utils.showLoadingDialog(context);
        dialog.dismiss();
    }

    public void setDatas(ArrayList<SuckleCartBean.ResBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gouwuche_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImg());
        holder.btn_shuliang.setText(datas.get(position).getNumber());
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_money.setText("￥" + datas.get(position).getPrice());
        holder.btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                int i = Integer.parseInt(holder.btn_shuliang.getText().toString());
                i = i + 1;
                if (i > Integer.parseInt(datas.get(position).getKucun())) {
                    i = Integer.parseInt(datas.get(position).getKucun());
                    EasyToast.showShort(holder.btn_shuliang.getContext(), "没有更多库存了");
                    dialog.dismiss();
                } else {
                    if (Utils.isConnected(holder.btn_jia.getContext())) {
                        cartJoinCart(holder.btn_jia.getContext(), datas.get(position).getGid());
                    } else {
                        i = i - 1;
                        EasyToast.showShort(holder.btn_jia.getContext(), "网络未连接");
                        dialog.dismiss();
                    }
                }
                holder.btn_shuliang.setText(String.valueOf(i));
                datas.get(position).setNumber(String.valueOf(i));

                double money = 0;
                for (int i2 = 0; i2 < datas.size(); i2++) {
                    if (datas.get(position).isCheck()) {
                        double Price = Double.parseDouble(datas.get(i2).getPrice());
                        int i1 = Integer.parseInt(datas.get(i2).getNumber());
                        money = money + (Price * i1);
                    }
                }
                tvMoney.setText("￥" + String.valueOf(money));

            }
        });
        holder.btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                int i = Integer.parseInt(holder.btn_shuliang.getText().toString());
                i = i - 1;
                if (i < 1) {
                    i = 1;
                    dialog.dismiss();
                } else {
                    if (Utils.isConnected(holder.btn_jian.getContext())) {
                        cartReduce(holder.btn_jian.getContext(), datas.get(position).getId());
                    } else {
                        i = i + 1;
                        EasyToast.showShort(holder.btn_jian.getContext(), "网络未连接");
                        dialog.dismiss();
                    }
                }
                holder.btn_shuliang.setText(String.valueOf(i));
                datas.get(position).setNumber(String.valueOf(i));

                double money = 0;
                for (int i2 = 0; i2 < datas.size(); i2++) {
                    if (datas.get(position).isCheck()) {
                        double Price = Double.parseDouble(datas.get(i2).getPrice());
                        int i1 = Integer.parseInt(datas.get(i2).getNumber());
                        money = money + (Price * i1);
                    }
                }
                tvMoney.setText("￥" + String.valueOf(money));
            }
        });

        holder.btnIsChoosed.setTag(new Integer(position));//把组件的状态更新为一个合法的状态值

        holder.btnIsChoosed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    datas.get(position).setCheck(true);
                    int ischecked = 0;
                    for (int i = 0; i < datas.size(); i++) {
                        if (datas.get(i).isCheck()) {
                            ischecked = ischecked + 1;
                        }
                    }
                    if (ischecked == datas.size()) {
                        holder.btnIsChoosed.getContext().sendBroadcast(new Intent("shopCarChoosedAll").putExtra("Choosed", true));
                    }
                } else {
                    datas.get(position).setCheck(false);
                    holder.btnIsChoosed.getContext().sendBroadcast(new Intent("shopCarChoosedAll").putExtra("Choosed", false));
                }
                double money = 0;
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCheck()) {
                        double v = Double.parseDouble(datas.get(i).getPrice());
                        int i1 = Integer.parseInt(datas.get(i).getNumber());
                        money = money + (v * i1);
                    }
                }
                tvMoney.setText("￥" + String.valueOf(money));
            }
        });

        Log.d("ShopCarListAdapter", datas.toString());
        if (datas.get(position).isCheck()) {
            holder.btnIsChoosed.setChecked(true);
        } else {
            holder.btnIsChoosed.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView SimpleDraweeView;
        public TextView tv_title;
        public TextView tv_money;
        public TextView btn_shuliang;
        public CheckBox btnIsChoosed;
        public Button btn_jian;
        public Button btn_jia;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.SimpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.btn_shuliang = (TextView) rootView.findViewById(R.id.btn_shuliang);
            this.btnIsChoosed = (CheckBox) rootView.findViewById(R.id.btnIsChoosed);
            this.btn_jian = (Button) rootView.findViewById(R.id.btn_jian);
            this.btn_jia = (Button) rootView.findViewById(R.id.btn_jia);
            this.btnIsChoosed.setTag(new Integer(-2));//这里
        }
    }


    /**
     * 加入购物车
     */
    private void cartJoinCart(final Context context, String id) {
        HashMap<String, String> params = new HashMap<>(4);
        params.put("key", UrlUtils.KEY);
        params.put("gid", id);
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
                        if (!"1".equals(String.valueOf(goodsCangBean.getIs_have()))) {

                        }
                    } else if ("327".equals(String.valueOf(goodsCangBean.getCode()))) {
                        EasyToast.showShort(context, "加入失败，库存不足");
                    } else {
                        EasyToast.showShort(context, "加入购物车失败");
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, context.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, context.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 减少数量
     */
    private void cartReduce(final Context context, String id) {
        HashMap<String, String> params = new HashMap<>(4);
        params.put("key", UrlUtils.KEY);
        params.put("id", id);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "cart/reduce", "cart/reduce", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("RegisterActivity", result);
                try {
                    GoodsCangBean goodsCangBean = new Gson().fromJson(result, GoodsCangBean.class);
                    if ("323".equals(String.valueOf(goodsCangBean.getCode()))) {
                        if (!"1".equals(String.valueOf(goodsCangBean.getIs_have()))) {

                        }
                    } else if ("327".equals(String.valueOf(goodsCangBean.getCode()))) {
                        EasyToast.showShort(context, "购买数量至少为1");
                    } else {
                        EasyToast.showShort(context, "编辑失败，请重试！");
                    }
                    goodsCangBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, context.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, context.getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
