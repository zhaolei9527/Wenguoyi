package com.wenguoyi.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenguoyi.Activity.MainActivity;
import com.wenguoyi.Adapter.HomeListAdapter;
import com.wenguoyi.App;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;

import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class HomeFragment extends BaseLazyFragment {
    private int p = 1;
    private Context context;
    private WenguoyiRecycleView rv_homelist;
    private SakuraLinearLayoutManager line;

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_homelist = (WenguoyiRecycleView) view.findViewById(R.id.rv_homelist);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_homelist.setLayoutManager(line);
        rv_homelist.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_homelist.setFootLoadingView(progressView);
        rv_homelist.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });

        HomeListAdapter adapter = new HomeListAdapter((MainActivity) getActivity());
        rv_homelist.setAdapter(adapter);

    }

    //数据获取
    public void getData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("index/index");
    }

}
