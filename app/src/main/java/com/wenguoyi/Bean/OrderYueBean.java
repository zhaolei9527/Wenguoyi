package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/9
 * 功能描述：
 */
public class OrderYueBean {

    /**
     * pay : 0
     * oid : 27
     * msg : 订单已生成，请支付
     * status : 1
     */

    private int pay;
    private String oid;
    private String msg;
    private int status;

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
