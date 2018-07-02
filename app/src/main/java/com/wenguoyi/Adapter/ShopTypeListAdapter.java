package com.wenguoyi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.GoodsFcateBean;
import com.wenguoyi.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.wenguoyi.Activity.XunYaoShopTypeActivity.ischeck;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class ShopTypeListAdapter extends RecyclerView.Adapter<ShopTypeListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<GoodsFcateBean.MsgBean> datas = new ArrayList();

    public ArrayList<GoodsFcateBean.MsgBean> getDatas() {
        return datas;
    }

    public ShopTypeListAdapter(Activity context, List<GoodsFcateBean.MsgBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(List<GoodsFcateBean.MsgBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_type_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    private boolean isfirst = false;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (!isfirst) {
            isfirst = !isfirst;
        }

        holder.tv_type_title.setText(datas.get(position).getTitle());

        holder.tv_type_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischeck = position;
                notifyDataSetChanged();
                EventBus.getDefault().post(new BankEvent(datas.get(position).getId(),"shoptype"));
            }
        });

        if (position != ischeck) {
            holder.img_news.setVisibility(View.GONE);
        } else {
            holder.img_news.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_type_title;
        public ImageView img_news;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.tv_type_title = (TextView) rootView.findViewById(R.id.tv_type_title);
            this.img_news = (ImageView) rootView.findViewById(R.id.img_news);
        }
    }

}
