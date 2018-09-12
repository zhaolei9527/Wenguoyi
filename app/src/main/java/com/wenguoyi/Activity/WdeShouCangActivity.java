package com.wenguoyi.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Adapter.WDeShouCangAdapter;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.UserCollectBean;
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

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/6/28
 * 功能描述：
 */
public class WdeShouCangActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.rv_shoucang_list)
    WenguoyiRecycleView rvShoucangList;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.LL_empty)
    RelativeLayout LLEmpty;
    private int p = 1;
    private SakuraLinearLayoutManager line1;
    private Dialog dialog;

    @Override
    protected int setthislayout() {
        return R.layout.activity_wdeshoucang_layout;
    }

    @Override
    protected void initview() {
        line1 = new SakuraLinearLayoutManager(context);
        rvShoucangList.setLayoutManager(line1);
        rvShoucangList.setItemAnimator(new DefaultItemAnimator());
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallRotate);
        progressView.setIndicatorColor(getResources().getColor(R.color.colorAccent));
        rvShoucangList.setFootLoadingView(progressView);
        rvShoucangList.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                p = p + 1;
                getData();
            }
        });
        rvShoucangList.setFootLoadingView(progressView);
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

        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            dialog.show();
            getData();
        } else {
            EasyToast.showLong(context, R.string.Networkexception);
        }

    }
    private WDeShouCangAdapter wDeShouCangAdapter;

    //数据获取
    public void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("page", String.valueOf(p));
        Log.e("WdeShouCangActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/collect", "user/collect", params, new VolleyInterface(context) {

            @Override
            public void onMySuccess(String result) {
                try {
                    Log.e("WdeShouCangActivity", result);
                    dialog.dismiss();
                    UserCollectBean userCollectBean = new Gson().fromJson(result, UserCollectBean.class);
                    if (1 == userCollectBean.getStatus()) {
                        LLEmpty.setVisibility(View.GONE);
                        if (p == 1) {
                            if (wDeShouCangAdapter == null) {
                                wDeShouCangAdapter = new WDeShouCangAdapter(WdeShouCangActivity.this, userCollectBean.getMsg());
                            }
                            rvShoucangList.setAdapter(wDeShouCangAdapter);
                        } else {
                            wDeShouCangAdapter.setDatas(userCollectBean.getMsg());
                        }
                        rvShoucangList.loadMoreComplete();

                        if (0 == userCollectBean.getFy()) {
                            rvShoucangList.loadMoreEnd();
                            rvShoucangList.setCanloadMore(false);
                        } else {
                            rvShoucangList.setCanloadMore(true);
                        }

                    } else if (2 == userCollectBean.getStatus()) {
                        rvShoucangList.loadMoreEnd();
                        rvShoucangList.setCanloadMore(false);
                        EasyToast.showShort(context, R.string.notmore);
                        if (p == 1) {
                            LLEmpty.setVisibility(View.VISIBLE);
                        }
                    } else {
                        EasyToast.showShort(context, R.string.notmore);
                    }
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();

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
}
