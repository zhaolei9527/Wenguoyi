package com.wenguoyi.Activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.wenguoyi.Adapter.ShopListAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.R;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.WenguoyiRecycleView;

import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class ShopListActivity extends BaseActivity {

    private int p = 1;
    private WenguoyiRecycleView rv_shop_list;

    @Override
    protected int setthislayout() {
        return R.layout.shoplist_activity_layout;
    }

    @Override
    protected void initview() {
        rv_shop_list = (WenguoyiRecycleView) findViewById(R.id.rv_shop_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rv_shop_list.setLayoutManager(gridLayoutManager);
        rv_shop_list.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_shop_list.setFootLoadingView(progressView);
        rv_shop_list.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });
        ShopListAdapter shopAdapter = new ShopListAdapter(this);
        rv_shop_list.setAdapter(shopAdapter);
        rv_shop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(context, PriceDetailsActivity.class));
            }
        });
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
