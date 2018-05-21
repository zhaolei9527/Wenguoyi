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
import com.wenguoyi.Adapter.NewsTitleListAdapter;
import com.wenguoyi.Adapter.NewsTypeListAdapter;
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
public class NewsFragment extends BaseLazyFragment {
    private int p = 1;
    private Context context;
    private SakuraLinearLayoutManager line1;
    private SakuraLinearLayoutManager line2;
    private WenguoyiRecycleView rv_news_type_list;
    private WenguoyiRecycleView rv_news_list;

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
        View view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_news_type_list = (WenguoyiRecycleView) view.findViewById(R.id.rv_news_type_list);
        rv_news_list = (WenguoyiRecycleView) view.findViewById(R.id.rv_news_list);

        line1 = new SakuraLinearLayoutManager(context);
        line2 = new SakuraLinearLayoutManager(context);

        line1.setOrientation(LinearLayoutManager.VERTICAL);
        line2.setOrientation(LinearLayoutManager.VERTICAL);

        rv_news_type_list.setLayoutManager(line1);
        rv_news_list.setLayoutManager(line2);


        rv_news_type_list.setItemAnimator(new DefaultItemAnimator());
        rv_news_list.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_news_list.setFootLoadingView(progressView);
        rv_news_list.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });
        NewsTypeListAdapter adapter = new NewsTypeListAdapter((MainActivity) getActivity());
        NewsTitleListAdapter titleAdapter = new NewsTitleListAdapter((MainActivity) getActivity());

        rv_news_list.setFootLoadingView(progressView);
        rv_news_type_list.setAdapter(adapter);
        rv_news_list.setAdapter(titleAdapter);

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
