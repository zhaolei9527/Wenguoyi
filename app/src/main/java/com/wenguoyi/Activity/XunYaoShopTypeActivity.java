package com.wenguoyi.Activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.wenguoyi.Adapter.ShopTitleListListAdapter;
import com.wenguoyi.Adapter.ShopTypeListAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.R;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;

import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/17
 * 功能描述：
 */
public class XunYaoShopTypeActivity extends BaseActivity {

    private int p = 1;
    private SakuraLinearLayoutManager line1;
    private SakuraLinearLayoutManager line2;
    private WenguoyiRecycleView rv_shop_type_list_list;
    private WenguoyiRecycleView rv_shop_list_type_list;

    @Override
    protected int setthislayout() {
        return R.layout.shoptypelist_activity_layout;
    }

    @Override
    protected void initview() {
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
        rv_shop_list_type_list.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });
        ShopTypeListAdapter adapter = new ShopTypeListAdapter(this);
        ShopTitleListListAdapter titleAdapter = new ShopTitleListListAdapter(this);
        rv_shop_type_list_list.setAdapter(adapter);
        rv_shop_list_type_list.setAdapter(titleAdapter);
    }

    private void getData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
