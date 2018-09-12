package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Adapter.YiShengListAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.YiShengListBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.DensityUtils;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Utils.WindowUtil;
import com.wenguoyi.View.CommonPopupWindow;
import com.wenguoyi.View.FlowLayout;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.tv_keshi)
    TextView tvKeshi;
    @BindView(R.id.tv_zhicheng)
    TextView tvZhicheng;
    @BindView(R.id.rv_yisheng_list)
    WenguoyiRecycleView rvYishengList;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private int p = 1;
    private WenguoyiRecycleView rv_yisheng_list;
    private SakuraLinearLayoutManager line;
    private WindowManager.LayoutParams lp;
    private String id = "";
    private String kid = "";
    private String zid = "";
    private String keshi = "";
    private Dialog dialog;
    private String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

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
        key = getIntent().getStringExtra("key");

    }

    @Override
    protected void initListener() {
        llKeshi.setOnClickListener(this);
        llZhicheng.setOnClickListener(this);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        dialog = Utils.showLoadingDialog(context);
        dialog.show();
        id = getIntent().getStringExtra("id");
        kid = getIntent().getStringExtra("kid");
        keshi = getIntent().getStringExtra("keshi");

        if (!TextUtils.isEmpty(keshi)) {
            tvKeshi.setText(keshi);
        }

        if (Utils.isConnected(context)) {
            getData();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }

    }

    @Override
    public void onClick(View view) {
        //PopupWindow win = window.getPopupWindow();
        //win.setAnimationStyle(R.style.animTranslate);
        switch (view.getId()) {
            case R.id.ll_keshi:
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                    keshiwindow.showAtLocation(llKeshi, Gravity.NO_GRAVITY, 0, WindowUtil.getStatusBarHeight(context) + DensityUtils.dp2px(context, 100));
                } else {
                    keshiwindow.showAsDropDown(llKeshi, 0, 0);
                }
                lp = getWindow().getAttributes();
                lp.alpha = 0.3f;

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
            case R.id.ll_zhicheng:
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
                    zhichengwindow.showAtLocation(llZhicheng, Gravity.NO_GRAVITY, 0, WindowUtil.getStatusBarHeight(context) + DensityUtils.dp2px(context, 100));
                } else {
                    zhichengwindow.showAsDropDown(llZhicheng, 0, 0);
                }
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
    private LayoutInflater mInflater;

    private void initKeShiPopupWindow(final List<YiShengListBean.KeshiBean> mkeshiVals) {
        mInflater = LayoutInflater.from(this);
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        keshiwindow = new CommonPopupWindow(this, R.layout.yisheng_keshi_popupwindow_layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view = getContentView();
                mFlowLayout = (FlowLayout) view.findViewById(R.id.id_flowlayout);
                /**
                 * 找到搜索标签的控件
                 */
                for (int i = 0; i < mkeshiVals.size(); i++) {
                    final TextView tv = (TextView) mInflater.inflate(
                            R.layout.label_tv_keshi_layout, mFlowLayout, false);
                    tv.setText(mkeshiVals.get(i).getTitle());
                    tv.setTag(String.valueOf(mkeshiVals.get(i).getId()));
                    final String str = tv.getText().toString();
                    //点击事件
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tvKeshi.setText(tv.getText());
                            tvZhicheng.setText("所有职称");
                            tvZhicheng.setTextColor(getResources().getColor(R.color.text666));
                            tvKeshi.setTextColor(getResources().getColor(R.color.bgtitle));
                            keshiwindow.getPopupWindow().dismiss();
                            p = 1;
                            zid = "";
                            key = "";
                            kid = (String) tv.getTag();
                            dialog.show();
                            getData();
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

    private void initZhiChengPopupWindow(final List<YiShengListBean.ZhichengBean> mzhichengVals) {
        mInflater = LayoutInflater.from(this);
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        zhichengwindow = new CommonPopupWindow(this,
                R.layout.yisheng_zhicheng_popupwindow_layout,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view = getContentView();
                LinearLayout ll_zhicheng = (LinearLayout) view.findViewById(R.id.ll_zhicheng);
                /**
                 * 找到搜索标签的控件
                 */
                for (int i = 0; i < mzhichengVals.size(); i++) {
                    LinearLayout label_tv_zhicheng_layout = (LinearLayout) mInflater.inflate(
                            R.layout.label_tv_zhicheng_layout, mFlowLayout, false);
                    final TextView tv = label_tv_zhicheng_layout.findViewById(R.id.tv_zhicheng);
                    tv.setText(mzhichengVals.get(i).getTitle());
                    tv.setTag(String.valueOf(mzhichengVals.get(i).getId()));
                    final String str = tv.getText().toString();
                    //点击事件
                    label_tv_zhicheng_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tvKeshi.setText("所有科室");
                            tvKeshi.setTextColor(getResources().getColor(R.color.text666));
                            tvZhicheng.setText(tv.getText());
                            tvZhicheng.setTextColor(getResources().getColor(R.color.bgtitle));
                            zhichengwindow.getPopupWindow().dismiss();
                            p = 1;
                            kid = "";
                            key = "";
                            zid = (String) tv.getTag();
                            dialog.show();
                            getData();
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
    private YiShengListAdapter shopAdapter;

    private void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        if (!TextUtils.isEmpty(kid)) {
            params.put("kid", kid);
        }
        if (!TextUtils.isEmpty(zid)) {
            params.put("zid", zid);
        }

        if (!TextUtils.isEmpty(key)) {
            params.put("key", key);
        }
        params.put("page", String.valueOf(p));
        Log.e("YiShengListActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "yisheng/lists", "yisheng/lists", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                Log.e("YiShengListActivity", result);
                try {
                    dialog.dismiss();
                    YiShengListBean yiShengListBean = new Gson().fromJson(result, YiShengListBean.class);
                    if (1 == yiShengListBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (p == 1) {
                            YiShengListBean.KeshiBean keshiBean = new YiShengListBean.KeshiBean();
                            keshiBean.setId("");
                            keshiBean.setTitle("全部科室");
                            yiShengListBean.getKeshi().add(0, keshiBean);
                            YiShengListBean.ZhichengBean zhichengBean = new YiShengListBean.ZhichengBean();
                            zhichengBean.setId("");
                            zhichengBean.setTitle("全部职称");
                            yiShengListBean.getZhicheng().add(0, zhichengBean);
                            initKeShiPopupWindow(yiShengListBean.getKeshi());
                            initZhiChengPopupWindow(yiShengListBean.getZhicheng());
                            shopAdapter = new YiShengListAdapter(YiShengListActivity.this, yiShengListBean.getYisheng());
                            rv_yisheng_list.setAdapter(shopAdapter);
                            rv_yisheng_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    startActivity(new Intent(context, YiShengDetailsActivity.class).putExtra("id", shopAdapter.getDatas().get(i).getId()));
                                }
                            });
                        } else {
                            shopAdapter.setDatas(yiShengListBean.getYisheng());
                            rv_yisheng_list.loadMoreComplete();
                        }
                        if (0 == yiShengListBean.getFy()) {
                            rv_yisheng_list.loadMoreEnd();
                            rv_yisheng_list.setCanloadMore(false);
                        } else {
                            rv_yisheng_list.setCanloadMore(true);
                        }
                    } else if (2 == yiShengListBean.getStatus()) {
                        if (1 == p) {
                            initKeShiPopupWindow(yiShengListBean.getKeshi());
                            initZhiChengPopupWindow(yiShengListBean.getZhicheng());
                            LLEmpty.setVisibility(View.VISIBLE);
                        } else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    } else {
                        EasyToast.showShort(context, R.string.notmore);
                    }
                } catch (Exception e) {
                    dialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                dialog.dismiss();
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }
}