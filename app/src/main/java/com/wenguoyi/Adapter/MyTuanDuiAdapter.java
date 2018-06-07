package com.wenguoyi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenguoyi.Bean.UserMytjBean;
import com.wenguoyi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class MyTuanDuiAdapter extends RecyclerView.Adapter<MyTuanDuiAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<UserMytjBean.MsgBean> datas = new ArrayList();

    public ArrayList<UserMytjBean.MsgBean> getDatas() {
        return datas;
    }

    public MyTuanDuiAdapter(Activity context, List<UserMytjBean.MsgBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<UserMytjBean.MsgBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mytuandui_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.SimpleDraweeView.setImageURI(datas.get(position).getHeadimg());
        holder.tvName.setText(datas.get(position).getNickname());
        holder.tvShenfen.setText("会员身份:" + datas.get(position).getLevel_id());
        holder.tvTime.setText("加入时间:" + Long.parseLong(datas.get(position).getAddtime()) * 1000);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        @BindView(R.id.SimpleDraweeView)
        com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_shenfen)
        TextView tvShenfen;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            ButterKnife.bind(this, view);
        }
    }
}
