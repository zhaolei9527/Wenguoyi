package com.wenguoyi.Adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
 * 功能描述：首页商品列表适配器，包括了头部，轮播，和列表
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
                titleList.add("生态环境保护多重要，听习近平怎么说");
                titleList.add("军事科研创新的强大引擎，习主席说要这样点燃");
                titleList.add("习近平谈马克思 | 马克思主义深刻改变中国");
                titleList.add("王岐山会见中美工商领袖和前高官对话美方代表");
                holder.tv_content.setTextList(titleList);
                holder.tv_content.startAutoScroll();
                isfirst = !isfirst;
            }
        } else {
            if (holder.gl_shoplist.getChildCount() == 0) {
                for (int i = 0; i < 4; i++) {
                    View inflate = View.inflate(mContext, R.layout.home_shop_list_list_item_layout, null);
                    holder.gl_shoplist.addView(inflate);
                }
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
        public android.support.v7.widget.GridLayout gl_shoplist;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.Roll_pagerView = (RollPagerView) rootView.findViewById(R.id.RollPagerView);
            this.tv_content = (VerticalTextview) rootView.findViewById(R.id.tv_content);
            this.tv_item_title = (TextView) rootView.findViewById(R.id.tv_item_title);
            this.img_search = (ImageView) rootView.findViewById(R.id.img_search);
            this.et_search = (EditText) rootView.findViewById(R.id.et_search);
            this.gv_home_type = (MyGridView) rootView.findViewById(R.id.gv_home_type);
            this.gl_shoplist = (android.support.v7.widget.GridLayout) rootView.findViewById(R.id.gl_shoplist);
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

            switch (i) {
                case 0:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype1);
                    tv_title.setText("中西药品");
                    break;
                case 1:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype2);
                    tv_title.setText("男性");
                    break;
                case 2:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype3);
                    tv_title.setText("女性");
                    break;
                case 3:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype4);
                    tv_title.setText("慢性病");
                    break;
                case 4:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype5);
                    tv_title.setText("问医");
                    break;
                case 5:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype7);
                    tv_title.setText("维生素钙");
                    break;
                case 6:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype8);
                    tv_title.setText("滋补保健");
                    break;
                case 7:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype9);
                    tv_title.setText("医疗器械");
                    break;
                case 8:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype10);
                    tv_title.setText("隐形眼睛");
                    break;
                case 9:
                    SimpleDraweeView.setBackgroundResource(R.mipmap.hometype6);
                    tv_title.setText("更多");
                    break;
                default:
                    break;
            }
            return inflate;
        }
    }


}
