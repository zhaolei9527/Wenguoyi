package com.wenguoyi.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
     * msg : {"name":"郑州","tel":"17629345001","card_no":"412726199807103758","xueli":"博士","sex":"1","zhiye":"人事","email":"975976959@qq.com","bank":"付邮费贵","bname":"复古风","bno":"14696578","address":"f\u2006y\u2006fu\u2006fhh","pic":"/Public/uploads/tupian/2018-08-27/153533039838726.jpeg","pic2":"/Public/uploads/tupian/2018-08-27/153533040369261.jpeg","pic3":"/Public/uploads/tupian/2018-08-27/153533041010464.jpeg","is_rz":"0","cause":null}
     */

    private int status;
    private MsgBean msg;

    public static List<UserResetinforBean> arrayUserResetinforBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<UserResetinforBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

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
         * name : 郑州
         * tel : 17629345001
         * card_no : 412726199807103758
         * xueli : 博士
         * sex : 1
         * zhiye : 人事
         * email : 975976959@qq.com
         * bank : 付邮费贵
         * bname : 复古风
         * bno : 14696578
         * address : f y fu fhh
         * pic : /Public/uploads/tupian/2018-08-27/153533039838726.jpeg
         * pic2 : /Public/uploads/tupian/2018-08-27/153533040369261.jpeg
         * pic3 : /Public/uploads/tupian/2018-08-27/153533041010464.jpeg
         * is_rz : 0
         * cause : null
         */

        private String name;
        private String tel;
        private String card_no;
        private String xueli;
        private String sex;
        private String zhiye;
        private String email;
        private String bank;
        private String bname;
        private String bno;
        private String address;
        private String pic;
        private String pic2;
        private String pic3;
        private String is_rz;
        private String cause;

        public static List<MsgBean> arrayMsgBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<MsgBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

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

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
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
