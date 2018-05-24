package com.wenguoyi.Activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import com.wenguoyi.Adapter.YiShengListAdapter;
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
 * @date 2018/5/21
 * 功能描述：
 */
public class YiShengListActivity extends BaseActivity {

    private int p = 1;
    private WenguoyiRecycleView rv_yisheng_list;
    private SakuraLinearLayoutManager line;

    @Override
    protected int setthislayout() {
        return R.layout.yishenglist_activity_layout;
    }

    @Override
    protected void initview() {
        rv_yisheng_list = (WenguoyiRecycleView) findViewById(R.id.rv_yisheng_list);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_yisheng_list.setLayoutManager(line);
        rv_yisheng_list.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_yisheng_list.setFootLoadingView(progressView);
        rv_yisheng_list.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });
        YiShengListAdapter shopAdapter = new YiShengListAdapter(this);
        rv_yisheng_list.setAdapter(shopAdapter);
        rv_yisheng_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
