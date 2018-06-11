package com.wenguoyi.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/30
 * 功能描述：
 */
public class OrderWxpayBean {

    /**
     * status : 1
     * msg : {"appId":"wxe921d2ca987f9c35","mch_id":"1507215981","nonceStr":"3zq22uxr0wlni004ujpcnl7nqa591839","package":"Sign=WXPay","prepay_id":"wx11102015023922af50b250f32408348115","timeStamp":1528683615,"sign":"B37BD76818FF400F9423A7251C434DB7"}
     */

    private int status;
    private MsgBean msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * appId : wxe921d2ca987f9c35
         * mch_id : 1507215981
         * nonceStr : 3zq22uxr0wlni004ujpcnl7nqa591839
         * package : Sign=WXPay
         * prepay_id : wx11102015023922af50b250f32408348115
         * timeStamp : 1528683615
         * sign : B37BD76818FF400F9423A7251C434DB7
         */

        private String appId;
        private String mch_id;
        private String nonceStr;
        @SerializedName("package")
        private String packageX;
        private String prepay_id;
        private String timeStamp;
        private String sign;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
