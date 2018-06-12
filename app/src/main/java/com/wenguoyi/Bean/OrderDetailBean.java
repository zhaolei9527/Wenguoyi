package com.wenguoyi.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/18
 * 功能描述：
 */
public class OrderDetailBean {

    /**
     * status : 1
     * msg : {"id":"37","gid":"19","nid":"19","val":"标准装（1盒）","price":"0.01","amount":"1","orderid":"1528698076735972","totalprice":"0.01","addressman":"zl","addressmobile":"17629345001","status":"3","exp":"中通快递","expnum":"4355345345349","addtime":"1528698076","address":"河南省郑州市中原区123456","shtime":null,"yunfei":"0","paytime":"1528698150","fhtime":"1528764760","goods_totalprice":0.01,"goods":[{"gid":"19","nid":"19","val":"标准装（1盒）","gname":"善存 多维元素片(29) 60片 centrum 补充维生素及矿物质","img":"/Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg","price":"0.01","amount":"1"}]}
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
         * id : 37
         * gid : 19
         * nid : 19
         * val : 标准装（1盒）
         * price : 0.01
         * amount : 1
         * orderid : 1528698076735972
         * totalprice : 0.01
         * addressman : zl
         * addressmobile : 17629345001
         * status : 3
         * exp : 中通快递
         * expnum : 4355345345349
         * addtime : 1528698076
         * address : 河南省郑州市中原区123456
         * shtime : null
         * yunfei : 0
         * paytime : 1528698150
         * fhtime : 1528764760
         * goods_totalprice : 0.01
         * goods : [{"gid":"19","nid":"19","val":"标准装（1盒）","gname":"善存 多维元素片(29) 60片 centrum 补充维生素及矿物质","img":"/Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg","price":"0.01","amount":"1"}]
         */

        private String id;
        private String gid;
        private String nid;
        private String val;
        private String price;
        private String amount;
        private String orderid;
        private String totalprice;
        private String addressman;
        private String addressmobile;
        private String status;
        private String exp;
        private String expnum;
        private String addtime;
        private String address;
        private Object shtime;
        private String yunfei;
        private String paytime;
        private String fhtime;
        private double goods_totalprice;
        private List<GoodsBean> goods;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getAddressman() {
            return addressman;
        }

        public void setAddressman(String addressman) {
            this.addressman = addressman;
        }

        public String getAddressmobile() {
            return addressmobile;
        }

        public void setAddressmobile(String addressmobile) {
            this.addressmobile = addressmobile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }

        public String getExpnum() {
            return expnum;
        }

        public void setExpnum(String expnum) {
            this.expnum = expnum;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getShtime() {
            return shtime;
        }

        public void setShtime(Object shtime) {
            this.shtime = shtime;
        }

        public String getYunfei() {
            return yunfei;
        }

        public void setYunfei(String yunfei) {
            this.yunfei = yunfei;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getFhtime() {
            return fhtime;
        }

        public void setFhtime(String fhtime) {
            this.fhtime = fhtime;
        }

        public double getGoods_totalprice() {
            return goods_totalprice;
        }

        public void setGoods_totalprice(double goods_totalprice) {
            this.goods_totalprice = goods_totalprice;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * gid : 19
             * nid : 19
             * val : 标准装（1盒）
             * gname : 善存 多维元素片(29) 60片 centrum 补充维生素及矿物质
             * img : /Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg
             * price : 0.01
             * amount : 1
             */

            private String gid;
            private String nid;
            private String val;
            private String gname;
            private String img;
            private String price;
            private String amount;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getNid() {
                return nid;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }

            public String getVal() {
                return val;
            }

            public void setVal(String val) {
                this.val = val;
            }

            public String getGname() {
                return gname;
            }

            public void setGname(String gname) {
                this.gname = gname;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }
        }
    }
}
