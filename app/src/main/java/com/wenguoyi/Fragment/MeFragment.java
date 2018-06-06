package com.wenguoyi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wenguoyi.Activity.AddressActivitry;
import com.wenguoyi.Activity.HuiYuanSJActivity;
import com.wenguoyi.Activity.MyMessageActivity;
import com.wenguoyi.Bean.UserIndexBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * com.wenguoyi.Fragment
 *
 * @author 赵磊
 * @date 2018/5/23
 * 功能描述：
 */
public class MeFragment extends BaseLazyFragment implements View.OnClickListener {
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.SimpleDraweeView)
    com.facebook.drawee.view.SimpleDraweeView SimpleDraweeView;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_userid)
    TextView tvUserid;
    @BindView(R.id.tv_userlv)
    TextView tvUserlv;
    @BindView(R.id.btn_shengji)
    Button btnShengji;
    @BindView(R.id.tv_daizhifu)
    TextView tvDaizhifu;
    @BindView(R.id.tv_daifahuo)
    TextView tvDaifahuo;
    @BindView(R.id.tv_daishouhuo)
    TextView tvDaishouhuo;
    @BindView(R.id.rl_dingdan)
    LinearLayout rlDingdan;
    @BindView(R.id.rl_gerenziliao)
    LinearLayout rlGerenziliao;
    @BindView(R.id.rl_shouhuodizhi)
    LinearLayout rlShouhuodizhi;
    @BindView(R.id.rl_wdetuandui)
    LinearLayout rlWdetuandui;
    @BindView(R.id.rl_wyaotuiguang)
    LinearLayout rlWyaotuiguang;
    @BindView(R.id.rl_wyaochongzhi)
    LinearLayout rlWyaochongzhi;
    @BindView(R.id.rl_wdeqianbao)
    LinearLayout rlWdeqianbao;
    @BindView(R.id.rl_wdeguzhi)
    LinearLayout rlWdeguzhi;
    @BindView(R.id.rl_guanyugongsi)
    LinearLayout rlGuanyugongsi;
    @BindView(R.id.rl_kefuzhongxin)
    LinearLayout rlKefuzhongxin;
    @BindView(R.id.rl_zijianzhuanjia)
    LinearLayout rlZijianzhuanjia;
    @BindView(R.id.rl_xitongshezhi)
    LinearLayout rlXitongshezhi;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_guzhi)
    TextView tvGuzhi;
    Unbinder unbinder;
    private Context context;

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {

        btnShengji.setOnClickListener(this);
        rlGerenziliao.setOnClickListener(this);
        rlShouhuodizhi.setOnClickListener(this);

    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.me_frament_layout, container, false);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

        SimpleDraweeView.setImageURI(String.valueOf(SpUtil.get(context, "Headimg", "")));
        tvUsername.setText(String.valueOf(SpUtil.get(context, "username", "")));
        tvUserlv.setText("等级:" + String.valueOf(SpUtil.get(context, "Level", "")));
        tvYue.setText("￥" + String.valueOf(SpUtil.get(context, "Money", "0")));
        tvGuzhi.setText(String.valueOf(SpUtil.get(context, "Integral", "0.00")));
        tvUserid.setText("ID:" + String.valueOf(SpUtil.get(context, "uuid", "")));
        tvDaizhifu.setText(String.valueOf(SpUtil.get(context, "Dfcount", "0")));
        tvDaifahuo.setText(String.valueOf(SpUtil.get(context, "Dfhcount", "0")));
        tvDaishouhuo.setText(String.valueOf(SpUtil.get(context, "Dscount", "0")));

        if (Utils.isConnected(context)) {
            userIndex();
        } else {
            EasyToast.showShort(context, getResources().getString(R.string.Networkexception));
        }

    }

    /**
     * 个人中心
     */
    private void userIndex() {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "0")));
        Log.e("MeFragment", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/index", "user/index", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                Log.e("MeFragment", result);
                try {
                    UserIndexBean userIndexBean = new Gson().fromJson(result, UserIndexBean.class);

                    if (1 == userIndexBean.getStatus()) {

                        SimpleDraweeView.setImageURI(userIndexBean.getUser().getHeadimg());
                        tvUsername.setText(userIndexBean.getUser().getNickname());
                        tvUserlv.setText("等级:" + userIndexBean.getUser().getLevel());
                        tvYue.setText("￥" + userIndexBean.getUser().getMoney());
                        tvGuzhi.setText(userIndexBean.getUser().getIntegral());
                        tvUserid.setText("ID:" + userIndexBean.getUser().getUid());

                        tvDaizhifu.setText(userIndexBean.getCount().getDfcount());
                        tvDaifahuo.setText(userIndexBean.getCount().getDfhcount());
                        tvDaishouhuo.setText(userIndexBean.getCount().getDscount());


                        SpUtil.putAndApply(context, "Headimg", "" + userIndexBean.getUser().getHeadimg());
                        SpUtil.putAndApply(context, "username", "" + userIndexBean.getUser().getNickname());
                        SpUtil.putAndApply(context, "Level", "" + userIndexBean.getUser().getLevel());
                        SpUtil.putAndApply(context, "Money", "" + userIndexBean.getUser().getMoney());
                        SpUtil.putAndApply(context, "Integral", "" + userIndexBean.getUser().getIntegral());
                        SpUtil.putAndApply(context, "uuid", "" + userIndexBean.getUser().getUid());
                        SpUtil.putAndApply(context, "Dfcount", "" + userIndexBean.getCount().getDfcount());
                        SpUtil.putAndApply(context, "Dfhcount", "" + userIndexBean.getCount().getDfhcount());
                        SpUtil.putAndApply(context, "Dscount", "" + userIndexBean.getCount().getDscount());
                        SpUtil.putAndApply(context, "Cartnum", "" + userIndexBean.getCount().getCart_num());

                    } else {
                        EasyToast.showShort(context, R.string.Abnormalserver);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMyError(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(context, getString(R.string.Abnormalserver), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shengji:
                startActivity(new Intent(context, HuiYuanSJActivity.class));
                break;
            case R.id.rl_gerenziliao:
                startActivity(new Intent(context, MyMessageActivity.class));
                break;
            case R.id.rl_shouhuodizhi:
                startActivity(new Intent(context, AddressActivitry.class));
                break;
            default:
                break;
        }
    }
}
