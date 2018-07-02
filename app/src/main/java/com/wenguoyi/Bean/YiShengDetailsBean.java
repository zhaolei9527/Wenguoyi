package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/4
 * 功能描述：
 */
public class YiShengDetailsBean {

    /**
     * status : 1
     * msg : {"id":"3","name":"昝朝元","head":"/Public/uploads/news/2018-05-18/5afeea0301541.jpg","kid":"7","zid":"2","shanchang":"从事临床一线工作多年，擅长诊治荨麻疹 过敏性皮炎 神经性皮炎 体癣 股癣 香港脚 白癜风 系统性红斑狼疮 梅毒 尖锐湿疣 外阴瘙痒 阴道炎 外阴白班病 皮肤瘙痒症 少年白发 脂溢性皮炎等","info":"男，毕业于成都中医药大学，擅长皮肤性病诊治。有丰富的临床经验。","addtime":"1526655491","sort":"100","yiyuan":"成都中医药大学二附院","keshi":"皮肤性病科","zhicheng":"副主任医师"}
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
         * id : 3
         * name : 昝朝元
         * head : /Public/uploads/news/2018-05-18/5afeea0301541.jpg
         * kid : 7
         * zid : 2
         * shanchang : 从事临床一线工作多年，擅长诊治荨麻疹 过敏性皮炎 神经性皮炎 体癣 股癣 香港脚 白癜风 系统性红斑狼疮 梅毒 尖锐湿疣 外阴瘙痒 阴道炎 外阴白班病 皮肤瘙痒症 少年白发 脂溢性皮炎等
         * info : 男，毕业于成都中医药大学，擅长皮肤性病诊治。有丰富的临床经验。
         * addtime : 1526655491
         * sort : 100
         * yiyuan : 成都中医药大学二附院
         * keshi : 皮肤性病科
         * zhicheng : 副主任医师
         */

        private String id;
        private String name;
        private String head;
        private String kid;
        private String zid;
        private String shanchang;
        private String info;
        private String addtime;
        private String sort;
        private String yiyuan;
        private String keshi;
        private String zhicheng;

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

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getKid() {
            return kid;
        }

        public void setKid(String kid) {
            this.kid = kid;
        }

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getShanchang() {
            return shanchang;
        }

        public void setShanchang(String shanchang) {
            this.shanchang = shanchang;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getYiyuan() {
            return yiyuan;
        }

        public void setYiyuan(String yiyuan) {
            this.yiyuan = yiyuan;
        }

        public String getKeshi() {
            return keshi;
        }

        public void setKeshi(String keshi) {
            this.keshi = keshi;
        }

        public String getZhicheng() {
            return zhicheng;
        }

        public void setZhicheng(String zhicheng) {
            this.zhicheng = zhicheng;
        }
    }
}
