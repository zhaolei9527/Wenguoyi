package com.wenguoyi.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Adapter.NewsTitleListAdapter;
import com.wenguoyi.Adapter.NewsTypeListAdapter;
import com.wenguoyi.App;
import com.wenguoyi.Bean.BankEvent;
import com.wenguoyi.Bean.NewsListBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
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

import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class MyNewsFragment extends BaseLazyFragment {
    private int p = 1;
    private String cid = "";
    public static int ischeck = 0;

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
            EventBus.getDefault().register(this);
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.activity_news_fragment_layout, container, false);
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
        rv_news_list.setFootLoadingView(progressView);

        String myNewsFragment = (String) SpUtil.get(context, "MyNewsFragment", "");

        if (!TextUtils.isEmpty(myNewsFragment)) {
            NewsListBean newsListBean = new Gson().fromJson(myNewsFragment, NewsListBean.class);
            NewsTypeListAdapter adapter = new NewsTypeListAdapter(getActivity(), newsListBean.getNewscate());
            rv_news_type_list.setAdapter(adapter);
            NewsTitleListAdapter titleAdapter = new NewsTitleListAdapter(getActivity(), newsListBean.getMsg());
            rv_news_list.setAdapter(titleAdapter);
        }

    }


    //数据获取
    public void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("page", String.valueOf(p));
        params.put("cid", cid);
        Log.e("NewsFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "news/lists", "news/lists", params, new VolleyInterface(context) {

            private NewsTitleListAdapter titleAdapter;
            private NewsTypeListAdapter adapter;

            @Override
            public void onMySuccess(String result) {
                Log.e("MyNewsFragment", result);
                try {
                    rv_news_list.loadMoreComplete();
                    NewsListBean newsListBean = new Gson().fromJson(result, NewsListBean.class);
                    if (1 == newsListBean.getStatus()) {
                        SpUtil.putAndApply(context, "MyNewsFragment", result);
                        if (p == 1) {
                            if (adapter == null) {
                                adapter = new NewsTypeListAdapter(getActivity(), newsListBean.getNewscate());
                                rv_news_type_list.setAdapter(adapter);
                            }
                            if (titleAdapter == null) {
                                titleAdapter = new NewsTitleListAdapter(getActivity(), newsListBean.getMsg());
                            }
                            rv_news_list.setAdapter(titleAdapter);
                        } else {
                            titleAdapter.setDatas(newsListBean.getMsg());
                        }

                        if (0 == newsListBean.getFy()) {
                            rv_news_list.loadMoreEnd();
                            rv_news_list.setCanloadMore(false);
                        } else {
                            rv_news_list.setCanloadMore(true);
                        }

                    } else if (2 == newsListBean.getStatus()) {
                        EasyToast.showShort(context, R.string.notmore);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeNewsType(BankEvent messageEvent) {
        if ("newstype".equals(messageEvent.getmType())) {
            p = 1;
            cid = messageEvent.getMsg();
            getData();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        App.getQueues().cancelAll("news/lists");
    }

}
