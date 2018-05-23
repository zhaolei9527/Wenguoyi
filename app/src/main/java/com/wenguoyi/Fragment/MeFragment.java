package com.wenguoyi.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenguoyi.R;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/23
 * 功能描述：
 */
public class MeFragment extends BaseLazyFragment {
    private Context context;


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
        View view = inflater.inflate(R.layout.me_frament_layout, container, false);
        return view;
    }
}
