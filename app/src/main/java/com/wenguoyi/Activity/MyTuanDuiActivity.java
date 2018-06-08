package com.wenguoyi.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Adapter.MyTuanDuiAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.UserMytjBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.View.ProgressView;
import com.wenguoyi.View.SakuraLinearLayoutManager;
import com.wenguoyi.View.WenguoyiRecycleView;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.fangx.haorefresh.LoadMoreListener;

import static com.wenguoyi.R.id.re_mytuandui;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/28
 * 功能描述：
 */
public class MyTuanDuiActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.ll_yiceng)
    LinearLayout llYiceng;
    @BindView(R.id.ll_erceng)
    LinearLayout llErceng;
    @BindView(re_mytuandui)
    WenguoyiRecycleView reMytuandui;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    private int p = 1;
    private SakuraLinearLayoutManager line;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_mytuandui_layout;
    }

    @Override
    protected void initview() {
        line = new SakuraLinearLayoutManager(context);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        reMytuandui.setLayoutManager(line);
        reMytuandui.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        reMytuandui.setFootLoadingView(progressView);
        reMytuandui.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData(T);
            }
        });
    }

    @Override
    protected void initListener() {
        llYiceng.setOnClickListener(this);
        llErceng.setOnClickListener(this);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    String T = "1";

    @Override
    protected void initData() {
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getData(T);
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }
    }


    private void getData(String t) {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        params.put("page", String.valueOf(p));
        params.put("t", t);
        Log.e("MyTuanDuiActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/mytj", "user/mytj", params, new VolleyInterface(context) {
            private MyTuanDuiAdapter myTuanDuiAdapter;
            @Override
            public void onMySuccess(String result) {
                Log.e("MyTuanDuiActivity", result);
                try {
                    dialog.dismiss();
                    UserMytjBean userMytjBean = new Gson().fromJson(result, UserMytjBean.class);
                    if (1 == userMytjBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (1 == p) {
                            myTuanDuiAdapter = new MyTuanDuiAdapter(MyTuanDuiActivity.this, userMytjBean.getMsg());
                            reMytuandui.setAdapter(myTuanDuiAdapter);
                        } else {
                            reMytuandui.loadMoreComplete();
                            myTuanDuiAdapter.setDatas(userMytjBean.getMsg());
                        }
                        if (0 == userMytjBean.getFy()) {
                            reMytuandui.loadMoreEnd();
                            reMytuandui.setCanloadMore(false);
                        } else {
                            reMytuandui.setCanloadMore(true);
                        }
                    } else {
                        if (1==p){
                            LLEmpty.setVisibility(View.VISIBLE);
                        }else {
                            EasyToast.showShort(context, R.string.notmore);
                        }
                    }
                    result = null;
                } catch (Exception e) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        p = 1;
        switch (view.getId()) {
            case R.id.ll_yiceng:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                textView1.setTextColor(getResources().getColor(R.color.bgtitle));
                textView2.setTextColor(getResources().getColor(R.color.text333));
                T = "1";
                initData();
                break;
            case R.id.ll_erceng:
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                textView1.setTextColor(getResources().getColor(R.color.text333));
                textView2.setTextColor(getResources().getColor(R.color.bgtitle));
                T = "2";
                initData();
                break;
            default:
                break;
        }
    }
}
