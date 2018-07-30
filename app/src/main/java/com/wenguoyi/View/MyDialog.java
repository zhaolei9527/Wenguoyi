package com.wenguoyi.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenguoyi.R;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by 赵磊 on 2017/10/9.
 */

public class MyDialog extends Dialog implements View.OnClickListener {
    Context mContext;

    private String title;
    private String url;
    private String img;
    private String text;

    public MyDialog(Context context, String title, String url, String img) {
        super(context, R.style.ActionSheetDialogStyle);
        this.mContext = context;
        this.title = title;
        this.url = url;
        this.img = img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_layout);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        LinearLayout ll_wechat = (LinearLayout) findViewById(R.id.ll_wechat);
        LinearLayout ll_wechatfriend = (LinearLayout) findViewById(R.id.ll_wechatfriend);
        tv_cancel.setOnClickListener(this);
        ll_wechat.setOnClickListener(this);
        ll_wechatfriend.setOnClickListener(this);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.ll_wechat:
                showShareWechat();
                dismiss();
                break;
            case R.id.ll_wechatfriend:
                showShareWechatfriend();
                dismiss();
                break;
            default:
                break;
        }
    }

    private void showShareWechat() {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setText(title);
        oks.setImageUrl(img);
        // TODO: 2017/11/8 此处填写下载连接
        oks.setUrl(url);
        oks.setComment(title);
        oks.setSiteUrl(url);
        oks.setPlatform(Wechat.NAME);
        oks.show(getContext());
    }

    private void showShareWechatfriend() {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setText(title);
        oks.setImageUrl(img);
        // TODO: 2017/11/8 此处填写下载连接
        oks.setUrl(url);
        oks.setComment(title);
        oks.setSiteUrl(url);
        oks.setPlatform(Wechat.NAME);
        oks.show(getContext());
    }

}