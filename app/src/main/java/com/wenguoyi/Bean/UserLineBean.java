package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class UserLineBean {
    /**
     * amount : 0.00
     * status : 1
     */

    private String amount;

    public String getRenminbi() {
        return renminbi;
    }

    public void setRenminbi(String renminbi) {
        this.renminbi = renminbi;
    }

    private String renminbi;
    private int status;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
