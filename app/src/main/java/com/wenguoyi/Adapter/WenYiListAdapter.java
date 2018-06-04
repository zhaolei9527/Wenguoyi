package com.wenguoyi.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.wenguoyi.Activity.MainActivity;
import com.wenguoyi.Activity.YiShengListActivity;
import com.wenguoyi.Bean.WenYiBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DensityUtils;
import com.wenguoyi.Utils.UrlUtils;
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
    private WenYiBean wenYiBean;

    private ArrayList<String> datas = new ArrayList();
    private ArrayList<String> titleList = new ArrayList<String>();

    public ArrayList<String> getDatas() {
        return datas;
    }

    public WenYiListAdapter(MainActivity context, WenYiBean wenYiBean) {
        this.mContext = context;
        this.wenYiBean = wenYiBean;
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
                //设置轮播图
                holder.Roll_pagerView.setHintView(new IconHintView(mContext, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.x7))));
                holder.Roll_pagerView.setPlayDelay(3000);
                holder.Roll_pagerView.setAdapter(new WenYiLoopAdapter(holder.Roll_pagerView, wenYiBean.getLunbo()));

                //设置科室分类
                holder.gl_wenyitype.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return wenYiBean.getKeshi().size();
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
                        tv_wenyitype.setText(wenYiBean.getKeshi().get(i).getTitle());
                        tv_wenyitype.setTag(wenYiBean.getKeshi().get(i).getId());
                        return inflate;
                    }
                });
                isfirst = !isfirst;
            }

            holder.ll_wenyitype_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, YiShengListActivity.class));
                }
            });

        } else {

            holder.ll_wenyi_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, YiShengListActivity.class));
                }
            });

            holder.gv_yishenglist.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return wenYiBean.getYisheng().size();
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
                    View inflate = View.inflate(mContext, R.layout.wenyi_yisheng_tuijian_list_item_layout, null);
                    SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
                    SimpleDraweeView.setImageURI(UrlUtils.URL + wenYiBean.getYisheng().get(i).getHead());
                    TextView tv_name = inflate.findViewById(R.id.tv_name);
                    tv_name.setText(wenYiBean.getYisheng().get(i).getName());
                    TextView tv_keshi = inflate.findViewById(R.id.tv_keshi);
                    tv_keshi.setText(wenYiBean.getYisheng().get(i).getKeshi());
                    TextView tv_zhicheng = inflate.findViewById(R.id.tv_zhicheng);
                    tv_zhicheng.setText(wenYiBean.getYisheng().get(i).getZhicheng());
                    return inflate;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RollPagerView Roll_pagerView;
        public LinearLayout ll_wenyitype_more;
        public MyGridView gl_wenyitype;
        public LinearLayout ll_wenyi_more;
        public MyGridView gv_yishenglist;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.Roll_pagerView = (RollPagerView) rootView.findViewById(R.id.RollPagerView);
            this.ll_wenyitype_more = (LinearLayout) rootView.findViewById(R.id.ll_wenyitype_more);
            this.gl_wenyitype = (MyGridView) rootView.findViewById(R.id.gl_wenyitype);
            this.ll_wenyi_more = (LinearLayout) rootView.findViewById(R.id.ll_wenyi_more);
            this.gv_yishenglist = (MyGridView) rootView.findViewById(R.id.gv_yishenglist);

        }
    }

}
