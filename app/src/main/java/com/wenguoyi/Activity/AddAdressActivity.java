package com.wenguoyi.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.wenguoyi.App;
import com.wenguoyi.Base.BaseActivity;
import com.wenguoyi.Bean.CodeBean;
import com.wenguoyi.Bean.JsonBean;
import com.wenguoyi.Bean.UserDoaddrBean;
import com.wenguoyi.R;
import com.wenguoyi.Utils.EasyToast;
import com.wenguoyi.Utils.GetJsonDataUtil;
import com.wenguoyi.Utils.SpUtil;
import com.wenguoyi.Utils.UrlUtils;
import com.wenguoyi.Utils.Utils;
import com.wenguoyi.Volley.VolleyInterface;
import com.wenguoyi.Volley.VolleyRequest;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAdressActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout rl_back;
    private EditText et_name;
    private EditText et_phone;
    private TextView tv_check_address;
    private EditText et_addressContent;
    private CheckBox btnIsChoosed;
    private Button btn_add_address;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    break;
                default:
                    break;

            }
        }
    };
    private boolean isLoaded = false;
    private String name;
    private String phone;
    private String addressContent;
    private String sheng;
    private String shi;
    private String xian;
    private Dialog dialog;
    private String id;
    private String IsChoosed;

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    private void ShowPickerView() {// 弹出选择器
        if (!options1Items.isEmpty()) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    sheng = options1Items.get(options1).getPickerViewText();
                    shi = options2Items.get(options1).get(options2);
                    xian = options3Items.get(options1).get(options2).get(options3);
                    String tx = options1Items.get(options1).getPickerViewText() +
                            options2Items.get(options1).get(options2) +
                            options3Items.get(options1).get(options2).get(options3);
                    tv_check_address.setText(tx);
                }
            })
                    .setTitleBgColor(getResources().getColor(R.color.bgtitle))
                    .setCancelColor(getResources().getColor(R.color.text))
                    .setSubmitColor(getResources().getColor(R.color.text))
                    .setTitleText("选择城市")
                    .setSelectOptions(15)//默认选中项
                    .setTitleColor(getResources().getColor(R.color.text))
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();

            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        }

    }

    @Override
    protected int setthislayout() {
        return R.layout.activity_add_adress;
    }

    @Override
    protected void initview() {
        rl_back = (FrameLayout) findViewById(R.id.rl_back);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_check_address = (TextView) findViewById(R.id.tv_check_address);
        et_addressContent = (EditText) findViewById(R.id.et_addressContent);
        btnIsChoosed = (CheckBox) findViewById(R.id.btnIsChoosed);
        btn_add_address = (Button) findViewById(R.id.btn_add_address);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
            id = getIntent().getStringExtra("id");
            IsChoosed = getIntent().getStringExtra("IsChoosed");
            if (TextUtils.equals("1", String.valueOf(IsChoosed))) {
                btnIsChoosed.setChecked(true);
            } else {
                btnIsChoosed.setChecked(false);
            }
            name = getIntent().getStringExtra("name");
            et_name.setText(name);
            phone = getIntent().getStringExtra("tel");
            et_phone.setText(phone);
            addressContent = getIntent().getStringExtra("address");
            et_addressContent.setText(addressContent);
            sheng = getIntent().getStringExtra("sheng");
            shi = getIntent().getStringExtra("shi");
            xian = getIntent().getStringExtra("xian");
            String tx = sheng +
                    shi +
                    xian;
            tv_check_address.setText(tx);


        }
    }

    @Override
    protected void initListener() {
        tv_check_address.setOnClickListener(this);
        btn_add_address.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_address:
                submit();
                break;
            case R.id.tv_check_address:
                ShowPickerView();
                break;
            case R.id.rl_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写联系电话", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Utils.isCellphone(phone)) {
            Toast.makeText(this, "请填写正确联系电话", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("请选择  >   ".equals(tv_check_address.getText())) {
            Toast.makeText(this, "请选择所在地", Toast.LENGTH_SHORT).show();
            return;
        }

        addressContent = et_addressContent.getText().toString().trim();
        if (TextUtils.isEmpty(addressContent)) {
            Toast.makeText(this, "请输入详细地址", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        if (Utils.isConnected(context)) {
            dialog = Utils.showLoadingDialog(context);
            if (!dialog.isShowing()) {
                dialog.show();
            }
            if (!TextUtils.isEmpty(id)) {
                addressDoedit();
            } else {
                addressAdd();
            }
        } else {
            EasyToast.showShort(context, "网络连接失败");
        }
    }

    /**
     * 添加收货地址
     */
    private void addressAdd() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("name", name);
        params.put("tel", phone);
        params.put("address", addressContent);
        params.put("sheng", sheng);
        params.put("shi", shi);
        params.put("xian", xian);
        if (btnIsChoosed.isChecked()) {
            params.put("is_default", "1");
        } else {
            params.put("is_default", "-1");
        }
        Log.e("addressAdd", params.toString());
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/doaddr", "user/doaddr", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("addressAdd", result);
                try {
                    UserDoaddrBean userDoaddrBean = new Gson().fromJson(result, UserDoaddrBean.class);
                    if ("1".equals(String.valueOf(userDoaddrBean.getStatus()))) {
                        EasyToast.showShort(context, "添加成功");
                        finish();
                    } else {
                        EasyToast.showShort(context, "添加失败");
                    }
                    userDoaddrBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
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

    /**
     * 修改收货地址
     */
    private void addressDoedit() {
        HashMap<String, String> params = new HashMap<>(9);
        params.put("pwd", UrlUtils.KEY);
        params.put("uid", String.valueOf(SpUtil.get(context, "uid", "")));
        params.put("id", id);
        params.put("name", name);
        params.put("tel", phone);
        params.put("address", addressContent);
        params.put("sheng", sheng);
        params.put("shi", shi);
        params.put("xian", xian);
        if (btnIsChoosed.isChecked()) {
            params.put("is_default", "1");
        } else {
            params.put("is_default", "-1");
        }
        VolleyRequest.RequestPost(context, UrlUtils.BASE_URL + "user/doaddr", "user/doaddr", params, new VolleyInterface(context) {
            @Override
            public void onMySuccess(String result) {
                dialog.dismiss();
                Log.e("addressAdd", result);
                try {
                    CodeBean codeBean = new Gson().fromJson(result, CodeBean.class);
                    if ("1".equals(String.valueOf(codeBean.getStatus()))) {
                        EasyToast.showShort(context, "修改成功");
                        finish();
                    } else {
                        EasyToast.showShort(context, "修改失败");
                    }
                    codeBean = null;
                    result = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
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
    protected void onDestroy() {
        super.onDestroy();
        App.getQueues().cancelAll("user/doaddr");
        dialog = null;
        System.gc();
    }
}
