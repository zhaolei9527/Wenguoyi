package com.wenguoyi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.wenguoyi.Activity.MainActivity;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DensityUtils;
import com.wenguoyi.View.MyGridView;

import java.util.ArrayList;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class WenYiListAdapter extends RecyclerView.Adapter<WenYiListAdapter.ViewHolder> {

    private MainActivity mContext;
    private ArrayList<String> datas = new ArrayList();
    private ArrayList<String> titleList = new ArrayList<String>();

    public ArrayList<String> getDatas() {
        return datas;
    }

    public WenYiListAdapter(MainActivity context) {
        this.mContext = context;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.wenyi_head_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.wenyi_yisheng_list_item_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        }
    }

    private boolean isfirst = false;


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0) {
            if (!isfirst) {
                //初始化TabHost容器
                holder.Roll_pagerView.setHintView(new IconHintView(mContext, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.x7))));
                holder.Roll_pagerView.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
                holder.Roll_pagerView.setPlayDelay(3000);
                holder.Roll_pagerView.setAdapter(new LoopAdapter(holder.Roll_pagerView));
                holder.gl_wenyitype.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return 9;
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        View inflate = View.inflate(mContext, R.layout.wenyi_item_wenyitype_layout, null);
                        TextView tv_wenyitype = inflate.findViewById(R.id.tv_wenyitype);
                        return inflate;
                    }
                });




                isfirst = !isfirst;
            }
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return 10 + 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RollPagerView Roll_pagerView;
        public LinearLayout ll_wenyitype_more;
        public MyGridView gl_wenyitype;
        public LinearLayout ll_wenyi_more;
        public SimpleDraweeView SimpleDraweeView1;
        public TextView tv_name1;
        public TextView tv_zhicheng1;
        public TextView tv_keshi1;
        public SimpleDraweeView SimpleDraweeView2;
        public TextView tv_name2;
        public TextView tv_zhicheng2;
        public TextView tv_keshi2;
        public SimpleDraweeView SimpleDraweeView3;
        public TextView tv_name3;
        public TextView tv_zhicheng3;
        public TextView tv_keshi3;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.Roll_pagerView = (RollPagerView) rootView.findViewById(R.id.RollPagerView);
            this.ll_wenyitype_more = (LinearLayout) rootView.findViewById(R.id.ll_wenyitype_more);
            this.gl_wenyitype = (MyGridView) rootView.findViewById(R.id.gl_wenyitype);
            this.ll_wenyi_more = (LinearLayout) rootView.findViewById(R.id.ll_wenyi_more);
            this.SimpleDraweeView1 = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView1);
            this.tv_name1 = (TextView) rootView.findViewById(R.id.tv_name1);
            this.tv_zhicheng1 = (TextView) rootView.findViewById(R.id.tv_zhicheng1);
            this.tv_keshi1 = (TextView) rootView.findViewById(R.id.tv_keshi1);
            this.SimpleDraweeView2 = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView2);
            this.tv_name2 = (TextView) rootView.findViewById(R.id.tv_name2);
            this.tv_zhicheng2 = (TextView) rootView.findViewById(R.id.tv_zhicheng2);
            this.tv_keshi2 = (TextView) rootView.findViewById(R.id.tv_keshi2);
            this.SimpleDraweeView3 = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView3);
            this.tv_name3 = (TextView) rootView.findViewById(R.id.tv_name3);
            this.tv_zhicheng3 = (TextView) rootView.findViewById(R.id.tv_zhicheng3);
            this.tv_keshi3 = (TextView) rootView.findViewById(R.id.tv_keshi3);
        }
    }

}
