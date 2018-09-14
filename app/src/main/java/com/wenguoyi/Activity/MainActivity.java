package com.wenguoyi.Activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.IndexCartBean;
import com.wenguoyi.Fragment.CartFragment;
import com.wenguoyi.Fragment.HomeFragment;
import com.wenguoyi.Fragment.MeFragment;
import com.wenguoyi.Fragment.NewsFragment;
import com.wenguoyi.Fragment.WenYiFragment;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.View.CustomViewPager;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sakura.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty((String) SpUtil.get(MainActivity.this, "uid", ""))) {
            indexCatr();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.CAMERA)
                        .setDeniedMessage(getString(R.string.requstPerminssions))
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        try {
                            // 从API11开始android推荐使用android.content.ClipboardManager
                            // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 将文本内容放到系统剪贴板里。
                            cm.setText("支付宝发红包啦！人人可领，天天可领！长按复制此消息，打开支付宝领红包！VavJvm63sZ");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        Toast.makeText(MainActivity.this, R.string.Thepermissionapplicationisrejected, Toast.LENGTH_SHORT).show();
                    }
                });
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new WenYiFragment());
        fragments.add(new NewsFragment());
        fragments.add(new CartFragment());
        fragments.add(new MeFragment());
        CustomViewPager viewpager = (CustomViewPager) findViewById(R.id.fl_content);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        ((BottomTabBar) findViewById(R.id.BottomTabBar)).setPadding(0, 0, 0, 0);
        ((BottomTabBar) findViewById(R.id.BottomTabBar))
                .initFragmentorViewPager(viewpager)
                .setImgSize(getResources().getDimension(R.dimen.x19), getResources().getDimension(R.dimen.y16))
                .setChangeColor(getResources().getColor(R.color.bgtitle), getResources().getColor(R.color.text666))
                .setDividerHeight(3)
                .isShowDivider(true)
                .setFontSize(12)
                .setDividerColor(getResources().getColor(R.color.bgea))
                .addTabItem("寻药", getResources().getDrawable(R.mipmap.xunyao2), getResources().getDrawable(R.mipmap.xunyao1))
                .addTabItem("问医", getResources().getDrawable(R.mipmap.wenyi2), getResources().getDrawable(R.mipmap.wenyi1))
                .addTabItem("资讯", getResources().getDrawable(R.mipmap.zixun2), getResources().getDrawable(R.mipmap.zixun1))
                .addTabItem("购物车", getResources().getDrawable(R.mipmap.gouwuche2), getResources().getDrawable(R.mipmap.gouwuche1))
                .addTabItem("我的", getResources().getDrawable(R.mipmap.me2), getResources().getDrawable(R.mipmap.me1))
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, View view) {
                        if (position == 3 || position == 4) {
                            if (TextUtils.isEmpty((String) SpUtil.get(MainActivity.this, "uid", ""))) {
                                EasyToast.showShort(MainActivity.this, "请先登录");
                                finish();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }
                        if (!TextUtils.isEmpty((String) SpUtil.get(MainActivity.this, "uid", ""))) {
                            indexCatr();
                        }

                    }
                })
                .commit();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                indexCatr();
            }
        };
        registerReceiver(receiver, new IntentFilter("indexCatr"));
    }

    /**
     * 购物车数量
     */
    private void indexCatr() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        Log.e("MainActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "index/cart", "index/cart", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MainActivity", result);
                try {
                    IndexCartBean indexCartBean = new Gson().fromJson(result, IndexCartBean.class);
                    if (1 == indexCartBean.getStatus()) {
                        SpUtil.putAndApply(context, "Cartnum", indexCartBean.getCart_num());
                        TextView tv_Cartnum = (TextView) findViewById(R.id.tv_Cartnum);
                        if (!TextUtils.isEmpty(indexCartBean.getCart_num())) {
                            if (!"0".equals(indexCartBean.getCart_num())) {
                                tv_Cartnum.setVisibility(View.VISIBLE);
                                tv_Cartnum.setText(indexCartBean.getCart_num());
                            } else {
                                tv_Cartnum.setVisibility(View.GONE);
                            }
                        } else {
                            tv_Cartnum.setVisibility(View.GONE);
                        }
                    }
                    indexCartBean = null;
                    result = null;
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
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    private boolean mIsExit;

    /**
     * 双击返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
