package com.wenguoyi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wenguoyi.Adapter.YiShengListAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.R;
import com.wenguoyi.View.CommonPopupWindow;
import com.wenguoyi.View.FlowLayout;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/21
 * 功能描述：
 */
public class YiShengListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.ll_keshi)
    LinearLayout llKeshi;
    @BindView(R.id.ll_zhicheng)
    LinearLayout llZhicheng;
    private int p = 1;
    private WenguoyiRecycleView rv_yisheng_list;
    private SakuraLinearLayoutManager line;

    @Override
    protected int setthislayout() {
        return R.layout.yishenglist_activity_layout;
    }

    @Override
    protected void initview() {
        rv_yisheng_list = (WenguoyiRecycleView) findViewById(R.id.rv_yisheng_list);
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rv_yisheng_list.setLayoutManager(line);
        rv_yisheng_list.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rv_yisheng_list.setFootLoadingView(progressView);
        rv_yisheng_list.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });
        YiShengListAdapter shopAdapter = new YiShengListAdapter(this);
        rv_yisheng_list.setAdapter(shopAdapter);
        rv_yisheng_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(context, PriceDetailsActivity.class));
            }
        });
    }

    private void getData() {

    }

    @Override
    protected void initListener() {
        llKeshi.setOnClickListener(this);
        llZhicheng.setOnClickListener(this);
        initPopupWindow();

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

    @Override
    public void onClick(View view) {
        //PopupWindow win = window.getPopupWindow();
        //win.setAnimationStyle(R.style.animTranslate);
        switch (view.getId()) {
            case R.id.ll_keshi:
                window.showAsDropDown(llKeshi, 0, 0);
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.3f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
            case R.id.ll_zhicheng:
                break;
            default:
                break;
        }
    }

    private CommonPopupWindow window;
    private FlowLayout mFlowLayout;
    private String[] mVals = new String[]{"苹果手机", "笔记本电脑笔记本电脑", "电饭煲 ", "腊肉",
            "特产", "剃须刀", "宝宝", "康佳"};
    private LayoutInflater mInflater;

    private void initPopupWindow() {
        mInflater = LayoutInflater.from(this);
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        window = new CommonPopupWindow(this, R.layout.yisheng_keshi_popupwindow_layout, ViewGroup.LayoutParams.MATCH_PARENT, screenHeight) {
            @Override
            protected void initView() {
                View view = getContentView();
                mFlowLayout = (FlowLayout) view.findViewById(R.id.id_flowlayout);
                /**
                 * 找到搜索标签的控件
                 */
                for (int i = 0; i < mVals.length; i++) {
                    TextView tv = (TextView) mInflater.inflate(
                            R.layout.label_tv_layout, mFlowLayout, false);
                    tv.setText(mVals[i]);
                    final String str = tv.getText().toString();
                    //点击事件
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    mFlowLayout.addView(tv);//添加到父View
                }

            }

            @Override
            protected void initEvent() {

            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
            }
        };
    }


}