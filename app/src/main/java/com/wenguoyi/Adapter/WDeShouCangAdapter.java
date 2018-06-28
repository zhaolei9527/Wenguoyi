package com.wenguoyi.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.UserCollectBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.CommomDialog;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mob.tools.utils.Strings.getString;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class WDeShouCangAdapter extends RecyclerView.Adapter<WDeShouCangAdapter.ViewHolder> {

    private Activity mContext;
    private ArrayList<UserCollectBean.MsgBean> datas = new ArrayList();
    private Dialog LoadingDialog;

    public ArrayList<UserCollectBean.MsgBean> getDatas() {
        return datas;
    }

    public WDeShouCangAdapter(Activity context, List<UserCollectBean.MsgBean> msgBeen) {
        this.mContext = context;
        this.datas.addAll(msgBeen);
    }

    public void setDatas(List<UserCollectBean.MsgBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shoucang_layout, parent, false);
        ViewHolder vp = new ViewHolder(view);
        return vp;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.simpleDraweeView.setImageURI(UrlUtils.URL + datas.get(position).getImgurl());
        holder.tv_money.setText("￥" + datas.get(position).getPrice());
        holder.tv_title.setText(datas.get(position).getGname());

        holder.btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isConnected(mContext)) {

                    new CommomDialog(mContext, R.style.dialog, "确定取消收藏吗？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                dialog.dismiss();
                            } else {
                                dialog.dismiss();
                                LoadingDialog = Utils.showLoadingDialog(mContext);
                                LoadingDialog.show();
                                goodsOnCang(datas.get(position).getGid());
                                datas.remove(position);
                                notifyDataSetChanged();
                            }
                        }
                    }).setTitle("提示").show();


                } else {
                    EasyToast.showShort(mContext, R.string.Networkexception);
                }
            }
        });

    }

    /**
     * 取消收藏
     */
    private void goodsOnCang(String gid) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("gid", gid);
        params.put("uid", String.valueOf(SpUtil.get(mContext, "uid", "")));
        Log.e("PriceDetailsActivity", params.toString());
        VolleyRequest.RequestPost(mContext, UrlUtils.BASE_URL + "user/bucoll", "user/bucoll", params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                Log.e("RegisterActivity", result);
                try {
                    LoadingDialog.dismiss();
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {
                        EasyToast.showShort(mContext, "取消收藏");
                    } else {
                        EasyToast.showShort(mContext, "取消失败");
                    }
                    codeBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                LoadingDialog.dismiss();
                error.printStackTrace();
                Toast.makeText(mContext, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public SimpleDraweeView simpleDraweeView;
        public TextView tv_title;
        public TextView tv_money;
        public Button btn_cancle;

        public ViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.simpleDraweeView = (SimpleDraweeView) rootView.findViewById(R.id.SimpleDraweeView);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.btn_cancle = (Button) rootView.findViewById(R.id.btn_cancle);
        }
    }

}
