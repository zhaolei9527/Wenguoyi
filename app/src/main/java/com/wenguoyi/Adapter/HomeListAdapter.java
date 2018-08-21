package com.wenguoyi.Adapter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.wenguoyi.Activity.MainActivity;
import com.wenguoyi.Activity.MyNewsActivity;
import com.wenguoyi.Activity.NewsDetailsActivity;
import com.wenguoyi.Activity.PriceDetailsActivity;
import com.wenguoyi.Activity.ShopListActivity;
import com.wenguoyi.Activity.XunYaoShopTypeActivity;
import com.wenguoyi.Bean.HomeBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DensityUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
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
    private HomeBean homeBean;

    private ArrayList<String> datas = new ArrayList();
    private ArrayList<String> titleList = new ArrayList<String>();

    public ArrayList<String> getDatas() {
        return datas;
    }

    public HomeListAdapter(MainActivity context, HomeBean homeBean) {
        this.mContext = context;
        this.homeBean = homeBean;
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
                //轮播图设置
                holder.Roll_pagerView.setHintView(new IconHintView(mContext, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.x7))));
                holder.Roll_pagerView.setPlayDelay(3000);
                holder.Roll_pagerView.setAdapter(new LoopAdapter(holder.Roll_pagerView, homeBean.getLunbo()));
                //新闻跑马灯设置
                //tvContent.setText(26, 5, Color.RED);//设置属性
                holder.tv_content.setTextStillTime(3000);//设置停留时长间隔
                holder.tv_content.setAnimTime(300);//设置进入和退出的时间间隔
                holder.tv_content.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //新闻点击事件
                        mContext.startActivity(new Intent(mContext, NewsDetailsActivity.class).putExtra("id", homeBean.getNews().get(position).getId()));
                    }
                });

                holder.gv_home_type.setAdapter(new myHomeTypeAdadapter());

                for (int i = 0; i < homeBean.getNews().size(); i++) {
                    titleList.add(Utils.Html2Text(homeBean.getNews().get(i).getTitle().toString()));
                }

                holder.tv_content.setTextList(titleList);
                holder.tv_content.startAutoScroll();

                holder.et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        if ((actionId == 0 || actionId == 3) && event != null) {
                            //点击搜索要做的操作
                            String trim = holder.et_search.getText().toString().trim();
                            if (TextUtils.isEmpty(trim)) {
                                EasyToast.showShort(mContext, "请输入商品名称");
                                return false;
                            }
                            holder.et_search.setText("");
                            mContext.startActivity(new Intent(mContext, ShopListActivity.class).putExtra("key", trim));
                        }
                        return false;
                    }
                });


                holder.img_news.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext, MyNewsActivity.class));
                    }
                });

                isfirst = !isfirst;
            }
        } else {
            if (position == 1) {
                holder.tv_item_title.setText("精品医药");
                holder.gl_shoplist.removeAllViews();
                if (holder.gl_shoplist.getChildCount() == 0) {
                    for (int i = 0; i < homeBean.getJingpin().size(); i++) {
                        final View inflate = View.inflate(mContext, R.layout.home_shop_list_list_item_layout, null);
                        SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
                        TextView tv_title = inflate.findViewById(R.id.tv_title);
                        TextView tv_price = inflate.findViewById(R.id.tv_price);
                        SimpleDraweeView.setImageURI(UrlUtils.URL + homeBean.getJingpin().get(i).getImgurl());
                        tv_title.setText(homeBean.getJingpin().get(i).getGname());
                        tv_price.setText("￥" + homeBean.getJingpin().get(i).getPrice());
                        inflate.setTag(homeBean.getJingpin().get(i).getId());
                        holder.gl_shoplist.addView(inflate);
                        inflate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", (String) inflate.getTag()));
                            }
                        });
                    }
                }
            } else {

                try {
                    holder.tv_item_title.setText(homeBean.getCate().get(position - 2).getTitle());
                    holder.gl_shoplist.removeAllViews();
                    if (holder.gl_shoplist.getChildCount() == 0) {
                        for (int i = 0; i < homeBean.getCate().size(); i++) {
                            final View inflate = View.inflate(mContext, R.layout.home_shop_list_list_item_layout, null);
                            SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
                            TextView tv_title = inflate.findViewById(R.id.tv_title);
                            TextView tv_price = inflate.findViewById(R.id.tv_price);
                            SimpleDraweeView.setImageURI(UrlUtils.URL + homeBean.getCate().get(position - 2).getGoods().get(i).getImgurl());
                            tv_title.setText(homeBean.getCate().get(position - 2).getGoods().get(i).getGname());
                            tv_price.setText("￥" + homeBean.getCate().get(position - 2).getGoods().get(i).getPrice());
                            inflate.setTag(homeBean.getCate().get(position - 2).getGoods().get(i).getId());
                            inflate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", (String) inflate.getTag()));
                                }
                            });
                            holder.gl_shoplist.addView(inflate);
                        }
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }


            }
        }
    }

    @Override
    public int getItemCount() {
        return homeBean.getCate().size() + 2;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RollPagerView Roll_pagerView;
        public TextView tv_item_title;
        public ImageView img_search;
        public ImageView img_news;
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
            this.img_news = (ImageView) rootView.findViewById(R.id.img_news);
            this.et_search = (EditText) rootView.findViewById(R.id.et_search);
            this.gv_home_type = (MyGridView) rootView.findViewById(R.id.gv_home_type);
            this.gl_shoplist = (android.support.v7.widget.GridLayout) rootView.findViewById(R.id.gl_shoplist);
        }
    }

    class myHomeTypeAdadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return homeBean.getCate().size() + 1;
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View inflate = View.inflate(mContext, R.layout.home_gv_item_type_layout, null);

            LinearLayout ll_type = inflate.findViewById(R.id.ll_type);
            SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
            TextView tv_title = inflate.findViewById(R.id.tv_title);
            if (i == homeBean.getCate().size()) {
                SimpleDraweeView.setBackgroundResource(R.mipmap.hometype6);
                tv_title.setText("全部分类");
                ll_type.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext, XunYaoShopTypeActivity.class));
                    }
                });
            } else {
                SimpleDraweeView.setImageURI(UrlUtils.URL + homeBean.getCate().get(i).getImgurl());
                tv_title.setText(homeBean.getCate().get(i).getTitle());
                ll_type.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivity(new Intent(mContext, ShopListActivity.class).putExtra("fcate", homeBean.getCate().get(i).getId()));
                    }
                });
            }
            return inflate;
        }
    }

}
