package com.wenguoyi.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/19
 * 功能描述：
 */
public class OrderPay {

    /**
     * code : 1
     * msg : 订单提交成功
     * order : {"id":"37","orderid":"15136470622038454","totalprice":"12","sfmoney":"12.00"}
     * is_wx : 0
     */

    private String code;
    private String msg;
    private OrderBean order;
    private int is_wx;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public int getIs_wx() {
        return is_wx;
    }

    public void setIs_wx(int is_wx) {
        this.is_wx = is_wx;
    }

    public static class OrderBean {
        /**
         * id : 37
         * orderid : 15136470622038454
         * totalprice : 12
         * sfmoney : 12.00
         */

        private String id;
        private String orderid;
        private String totalprice;
        private String sfmoney;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getSfmoney() {
            return sfmoney;
        }

        public void setSfmoney(String sfmoney) {
            this.sfmoney = sfmoney;
        }
    }
}
