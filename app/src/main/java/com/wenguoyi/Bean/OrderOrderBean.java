package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/9
 * 功能描述：
 */
public class OrderOrderBean {

    /**
     * status : 1
     * goods : [{"title":"善存 多维元素片(29) 60片 centrum 补充维生素及矿物质","amount":"1","val":"标准装（1盒）","price":"81.00","imgurl":"/Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg"}]
     * ordertotal : 81
     * goodstotal : 81
     * yunfei : 0
     * addr : 1
     * address : {"id":"7","name":"zl","sheng":"河南省","shi":"郑州市","xian":"中原区","address":"123456","email":null,"tel":"17629345001","is_default":"-1","uid":"6","addtime":"1528533693","key":null}
     */

    private int status;
    private double ordertotal;
    private double goodstotal;
    private double yunfei;
    private int addr;
    private AddressBean address;
    private List<GoodsBean> goods;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(double ordertotal) {
        this.ordertotal = ordertotal;
    }

    public double getGoodstotal() {
        return goodstotal;
    }

    public void setGoodstotal(double goodstotal) {
        this.goodstotal = goodstotal;
    }

    public double getYunfei() {
        return yunfei;
    }

    public void setYunfei(double yunfei) {
        this.yunfei = yunfei;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class AddressBean {
        /**
         * id : 7
         * name : zl
         * sheng : 河南省
         * shi : 郑州市
         * xian : 中原区
         * address : 123456
         * email : null
         * tel : 17629345001
         * is_default : -1
         * uid : 6
         * addtime : 1528533693
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

    public static class GoodsBean {
        /**
         * title : 善存 多维元素片(29) 60片 centrum 补充维生素及矿物质
         * amount : 1
         * val : 标准装（1盒）
         * price : 81.00
         * imgurl : /Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg
         */

        private String title;
        private String amount;
        private String val;
        private String price;
        private String imgurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
