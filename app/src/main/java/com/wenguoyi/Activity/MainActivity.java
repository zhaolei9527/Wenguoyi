package com.wenguoyi.Activity;

import android.Manifest;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Fragment.HomeFragment;
import com.wenguoyi.Fragment.NewsFragment;
import com.wenguoyi.Fragment.WenYiFragment;
import com.wenguoyi.R;

import java.util.List;

import sakura.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity {

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .setDeniedMessage(getString(R.string.requstPerminssions))
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(MainActivity.this, R.string.Thepermissionapplicationisrejected, Toast.LENGTH_SHORT).show();
                    }
                });

        ((BottomTabBar) findViewById(R.id.BottomTabBar))
                .initFragmentorViewPager(getSupportFragmentManager())
                // .setImgSize(getResources().getDimension(R.dimen.x25), getResources().getDimension(R.dimen.x20))
                .setChangeColor(getResources().getColor(R.color.text666), getResources().getColor(R.color.text666))
                .addReplaceLayout(R.id.fl_content)
                .setDividerHeight(3)
                .isShowDivider(true)
                .setFontSize(1)
                .setDividerColor(getResources().getColor(R.color.bgea))
                .addTabItem("寻药", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher), HomeFragment.class)
                .addTabItem("问医", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher), WenYiFragment.class)
                .addTabItem("资讯", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher), NewsFragment.class)
                .addTabItem("购物车", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher), WenYiFragment.class)
                .addTabItem("我的", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher), WenYiFragment.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, View V) {

                    }
                })
                .commit();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
