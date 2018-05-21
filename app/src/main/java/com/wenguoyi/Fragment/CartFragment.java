package com.wenguoyi.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wenguoyi.R;
import java.util.ArrayList;
import java.util.List;

/**
 * com.wenguoyi.Fragment
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class CartFragment extends BaseLazyFragment {

    private Context context;
    private List<Class> fragmentList;

    @Override
    protected void initPrepare() {
    }

    @Override
    protected void onInvisible() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.cart_fragment_layout, container, false);
        fragmentList = new ArrayList();
        fragmentList.add(ShopCarFragment.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        relaceFrament(0);
    }

    private void relaceFrament(int i) {
        Class aClass = (Class) this.fragmentList.get(i);
        Class clazz = null;
        try {
            clazz = Class.forName(aClass.getName());
            Fragment e = (Fragment) clazz.newInstance();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl, e);
            fragmentTransaction.commit();
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }

}
