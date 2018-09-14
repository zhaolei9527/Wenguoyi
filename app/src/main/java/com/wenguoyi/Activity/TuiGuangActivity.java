package com.wenguoyi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class TuiGuangActivity extends BaseActivity {

    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_share)
    TextView tvShare;
    private CodeBean codeBean;

    @Override
    protected int setthislayout() {
        return R.layout.activity_tuiguang_layout;
    }

    @Override
    protected void initview() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initListener() {
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });
    }

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void getData() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("TuiGuangActivity", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/geterweima", "user/geterweima", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("TuiGuangActivity", result);
                try {
                    codeBean = new Gson().fromJson(result, CodeBean.class);
                    if (1 == codeBean.getStatus()) {
                        SimpleDraweeView.setImageURI(UrlUtils.URL + codeBean.getMsg());
                    } else {
                    }
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

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setUrl(codeBean.getUrl());
        oks.setSiteUrl(codeBean.getUrl());
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("欢迎加入问国医");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(codeBean.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText("扫描二维码加入我们吧");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(UrlUtils.URL + codeBean.getMsg());//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        // 启动分享GUI
        oks.show(this);
    }

}
