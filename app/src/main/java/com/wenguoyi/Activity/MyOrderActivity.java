package com.wenguoyi.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Fragment.MyOrderFragment;
import com.wenguoyi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/6/12
 * 功能描述：
 */
public class MyOrderActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    @Override
    protected int setthislayout() {
        return R.layout.activity_myorder_layout;
    }

    @Override
    protected void initview() {
        Class aClass = (Class) MyOrderFragment.class;
        Class clazz = null;
        try {
            clazz = Class.forName(aClass.getName());
            Fragment e = (Fragment) clazz.newInstance();
            FragmentTransaction fragmentTransaction = MyOrderActivity.this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_content, e);
            fragmentTransaction.commit();
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
