package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class UserTxRecordBean {


    /**
     * status : 1
     * msg : [{"id":"2","money":"1.00","addtime":"1528365693","status":"-1"}]
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
         * id : 2
         * money : 1.00
         * addtime : 1528365693
         * status : -1
         */

        private String id;
        private String money;
        private String addtime;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
