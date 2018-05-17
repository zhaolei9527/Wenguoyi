package com.wenguoyi.View;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wenguoyi.R;
import com.wenguoyi.Utils.Utils;


/**
 * Created by 赵磊 on 2017/9/25.
 */

public  class XieYiDialog extends Dialog implements View.OnClickListener {
    private WebView forum_context;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public XieYiDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public XieYiDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
    }

    public XieYiDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected XieYiDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public XieYiDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public XieYiDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public XieYiDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_xieyi);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        forum_context = (WebView) findViewById(R.id.forum_context);
        titleTxt = (TextView) findViewById(R.id.title);
        submitTxt = (TextView) findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView) findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);
        Log.e("XieYiDialog", content);

        forum_context.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                int w = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED);
                //重新测量
                webView.measure(w, h);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (getContext() != null) {
                    Toast.makeText(getContext(), R.string.hasError, Toast.LENGTH_SHORT).show();
                }
            }
        });
        forum_context.post(new Runnable() {
            @Override
            public void run() {
                String decode = Utils.decode(content);
                Spanned spanned = Html.fromHtml(decode);
                Utils.inSetWebView(spanned.toString(), forum_context, getContext());
            }
        });
        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                break;
            case R.id.submit:
                if (listener != null) {
                    listener.onClick(this, true);
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}