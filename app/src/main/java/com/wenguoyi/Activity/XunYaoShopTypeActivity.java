package com.wenguoyi.Activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Adapter.ShopTitleListListAdapter;
import com.wenguoyi.Adapter.ShopTypeListAdapter;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.GoodsFcateBean;
import com.wenguoyi.Bean.GoodsScateBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/17
 * 功能描述：
 */
public class XunYaoShopTypeActivity extends BaseActivity {


    private String cid = "";
    public static int ischeck = 0;
    private SakuraLinearLayoutManager line1;
    private SakuraLinearLayoutManager line2;
    private WenguoyiRecycleView rv_shop_type_list_list;
    private WenguoyiRecycleView rv_shop_list_type_list;
    private FrameLayout rl_back;

    @Override
    protected int setthislayout() {
        return R.layout.shoptypelist_activity_layout;
    }

    @Override
    protected void initview() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        rv_shop_type_list_list = (WenguoyiRecycleView) findViewById(R.id.rv_shop_type_list_list);
        rv_shop_list_type_list = (WenguoyiRecycleView) findViewById(R.id.rv_shop_list_type_list);
        line1 = new SakuraLinearLayoutManager(context);
        line2 = new SakuraLinearLayoutManager(context);
        line1.setOrientation(LinearLayoutManager.VERTICAL);
        line2.setOrientation(LinearLayoutManager.VERTICAL);
        rv_shop_type_list_list.setLayoutManager(line1);
        rv_shop_list_type_list.setLayoutManager(line2);
        rv_shop_type_list_list.setItemAnimator(new DefaultItemAnimator());
        rv_shop_list_type_list.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_shop_list_type_list.setFootLoadingView(progressView);
    }

    private void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("cid", cid);
        Log.e("XunYaoShopTypeActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/scate", "goods/scate", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                Log.e("XunYaoShopTypeActivity", result);
                try {
                    GoodsScateBean goodsScateBean = new Gson().fromJson(result, GoodsScateBean.class);
                    if (1 == goodsScateBean.getStatus()) {
                        ShopTitleListListAdapter titleAdapter = new ShopTitleListListAdapter(XunYaoShopTypeActivity.this, goodsScateBean.getMsg());
                        rv_shop_list_type_list.setAdapter(titleAdapter);
                    } else {
                        EasyToast.showShort(context, R.string.hasError);
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getType() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        Log.e("XunYaoShopTypeActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "goods/fcate", "goods/fcate", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("XunYaoShopTypeActivity", result);
                try {
                    GoodsFcateBean goodsFcateBean = new Gson().fromJson(result, GoodsFcateBean.class);
                    if (1 == goodsFcateBean.getStatus()) {
                        cid = goodsFcateBean.getMsg().get(0).getId();
                        getData();
                        ShopTypeListAdapter adapter = new ShopTypeListAdapter(XunYaoShopTypeActivity.this, goodsFcateBean.getMsg());
                        rv_shop_type_list_list.setAdapter(adapter);
                    } else {
                        EasyToast.showShort(context, R.string.hasError);
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void initListener() {
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            getType();
            EventBus.getDefault().register(this);
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeNewsType(BankEvent messageEvent) {
        if ("shoptype".equals(messageEvent.getmType())) {
            cid = messageEvent.getMsg();
            getData();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        App.getQueues().cancelAll("goods/fcate");
    }

}
