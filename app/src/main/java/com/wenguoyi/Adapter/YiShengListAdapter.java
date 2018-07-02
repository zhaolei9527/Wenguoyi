package com.wenguoyi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenguoyi.Bean.YiShengListBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.UrlUtils;

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
public class YiShengListAdapter extends RecyclerView.Adapter<YiShengListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<YiShengListBean.YishengBean> datas = new ArrayList();

    public ArrayList<YiShengListBean.YishengBean> getDatas() {
        return datas;
    }

    public YiShengListAdapter(Activity context, List<YiShengListBean.YishengBean> arrayList) {
        this.mContext = context;
        this.datas.addAll(arrayList);
    }

    public void setDatas(List<YiShengListBean.YishengBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.yisheng_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvContent.setText(datas.get(position).getShanchang());
        holder.tvName.setText(datas.get(position).getName());
        holder.tvZhicheng.setText(datas.get(position).getKeshi() + "|" + datas.get(position).getZhicheng());
        holder.SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getHead());
        holder.tvYiyuan.setText(datas.get(position).getYiyuan());
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
        @BindView(R.id.tv_zhicheng)
        TextView tvZhicheng;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_yiyuan)
        TextView tvYiyuan;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.rootView = view;
        }
    }
}
