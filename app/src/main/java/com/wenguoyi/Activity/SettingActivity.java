package com.wenguoyi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.View.CommomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Activity
 *
 * @author 赵磊
 * @date 2018/5/29
 * 功能描述：
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    FrameLayout rlBack;
    @BindView(R.id.tv_updata)
    TextView tvUpdata;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.tv_change_psw)
    TextView tvChangePsw;
    @BindView(R.id.tv_exit)
    TextView tvExit;

    @Override
    protected int setthislayout() {
        return R.layout.activity_setting_layout;
    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvChangePsw.setOnClickListener(this);
        tvClear.setOnClickListener(this);
        tvExit.setOnClickListener(this);
        tvUpdata.setOnClickListener(this);

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
        switch (view.getId()) {
            case R.id.tv_updata:
                EasyToast.showShort(context, "已是最新版本~");
                break;
            case R.id.tv_clear:
                EasyToast.showShort(context, "清除完成~");
                break;
            case R.id.tv_exit:
                new CommomDialog(context, R.style.dialog, "您确定退出登录么？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, final boolean confirm) {
                        if (confirm) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            SpUtil.clear(context);
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                }).setTitle("提示").show();
                break;
            case R.id.tv_change_psw:
                startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            default:

                break;
        }
    }
}
