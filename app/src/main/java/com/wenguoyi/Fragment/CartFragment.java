package com.wenguoyi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenguoyi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class CartFragment extends BaseLazyFragment {

    @BindView(R.id.tv_bianji)
    TextView tvBianji;
    @BindView(R.id.tv_gouwuche)
    TextView tvGouwuche;
    @BindView(R.id.ll_gouweuche)
    LinearLayout llGouweuche;
    @BindView(R.id.tv_wdedingdan)
    TextView tvWdedingdan;
    @BindView(R.id.ll_wdedingdan)
    LinearLayout llWdedingdan;
    @BindView(R.id.fl)
    FrameLayout fl;
    Unbinder unbinder;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
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
        llGouweuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvGouwuche.setTextColor(getResources().getColor(R.color.text333));
                tvWdedingdan.setTextColor(getResources().getColor(R.color.text333));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
            }
        });

        llWdedingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvGouwuche.setTextColor(getResources().getColor(R.color.text333));
                tvWdedingdan.setTextColor(getResources().getColor(R.color.text333));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
            }
        });

        tvBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvBianji.getText().equals("完成")) {
                    tvBianji.setText("编辑");
                    context.sendBroadcast(new Intent("gouwuchebianjiwancheng"));
                } else {
                    tvBianji.setText("完成");
                    context.sendBroadcast(new Intent("gouwuchebianji"));
                }
            }
        });

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
