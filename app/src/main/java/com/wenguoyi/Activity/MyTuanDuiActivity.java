package com.wenguoyi.Activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.wenguoyi.Adapter.MyTuanDuiAdapter;
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
 * @date 2018/5/28
 * 功能描述：
 */
public class MyTuanDuiActivity extends BaseActivity {

    private int p = 1;
    private WenguoyiRecycleView rv_shop_list;
    private SakuraLinearLayoutManager line;

    @Override
    protected int setthislayout() {
        return R.layout.activity_mytuandui_layout;
    }

    @Override
    protected void initview() {
        rv_shop_list = (WenguoyiRecycleView) findViewById(R.id.rv_shop_list);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
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
        MyTuanDuiAdapter myTuanDuiAdapter = new MyTuanDuiAdapter(this);
        rv_shop_list.setAdapter(myTuanDuiAdapter);
        rv_shop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(context, PriceDetailsActivity.class));
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    private void getData() {

    }

}
