package com.wenguoyi.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/8
 * 功能描述：
 */
public class GoodsDetailBean {


    /**
     * goods : {"id":"3","title":"心血康2","img":["/Public/uploads/goods/img/2017-10-09/59dac67e86470.jpg","/Public/uploads/goods/img/2017-10-09/59dac6826f158.jpg","/Public/uploads/goods/img/2017-10-09/59dac6854af38.jpg"],"keywords":"心血康2","description":"心血康2心血康2心血康2","content":"&lt;p&gt;心血康2心血康2心血康2&lt;/p&gt;","addtime":"1502708470","sort":"0","price":"33","is_show":"1","kucun":"100","yunfei":"22.00","xiaoliang":"73","type":"1","cang":"0","paytime":null,"paynum":null}
     * is_cang : 0
     * pj : {"id":"2","orderid":"15078770237362934","gid":"3","uid":"9","content":"一次就见效，真是好","xing":"5","addtime":"1507877859","stu":"1","hf":null,"admin":null,"hftime":null,"ogid":"19","count":"1","nickname":"靓**神","headimg":"/Public/uploads/headimg/default_img.png"}
     * code : 310
     * status : 1
     */

    private GoodsBean goods;
    private String is_cang;
    private PjBean pj;
    private String code;
    private String status;

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public String getIs_cang() {
        return is_cang;
    }

    public void setIs_cang(String is_cang) {
        this.is_cang = is_cang;
    }

    public PjBean getPj() {
        return pj;
    }

    public void setPj(PjBean pj) {
        this.pj = pj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class GoodsBean {
        /**
         * id : 3
         * title : 心血康2
         * img : ["/Public/uploads/goods/img/2017-10-09/59dac67e86470.jpg","/Public/uploads/goods/img/2017-10-09/59dac6826f158.jpg","/Public/uploads/goods/img/2017-10-09/59dac6854af38.jpg"]
         * keywords : 心血康2
         * description : 心血康2心血康2心血康2
         * content : &lt;p&gt;心血康2心血康2心血康2&lt;/p&gt;
         * addtime : 1502708470
         * sort : 0
         * price : 33
         * is_show : 1
         * kucun : 100
         * yunfei : 22.00
         * xiaoliang : 73
         * type : 1
         * cang : 0
         * paytime : null
         * paynum : null
         */

        private String id;
        private String title;
        private String keywords;
        private String description;
        private String content;
        private String addtime;
        private String sort;
        private String price;
        private String is_show;
        private String kucun;
        private String yunfei;
        private String xiaoliang;
        private String type;
        private String cang;
        private Object paytime;
        private Object paynum;
        private List<String> img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getIs_show() {
            return is_show;
        }

        public void setIs_show(String is_show) {
            this.is_show = is_show;
        }

        public String getKucun() {
            return kucun;
        }

        public void setKucun(String kucun) {
            this.kucun = kucun;
        }

        public String getYunfei() {
            return yunfei;
        }

        public void setYunfei(String yunfei) {
            this.yunfei = yunfei;
        }

        public String getXiaoliang() {
            return xiaoliang;
        }

        public void setXiaoliang(String xiaoliang) {
            this.xiaoliang = xiaoliang;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCang() {
            return cang;
        }

        public void setCang(String cang) {
            this.cang = cang;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public Object getPaynum() {
            return paynum;
        }

        public void setPaynum(Object paynum) {
            this.paynum = paynum;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }
    }

    public static class PjBean {
        /**
         * id : 2
         * orderid : 15078770237362934
         * gid : 3
         * uid : 9
         * content : 一次就见效，真是好
         * xing : 5
         * addtime : 1507877859
         * stu : 1
         * hf : null
         * admin : null
         * hftime : null
         * ogid : 19
         * count : 1
         * nickname : 靓**神
         * headimg : /Public/uploads/headimg/default_img.png
         */

        private String id;
        private String orderid;
        private String gid;
        private String uid;
        private String content;
        private String xing;
        private String addtime;
        private String stu;
        private String hf;
        private String admin;
        private String hftime;
        private String ogid;
        private String count;
        private String nickname;
        private String headimg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getXing() {
            return xing;
        }

        public void setXing(String xing) {
            this.xing = xing;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getStu() {
            return stu;
        }

        public void setStu(String stu) {
            this.stu = stu;
        }

        public String getHf() {
            return hf;
        }

        public void setHf(String hf) {
            this.hf = hf;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getHftime() {
            return hftime;
        }

        public void setHftime(String hftime) {
            this.hftime = hftime;
        }

        public String getOgid() {
            return ogid;
        }

        public void setOgid(String ogid) {
            this.ogid = ogid;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
    }
}
