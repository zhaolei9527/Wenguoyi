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
import com.wenguoyi.Activity.MainActivity;
import com.wenguoyi.Adapter.WenYiListAdapter;
import com.wenguoyi.App;
import com.wenguoyi.Bean.WenYiBean;
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

import java.util.HashMap;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：
 */
public class WenYiFragment extends BaseLazyFragment {
    private int p = 1;
    private Context context;
    private WenguoyiRecycleView rv_wenyilist;
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
        View view = inflater.inflate(R.layout.wenyi_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_wenyilist = (WenguoyiRecycleView) view.findViewById(R.id.rv_wenyilist);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_wenyilist.setLayoutManager(line);
        rv_wenyilist.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_wenyilist.setFootLoadingView(progressView);

        String WenYiFragment = (String) SpUtil.get(context, "WenYiFragment", "");

        if (!TextUtils.isEmpty(WenYiFragment)) {
            WenYiBean wenYiBean = new Gson().fromJson(WenYiFragment, WenYiBean.class);
            WenYiListAdapter adapter = new WenYiListAdapter((MainActivity) getActivity(), wenYiBean);
            rv_wenyilist.setAdapter(adapter);
        }

    }

    //数据获取
    public void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        Log.e("WenYiFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "yisheng/index", "yisheng/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("WenYiFragment", result);
                try {
                    WenYiBean wenYiBean = new Gson().fromJson(result, WenYiBean.class);
                    if (1 == wenYiBean.getStatus()) {
                        SpUtil.putAndApply(context, "WenYiFragment", result);
                        WenYiListAdapter adapter = new WenYiListAdapter((MainActivity) getActivity(), wenYiBean);
                        rv_wenyilist.setAdapter(adapter);
                    } else {
                        EasyToast.showShort(context, R.string.hasError);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("yisheng/index");
        System.gc();
    }

}
