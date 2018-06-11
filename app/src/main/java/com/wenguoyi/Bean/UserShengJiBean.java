package com.wenguoyi.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/11
 * 功能描述：
 */
public class UserShengJiBean {

    /**
     * status : 1
     * msg : {"appId":"wx82670e4f447fbb75","mch_id":"1507215981","nonceStr":"a6xy7ke076po5ena1ae1k6we5dt2x72f","package":"Sign=WXPay","prepay_id":"wx111624129091668be0005ac62082821891","timeStamp":1528705452,"sign":"C29E52D1B7E3955DAAD0B7483E47893B"}
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
         * appId : wx82670e4f447fbb75
         * mch_id : 1507215981
         * nonceStr : a6xy7ke076po5ena1ae1k6we5dt2x72f
         * package : Sign=WXPay
         * prepay_id : wx111624129091668be0005ac62082821891
         * timeStamp : 1528705452
         * sign : C29E52D1B7E3955DAAD0B7483E47893B
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
