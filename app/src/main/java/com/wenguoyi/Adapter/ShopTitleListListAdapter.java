package com.wenguoyi.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wenguoyi.Activity.ShopListActivity;
import com.wenguoyi.Bean.GoodsScateBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class ShopTitleListListAdapter extends RecyclerView.Adapter<ShopTitleListListAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<GoodsScateBean.MsgBean> datas = new ArrayList();

    public ArrayList<GoodsScateBean.MsgBean> getDatas() {
        return datas;
    }

    public ShopTitleListListAdapter(Activity context, List<GoodsScateBean.MsgBean> msgBeanList) {
        this.mContext = context;
        this.datas.addAll(msgBeanList);
    }

    public void setDatas(ArrayList<GoodsScateBean.MsgBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_title_list_item_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.gl_shoptype.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.get(position).getTcate().size();
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
                View inflate = View.inflate(mContext, R.layout.shop_title_list_item_gv_item_layout, null);
                SimpleDraweeView SimpleDraweeView = inflate.findViewById(R.id.SimpleDraweeView);
                TextView tv_title = inflate.findViewById(R.id.tv_title);
                SimpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getTcate().get(i).getImgurl());
                tv_title.setText(datas.get(position).getTcate().get(i).getTitle());
                return inflate;
            }
        });

        holder.gl_shoptype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mContext.startActivity(new Intent(mContext, ShopListActivity.class).putExtra("tcate", datas.get(position).getId()));
            }
        });

        holder.tv_type_title.setText(datas.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_type_title;
        public com.wenguoyi.View.MyGridView gl_shoptype;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.tv_type_title = (TextView) rootView.findViewById(R.id.tv_type_title);
            this.gl_shoptype = (com.wenguoyi.View.MyGridView) rootView.findViewById(R.id.gl_shoptype);
        }
    }
}
