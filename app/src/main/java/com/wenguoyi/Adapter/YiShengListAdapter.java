package com.wenguoyi.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenguoyi.R;

import java.util.ArrayList;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class YiShengListAdapter extends RecyclerView.Adapter<YiShengListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<String> datas = new ArrayList();
    private ArrayList<String> titleList = new ArrayList<String>();

    public ArrayList<String> getDatas() {
        return datas;
    }

    public YiShengListAdapter(Activity context) {
        this.mContext = context;
    }

    public void setDatas(ArrayList<String> datas) {
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
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
        }
    }
}
