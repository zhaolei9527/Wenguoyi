package com.wenguoyi.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/23
 * 功能描述：
 */
public class BankEvent {
    private String mMsg;
    private String mType;

    public BankEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mType = "";
    }

    public BankEvent(String msg, String type) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mType = type;
    }

    public String getMsg() {
        return mMsg;
    }


    public String getmType() {
        return mType;
    }
}
