package com.wenguoyi.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/11/29
 * 功能描述：
 */
public class LoginBean {


    /**
     * status : 1
     * msg : 恭喜你，登陆成功！
     * user : {"id":"6","tel":"17629345001","nickname":"Sakura","status":"1"}
     */

    private int status;
    private String msg;
    private UserBean user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 6
         * tel : 17629345001
         * nickname : Sakura
         * status : 1
         */

        private String id;
        private String tel;
        private String nickname;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
