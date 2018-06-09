package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/6
 * 功能描述：
 */
public class UserIndexBean {


    /**
     * status : 1
     * count : {"dfcount":"0","dfhcount":"0","dscount":"0","cart_num":"0"}
     * user : {"headimg":"http://thirdwx.qlogo.cn/mmopen/8NziaiaYPIAK0C5w4WuicXwXZxOusWzfYBjHEkYXEWAORIf8ETYiaich1DK2G1AJMhlnIu9jVmfjwPQdHhqYHpvMu78aB8jKQzKtn/132","nickname":"Sakura","uid":"","money":0,"integral":"0.00","level":"D级合伙人"}
     */

    private int status;
    private CountBean count;
    private UserBean user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CountBean getCount() {
        return count;
    }

    public void setCount(CountBean count) {
        this.count = count;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class CountBean {
        /**
         * dfcount : 0
         * dfhcount : 0
         * dscount : 0
         * cart_num : 0
         */

        private String dfcount;
        private String dfhcount;
        private String dscount;
        private String cart_num;

        public String getDfcount() {
            return dfcount;
        }

        public void setDfcount(String dfcount) {
            this.dfcount = dfcount;
        }

        public String getDfhcount() {
            return dfhcount;
        }

        public void setDfhcount(String dfhcount) {
            this.dfhcount = dfhcount;
        }

        public String getDscount() {
            return dscount;
        }

        public void setDscount(String dscount) {
            this.dscount = dscount;
        }

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }
    }

    public static class UserBean {
        /**
         * headimg : http://thirdwx.qlogo.cn/mmopen/8NziaiaYPIAK0C5w4WuicXwXZxOusWzfYBjHEkYXEWAORIf8ETYiaich1DK2G1AJMhlnIu9jVmfjwPQdHhqYHpvMu78aB8jKQzKtn/132
         * nickname : Sakura
         * uid :
         * money : 0
         * integral : 0.00
         * level : D级合伙人
         */

        private String headimg;
        private String nickname;
        private String uid;
        private String money;
        private String integral;
        private String level;

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
