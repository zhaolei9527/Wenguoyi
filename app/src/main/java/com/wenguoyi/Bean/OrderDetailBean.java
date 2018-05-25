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
     * stu : 1
     * goods : [{"id":"82","gid":"12","status":"0","price":"12.00","number":"1","type":"1","yfwnum":"0","img":"/Public/uploads/goods/img/2017-10-13/59e05bda67cf0.jpg","title":"出口外贸尾单秋冬羊绒衫女毛衣加厚高领菠萝针织衫打底外套保暖衫","gstatus":"1"}]
     * order : {"xian":"金水区","shi":"郑州","sheng":"河南","address":"河西大道东1027","tel":"13027607540","paytype":"1","name":"jackma","orderid":"15149632437408096","sfmoney":"0.00","totalprice":"12","yunfei":"0.00","score":"","yemoney":"12","payment":"","fh_jifen":"","addtime":"1514963243","exp":"申通快递","expnum":"14876165615651233","fhbeizhu":"","paybeizhu":"","shtime":"","stu":"3"}
     */

    private int stu;
    private OrderBean order;
    private List<GoodsBean> goods;

    public int getStu() {
        return stu;
    }

    public void setStu(int stu) {
        this.stu = stu;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class OrderBean {
        /**
         * xian : 金水区
         * shi : 郑州
         * sheng : 河南
         * address : 河西大道东1027
         * tel : 13027607540
         * paytype : 1
         * name : jackma
         * orderid : 15149632437408096
         * sfmoney : 0.00
         * totalprice : 12
         * yunfei : 0.00
         * score :
         * yemoney : 12
         * payment :
         * fh_jifen :
         * addtime : 1514963243
         * exp : 申通快递
         * expnum : 14876165615651233
         * fhbeizhu :
         * paybeizhu :
         * shtime :
         * stu : 3
         */

        private String xian;
        private String shi;
        private String sheng;
        private String address;
        private String tel;
        private String paytype;
        private String name;
        private String orderid;
        private String sfmoney;
        private String totalprice;
        private String yunfei;
        private String score;
        private String yemoney;
        private String payment;
        private String fh_jifen;
        private String addtime;
        private String exp;
        private String expnum;
        private String fhbeizhu;
        private String paybeizhu;
        private String shtime;
        private String stu;

        public String getXian() {
            return xian;
        }

        public void setXian(String xian) {
            this.xian = xian;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getSheng() {
            return sheng;
        }

        public void setSheng(String sheng) {
            this.sheng = sheng;
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

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getSfmoney() {
            return sfmoney;
        }

        public void setSfmoney(String sfmoney) {
            this.sfmoney = sfmoney;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getYunfei() {
            return yunfei;
        }

        public void setYunfei(String yunfei) {
            this.yunfei = yunfei;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getYemoney() {
            return yemoney;
        }

        public void setYemoney(String yemoney) {
            this.yemoney = yemoney;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getFh_jifen() {
            return fh_jifen;
        }

        public void setFh_jifen(String fh_jifen) {
            this.fh_jifen = fh_jifen;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
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

        public String getFhbeizhu() {
            return fhbeizhu;
        }

        public void setFhbeizhu(String fhbeizhu) {
            this.fhbeizhu = fhbeizhu;
        }

        public String getPaybeizhu() {
            return paybeizhu;
        }

        public void setPaybeizhu(String paybeizhu) {
            this.paybeizhu = paybeizhu;
        }

        public String getShtime() {
            return shtime;
        }

        public void setShtime(String shtime) {
            this.shtime = shtime;
        }

        public String getStu() {
            return stu;
        }

        public void setStu(String stu) {
            this.stu = stu;
        }
    }

    public static class GoodsBean {
        /**
         * id : 82
         * gid : 12
         * status : 0
         * price : 12.00
         * number : 1
         * type : 1
         * yfwnum : 0
         * img : /Public/uploads/goods/img/2017-10-13/59e05bda67cf0.jpg
         * title : 出口外贸尾单秋冬羊绒衫女毛衣加厚高领菠萝针织衫打底外套保暖衫
         * gstatus : 1
         */

        private String id;
        private String gid;
        private String status;
        private String price;
        private String number;
        private String type;
        private String yfwnum;
        private String img;
        private String title;
        private String gstatus;

        public String getThid() {
            return thid;
        }

        public void setThid(String thid) {
            this.thid = thid;
        }

        private String thid;


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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getYfwnum() {
            return yfwnum;
        }

        public void setYfwnum(String yfwnum) {
            this.yfwnum = yfwnum;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGstatus() {
            return gstatus;
        }

        public void setGstatus(String gstatus) {
            this.gstatus = gstatus;
        }
    }

//
//    @property (nonatomic, copy) NSString *address;//地址
//    @property (nonatomic, copy) NSString *addtime;//下单时间
//    @property (nonatomic, copy) NSString *exp;//快递名称
//    @property (nonatomic, copy) NSString *expnum;//快递单号
//    @property (nonatomic, copy) NSString *name;
//    @property (nonatomic, copy) NSString *orderid;//订单号
//    @property (nonatomic, copy) NSString *payment;//支付方式1支付宝2微信
//    @property (nonatomic, copy) NSString *paytype;//余额积分方式1余额2积分
//    @property (nonatomic, copy) NSString *fh_jifen;//返回积分
//    @property (nonatomic, copy) NSString *score;//使用积分
//    @property (nonatomic, copy) NSString *sfmoney;//支付宝/微信实付金额
//    @property (nonatomic, copy) NSString *sheng;//省
//    @property (nonatomic, copy) NSString *shi;//市
//    @property (nonatomic, copy) NSString *tel;//电话
//    @property (nonatomic, copy) NSString *totalprice;//商品价格
//    @property (nonatomic, copy) NSString *xian;//县
//    @property (nonatomic, copy) NSString *yemoney;//余额支付多少
//    @property (nonatomic, copy) NSString *yunfei;//运费
//    @property (nonatomic, copy) NSString *stu;//订单状态


}
