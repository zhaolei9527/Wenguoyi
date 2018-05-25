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
    private WindowManager.LayoutParams lp;

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
                startActivity(new Intent(context, YiShengDetailsActivity.class));
            }
        });
    }

    private void getData() {

    }

    @Override
    protected void initListener() {
        llKeshi.setOnClickListener(this);
        llZhicheng.setOnClickListener(this);
        initKeShiPopupWindow();
        initZhiChengPopupWindow();
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
                keshiwindow.showAsDropDown(llKeshi, 0, 0);
                lp = getWindow().getAttributes();
                lp.alpha = 0.3f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
            case R.id.ll_zhicheng:
                zhichengwindow.showAsDropDown(llZhicheng, 0, 0);
                lp = getWindow().getAttributes();
                lp.alpha = 0.3f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
            default:
                break;
        }
    }

    private CommonPopupWindow keshiwindow;
    private CommonPopupWindow zhichengwindow;

    private FlowLayout mFlowLayout;

    private String[] mkeshiVals = new String[]{"全部科室", "妇产科", "儿科 ", "综合门诊",
            "内科", "外壳", "中医科", "皮肤性病科", "康复医学", "重症医学科", "其他科室"};
    private LayoutInflater mInflater;

    private void initKeShiPopupWindow() {
        mInflater = LayoutInflater.from(this);
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        keshiwindow = new CommonPopupWindow(this, R.layout.yisheng_keshi_popupwindow_layout, ViewGroup.LayoutParams.MATCH_PARENT, screenHeight) {
            @Override
            protected void initView() {
                View view = getContentView();
                mFlowLayout = (FlowLayout) view.findViewById(R.id.id_flowlayout);
                /**
                 * 找到搜索标签的控件
                 */
                for (int i = 0; i < mkeshiVals.length; i++) {
                    TextView tv = (TextView) mInflater.inflate(
                            R.layout.label_tv_keshi_layout, mFlowLayout, false);
                    tv.setText(mkeshiVals[i]);
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


    private void initZhiChengPopupWindow() {
        mInflater = LayoutInflater.from(this);
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        zhichengwindow = new CommonPopupWindow(this, R.layout.yisheng_zhicheng_popupwindow_layout, ViewGroup.LayoutParams.MATCH_PARENT, screenHeight) {
            @Override
            protected void initView() {
                View view = getContentView();
                LinearLayout ll_zhicheng = (LinearLayout) view.findViewById(R.id.ll_zhicheng);
                /**
                 * 找到搜索标签的控件
                 */
                for (int i = 0; i < mkeshiVals.length; i++) {
                    LinearLayout label_tv_zhicheng_layout = (LinearLayout) mInflater.inflate(
                            R.layout.label_tv_zhicheng_layout, mFlowLayout, false);
                    TextView tv = label_tv_zhicheng_layout.findViewById(R.id.tv_zhicheng);
                    tv.setText(mkeshiVals[i]);
                    final String str = tv.getText().toString();
                    //点击事件
                    label_tv_zhicheng_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    ll_zhicheng.addView(label_tv_zhicheng_layout);//添加到父View
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