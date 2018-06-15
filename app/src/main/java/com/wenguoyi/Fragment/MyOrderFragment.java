package com.wenguoyi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenguoyi.Activity.MyOrderActivity;
import com.wenguoyi.Adapter.OrderPageAdapter;
import com.wenguoyi.R;
import com.wenguoyi.View.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/25
 * 功能描述：
 */
public class MyOrderFragment extends BaseLazyFragment {

    private Context context;
    private PagerSlidingTabStrip tabs;
    private ViewPager VpNews_context;
    private List titles = new ArrayList();
    private OrderPageAdapter orderPageAdapter;


    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        if (orderPageAdapter == null) {
            orderPageAdapter = new OrderPageAdapter(context, titles);
            VpNews_context.setAdapter(orderPageAdapter);
            if ("1".equals(MyOrderActivity.cid)) {
                VpNews_context.setCurrentItem(1);
            } else if ("2".equals(MyOrderActivity.cid)) {
                VpNews_context.setCurrentItem(2);
            } else if ("3".equals(MyOrderActivity.cid)) {
                VpNews_context.setCurrentItem(3);
            }
        }

        VpNews_context.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", ""));
                } else if (position == 1) {
                    context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", "1"));
                } else if (position == 2) {
                    context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", "2"));
                } else if (position == 3) {
                    context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", "3"));
                } else if (position == 4) {
                    context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", "4"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabs.setViewPager(VpNews_context);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_my_data, container, false);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        VpNews_context = (ViewPager) view.findViewById(R.id.VpNews_context);
        titles.clear();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待发货");
        titles.add("待收货");
        titles.add("已完成");



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("stu", ""));
    }

    @Override
    public void onDestroy() {
        context.sendBroadcast(new Intent("OrderContentRefresh").putExtra("unRegister", "unRegister"));
        super.onDestroy();
    }

}
