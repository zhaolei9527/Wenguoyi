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
     * msg : {"name":"韩磊","tel":"17629345001","card_no":"412726199807103758","xueli":null,"sex":"1","zhiye":null,"email":null,"bank":null,"bno":null,"address":null,"pic":"/Public/uploads/tupian/2018-06-06/152827349035940.jpg","pic2":"/Public/uploads/tupian/2018-06-06/152827349059042.jpg","pic3":"/Public/uploads/tupian/2018-06-06/152827349096600.jpg","is_rz":"1","cause":"不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过"}
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
         * name : 韩磊
         * tel : 17629345001
         * card_no : 412726199807103758
         * xueli : null
         * sex : 1
         * zhiye : null
         * email : null
         * bank : null
         * bno : null
         * address : null
         * pic : /Public/uploads/tupian/2018-06-06/152827349035940.jpg
         * pic2 : /Public/uploads/tupian/2018-06-06/152827349059042.jpg
         * pic3 : /Public/uploads/tupian/2018-06-06/152827349096600.jpg
         * is_rz : 1
         * cause : 不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过不通过
         */

        private String name;
        private String tel;
        private String card_no;
        private String xueli;
        private String sex;
        private String zhiye;
        private String email;
        private String bank;
        private String bno;
        private String address;
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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getXueli() {
            return xueli;
        }

        public void setXueli(String xueli) {
            this.xueli = xueli;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getZhiye() {
            return zhiye;
        }

        public void setZhiye(String zhiye) {
            this.zhiye = zhiye;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBno() {
            return bno;
        }

        public void setBno(String bno) {
            this.bno = bno;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
