package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class UserMoneyBean {

    /**
     * status : 1
     * msg : [{"addtime":"1528360227","money":"0.01"},{"addtime":"1528360167","money":"0.01"},{"addtime":"1528360135","money":"0.01"}]
     * fy : 0
     */

    private int status;
    private int fy;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFy() {
        return fy;
    }

    public void setFy(int fy) {
        this.fy = fy;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * addtime : 1528360227
         * money : 0.01
         */

        private String addtime;
        private String money;

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
