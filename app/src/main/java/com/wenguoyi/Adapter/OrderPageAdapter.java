package com.wenguoyi.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.wenguoyi.View.OrderContentFrameLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 赵磊 on 2017/9/20.
 */

public class OrderPageAdapter extends PagerAdapter {

    private List<String> PageTitle;
    private Context context;
    private List<OrderContentFrameLayout> PageS = new ArrayList<>();

    public OrderPageAdapter(Context context, List pageTitle) {
        this.PageTitle = pageTitle;
        this.context = context;
        OrderContentFrameLayout orderContentFrameLayout1 = new OrderContentFrameLayout(context, "");
        orderContentFrameLayout1.setTag("");
        PageS.add(orderContentFrameLayout1);
        OrderContentFrameLayout orderContentFrameLayout2 = new OrderContentFrameLayout(context, "1");
        orderContentFrameLayout2.setTag("1");

        PageS.add(orderContentFrameLayout2);
        OrderContentFrameLayout orderContentFrameLayout3 = new OrderContentFrameLayout(context, "2");
        orderContentFrameLayout3.setTag("2");

        PageS.add(orderContentFrameLayout3);
        OrderContentFrameLayout orderContentFrameLayout4 = new OrderContentFrameLayout(context, "3");
        orderContentFrameLayout4.setTag("3");

        PageS.add(orderContentFrameLayout4);
        OrderContentFrameLayout orderContentFrameLayout5 = new OrderContentFrameLayout(context, "4");
        orderContentFrameLayout5.setTag("4");

        PageS.add(orderContentFrameLayout5);
        OrderContentFrameLayout orderContentFrameLayout6 = new OrderContentFrameLayout(context, "-1");
        orderContentFrameLayout6.setTag("-1");

        PageS.add(orderContentFrameLayout6);

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return PageTitle.get(position);
    }

    @Override
    public int getCount() {
        return PageTitle == null ? 0 : PageTitle.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(PageS.get(position));
        return PageS.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        }
        return false;
    }

}