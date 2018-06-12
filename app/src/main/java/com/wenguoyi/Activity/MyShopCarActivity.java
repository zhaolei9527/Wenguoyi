package com.wenguoyi.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Fragment.CartFragment;
import com.wenguoyi.R;

import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/6/12
 * 功能描述：
 */
public class MyShopCarActivity extends BaseActivity {


    @Override
    protected int setthislayout() {
        return R.layout.activity_myshopcar_layout;
    }

    @Override
    protected void initview() {
        Class aClass = (Class) CartFragment.class;
        Class clazz = null;
        try {
            clazz = Class.forName(aClass.getName());
            Fragment e = (Fragment) clazz.newInstance();
            FragmentTransaction fragmentTransaction = MyShopCarActivity.this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_content, e);
            fragmentTransaction.commit();
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
