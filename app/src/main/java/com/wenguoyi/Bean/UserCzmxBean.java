package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class UserCzmxBean {

    /**
     * status : 1
     * msg : [{"id":"4","uid":"6","xiangmu":"充值","money":"0.01","yuemoney":"0.01","addtime":"1528360253","is_admin":"0","adminid":"0","is_tui":"0","type":"1"},{"id":"1","uid":"6","xiangmu":"充值","money":"0.10","yuemoney":"0.20","addtime":"1527571418","is_admin":"0","adminid":"0","is_tui":"0","type":"1"}]
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
         * id : 4
         * uid : 6
         * xiangmu : 充值
         * money : 0.01
         * yuemoney : 0.01
         * addtime : 1528360253
         * is_admin : 0
         * adminid : 0
         * is_tui : 0
         * type : 1
         */

        private String id;
        private String uid;
        private String xiangmu;
        private String money;
        private String yuemoney;
        private String addtime;
        private String is_admin;
        private String adminid;
        private String is_tui;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getXiangmu() {
            return xiangmu;
        }

        public void setXiangmu(String xiangmu) {
            this.xiangmu = xiangmu;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getYuemoney() {
            return yuemoney;
        }

        public void setYuemoney(String yuemoney) {
            this.yuemoney = yuemoney;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
        }

        public String getAdminid() {
            return adminid;
        }

        public void setAdminid(String adminid) {
            this.adminid = adminid;
        }

        public String getIs_tui() {
            return is_tui;
        }

        public void setIs_tui(String is_tui) {
            this.is_tui = is_tui;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
