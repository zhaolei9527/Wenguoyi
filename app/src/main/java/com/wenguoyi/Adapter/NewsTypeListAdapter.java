package com.wenguoyi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenguoyi.Activity.MainActivity;
import com.wenguoyi.R;

import java.util.ArrayList;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class NewsTypeListAdapter extends RecyclerView.Adapter<NewsTypeListAdapter.ViewHolder> {

    private MainActivity mContext;
    private ArrayList<String> datas = new ArrayList();
    private ArrayList<String> titleList = new ArrayList<String>();

    public ArrayList<String> getDatas() {
        return datas;
    }

    public NewsTypeListAdapter(MainActivity context) {
        this.mContext = context;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_type_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    private boolean isfirst = false;

    private int ischeck = 0;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (!isfirst) {
            isfirst = !isfirst;
        }

        holder.tv_type_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ischeck = position;
                notifyDataSetChanged();

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
        return 10;
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
