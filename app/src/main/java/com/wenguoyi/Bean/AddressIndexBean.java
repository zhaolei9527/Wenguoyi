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
     * code : 1
     * msg : 查询成功,返回数据
     * list : [{"id":"48","name":"Jayme","province":"河北","city":"石家庄","country":"长安区","address":"eee","tel":"17629345001","is_default":"0","uid":"10","add_time":"1491380375","ni_name":"靓帝女神"},{"id":"57","name":"jiaxiangkangjiaxiangkangjiaxiangkangjiaxiangkangjiaxiangkangjiaxiangkangjiaxiangkangjiaxiangkangjiax","province":"山西","city":"太原","country":"小店区","address":"店小店小店小店小店小店小店小店小店小店小店小店","tel":"17629345001","is_default":"0","uid":"10","add_time":"1491380609","ni_name":"靓帝女神"},{"id":"73","name":"贾向康","province":"河南","city":"郑州","country":"中牟县","address":"中牟","tel":"17629345001","is_default":"1","uid":"10","add_time":"1491380321","ni_name":"靓帝女神"}]
     */

    private String code;
    private String msg;
    private List<ListBean> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 48
         * name : Jayme
         * province : 河北
         * city : 石家庄
         * country : 长安区
         * address : eee
         * tel : 17629345001
         * is_default : 0
         * uid : 10
         * add_time : 1491380375
         * ni_name : 靓帝女神
         */

        private String id;
        private String name;
        private String province;
        private String city;
        private String country;
        private String address;
        private String tel;
        private String is_default;
        private String uid;
        private String add_time;
        private String ni_name;

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getNi_name() {
            return ni_name;
        }

        public void setNi_name(String ni_name) {
            this.ni_name = ni_name;
        }
    }
}
