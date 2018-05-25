package com.wenguoyi.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/30
 * 功能描述：
 */
public class OrderWxpayBean {


    /**
     * stu : 1
     * data : {"appId":"wx0a74ea54b7f465bf","mch_id":"1495164722","nonceStr":"3efdrnwz64n8hosl5rtfaidzsefm8ye8","package":"Sign=WXPay","prepay_id":"wx20171230153434923ebb07330325298796","timeStamp":1514619274,"sign":"CBE5463A84EDC44B666937C754B552E4"}
     */

    private int stu;
    private DataBean data;

    public int getStu() {
        return stu;
    }

    public void setStu(int stu) {
        this.stu = stu;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appId : wx0a74ea54b7f465bf
         * mch_id : 1495164722
         * nonceStr : 3efdrnwz64n8hosl5rtfaidzsefm8ye8
         * package : Sign=WXPay
         * prepay_id : wx20171230153434923ebb07330325298796
         * timeStamp : 1514619274
         * sign : CBE5463A84EDC44B666937C754B552E4
         */

        private String appId;
        private String mch_id;
        private String nonceStr;
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
