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

import com.facebook.drawee.view.SimpleDraweeView;
import com.wenguoyi.Bean.SuckleCartBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵磊 on 2017/9/20.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter<ShopCarListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SuckleCartBean.CartBean> datas = new ArrayList();
    private TextView tvMoney;
    private final Dialog dialog;

    public ArrayList<SuckleCartBean.CartBean> getDatas() {
        return datas;
    }

    public ShopCarListAdapter(List<SuckleCartBean.CartBean> list, Context context, TextView tvMoney) {
        this.datas = (ArrayList<SuckleCartBean.CartBean>) list;
        this.mContext = context;
        this.tvMoney = tvMoney;
        dialog = Utils.showLoadingDialog(context);
        dialog.dismiss();
    }

    public void setDatas(ArrayList<SuckleCartBean.CartBean> datas) {
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
        holder.tv_guige.setText("规格：" + datas.get(position).getVal());
        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImgurl());
        holder.btn_shuliang.setText(datas.get(position).getNum());
        holder.tv_title.setText(datas.get(position).getGname());
        holder.tv_money.setText("￥" + datas.get(position).getPrice());
        holder.btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(holder.btn_shuliang.getText().toString());
                i = i + 1;
                if (i > Integer.parseInt(datas.get(position).getNums())) {
                    i = Integer.parseInt(datas.get(position).getNums());
                    EasyToast.showShort(holder.btn_shuliang.getContext(), "没有更多库存了");
                } else {

                }
                holder.btn_shuliang.setText(String.valueOf(i));
                datas.get(position).setNum(String.valueOf(i));
                double money = 0;
                for (int i2 = 0; i2 < datas.size(); i2++) {
                    if (datas.get(i2).isCheck()) {
                        double Price = Double.parseDouble(datas.get(i2).getPrice());
                        int i1 = Integer.parseInt(datas.get(i2).getNum());
                        money = money + (Price * i1);
                    }
                }
                tvMoney.setText("￥" + String.valueOf(money));

            }
        });
        holder.btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(holder.btn_shuliang.getText().toString());
                i = i - 1;
                if (i < 1) {
                    i = 1;
                }
                holder.btn_shuliang.setText(String.valueOf(i));
                datas.get(position).setNum(String.valueOf(i));

                double money = 0;
                for (int i2 = 0; i2 < datas.size(); i2++) {
                    if (datas.get(i2).isCheck()) {
                        double Price = Double.parseDouble(datas.get(i2).getPrice());
                        int i1 = Integer.parseInt(datas.get(i2).getNum());
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
                        int i1 = Integer.parseInt(datas.get(i).getNum());
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
        public TextView tv_guige;
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
            this.tv_guige = (TextView) rootView.findViewById(R.id.tv_guige);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.btn_shuliang = (TextView) rootView.findViewById(R.id.btn_shuliang);
            this.btnIsChoosed = (CheckBox) rootView.findViewById(R.id.btnIsChoosed);
            this.btn_jian = (Button) rootView.findViewById(R.id.btn_jian);
            this.btn_jia = (Button) rootView.findViewById(R.id.btn_jia);
            this.btnIsChoosed.setTag(new Integer(-2));//这里
        }
    }

}
