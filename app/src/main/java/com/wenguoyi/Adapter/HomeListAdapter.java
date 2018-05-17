package com.wenguoyi.Adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
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
import com.wenguoyi.View.VerticalTextview;

import java.util.ArrayList;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private MainActivity mContext;
    private ArrayList<String> datas = new ArrayList();
    private ArrayList<String> titleList = new ArrayList<String>();

    public ArrayList<String> getDatas() {
        return datas;
    }

    public HomeListAdapter(MainActivity context) {
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_head_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_shop_list_item_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        }
    }

    private boolean isfirst = false;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
                //tvContent.setText(26, 5, Color.RED);//设置属性
                holder.tv_content.setTextStillTime(3000);//设置停留时长间隔
                holder.tv_content.setAnimTime(300);//设置进入和退出的时间间隔
                holder.tv_content.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //startActivity(new Intent(context, NewsDetailsActivity.class).putExtra("id", comeOn.getRes().getNews().get(position).getId()));
                    }
                });
                holder.gv_home_type.setAdapter(new myHomeTypeAdadapter());
                titleList.add("习近平的八个“治党妙喻”");
                titleList.add("习近平的八个“治党妙喻”");
                titleList.add("习近平的八个“治党妙喻”");
                titleList.add("习近平的八个“治党妙喻”");
                titleList.add("习近平的八个“治党妙喻”");
                holder.tv_content.setTextList(titleList);
                holder.tv_content.startAutoScroll();
                isfirst = !isfirst;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                View inflate = View.inflate(mContext, R.layout.home_shop_list_list_item_layout, null);
                //使用Spec定义子控件的位置和比重
                GridLayout.Spec rowSpec = GridLayout.spec(i / 2, 1f);
                GridLayout.Spec columnSpec = GridLayout.spec(i % 2, 1f);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
                if (i / 2 == 0) {
                    layoutParams.bottomMargin = mContext.getResources().getDimensionPixelSize(R.dimen.x2);
                }
                if (i % 2 == 1) {
                    layoutParams.leftMargin = mContext.getResources().getDimensionPixelSize(R.dimen.x2);
                    layoutParams.rightMargin = mContext.getResources().getDimensionPixelSize(R.dimen.x2);
                }
                holder.gl_shoplist.addView(inflate, layoutParams);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 10 + 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RollPagerView Roll_pagerView;
        public TextView tv_item_title;
        public ImageView img_search;
        public EditText et_search;
        public MyGridView gv_home_type;
        public VerticalTextview tv_content;
        public GridLayout gl_shoplist;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.Roll_pagerView = (RollPagerView) rootView.findViewById(R.id.RollPagerView);
            this.tv_content = (VerticalTextview) rootView.findViewById(R.id.tv_content);
            this.tv_item_title = (TextView) rootView.findViewById(R.id.tv_item_title);
            this.img_search = (ImageView) rootView.findViewById(R.id.img_search);
            this.et_search = (EditText) rootView.findViewById(R.id.et_search);
            this.gv_home_type = (MyGridView) rootView.findViewById(R.id.gv_home_type);
            this.gl_shoplist = (GridLayout) rootView.findViewById(R.id.gl_shoplist);
        }
    }

    class myHomeTypeAdadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
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
            View inflate = View.inflate(mContext, R.layout.home_gv_item_type_layout, null);

            LinearLayout ll_type = inflate.findViewById(R.id.ll_type);
            SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
            TextView tv_title = inflate.findViewById(R.id.tv_title);

            if (i == 9) {
                tv_title.setText("更多");
            } else {
                tv_title.setText("分类" + i);
            }

            return inflate;
        }
    }


}
