package com.wenguoyi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.NewsListBean;
import com.wenguoyi.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static com.wenguoyi.Fragment.NewsFragment.ischeck;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class NewsTypeListAdapter extends RecyclerView.Adapter<NewsTypeListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<NewsListBean.NewscateBean> datas = new ArrayList();

    public ArrayList<NewsListBean.NewscateBean> getDatas() {
        return datas;
    }

    public NewsTypeListAdapter(Activity context, List<NewsListBean.NewscateBean> newsListBean) {
        this.mContext = context;
        NewsListBean.NewscateBean newscateBean = new NewsListBean.NewscateBean();
        newscateBean.setCate_name("推荐");
        newscateBean.setId("");
        this.datas.add(newscateBean);
        this.datas.addAll(newsListBean);
    }

    public void setDatas(ArrayList<NewsListBean.NewscateBean> datas) {
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

        holder.tv_type_title.setText(datas.get(position).getCate_name());

        holder.tv_type_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischeck = position;
                notifyDataSetChanged();
                EventBus.getDefault().post(new BankEvent(datas.get(position).getId()));
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
