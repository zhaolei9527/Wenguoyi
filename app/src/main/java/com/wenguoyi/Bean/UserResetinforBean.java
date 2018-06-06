package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/6
 * 功能描述：
 */
public class UserResetinforBean {

    /**
     * status : 1
     * msg : {"name":null,"card_no":null,"pic":"0","pic2":"0","pic3":"0","is_rz":"0","cause":null}
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
         * name : null
         * card_no : null
         * pic : 0
         * pic2 : 0
         * pic3 : 0
         * is_rz : 0
         * cause : null
         */

        private String name;
        private String card_no;
        private String pic;
        private String pic2;
        private String pic3;
        private String is_rz;
        private String cause;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPic2() {
            return pic2;
        }

        public void setPic2(String pic2) {
            this.pic2 = pic2;
        }

        public String getPic3() {
            return pic3;
        }

        public void setPic3(String pic3) {
            this.pic3 = pic3;
        }

        public String getIs_rz() {
            return is_rz;
        }

        public void setIs_rz(String is_rz) {
            this.is_rz = is_rz;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }
    }
}
