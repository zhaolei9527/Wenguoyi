package com.wenguoyi.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/13
 * 功能描述：
 */
public class AddressIndexBean {

    /**
     * status : 1
     * msg : [{"id":"4","name":"zl","sheng":"河南省","shi":"郑州市","xian":"中原区","address":"jingsanlu","email":null,"tel":"17629345001","is_default":"1","uid":"6","addtime":"1528333634","key":null}]
     */

    private int status;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
         * name : zl
         * sheng : 河南省
         * shi : 郑州市
         * xian : 中原区
         * address : jingsanlu
         * email : null
         * tel : 17629345001
         * is_default : 1
         * uid : 6
         * addtime : 1528333634
         * key : null
         */

        private String id;
        private String name;
        private String sheng;
        private String shi;
        private String xian;
        private String address;
        private Object email;
        private String tel;
        private String is_default;
        private String uid;
        private String addtime;
        private Object key;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getXian() {
            return xian;
        }

        public void setXian(String xian) {
            this.xian = xian;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }
    }
}
