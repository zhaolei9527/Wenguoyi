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
     * normname : [{"nid":"9","title":"套餐","term":[{"tid":"19","title":"标准装（1盒）"},{"tid":"20","title":"套餐二（2盒）"}]}]
     * status : 1
     * goods : {"id":"19","gname":"善存 多维元素片(29) 60片 centrum 补充维生素及矿物质","is_norm":"1","price":"81.00","price1":"81.00","price2":"80.00","price3":"78.00","price4":"75.00","is_coll":0}
     * imgs : ["/Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg","/Public/uploads/goods/2018-05-28/2018_05_28_09_33_57_16797.jpg","/Public/uploads/goods/2018-05-28/2018_05_28_09_34_01_93954.jpg"]
     * usertype : 1
     */

    private int status;
    private GoodsBean goods;
    private String usertype;
    private List<NormnameBean> normname;
    private List<String> imgs;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GoodsBean getGoods() {
        return goods;
    }

    public void setGoods(GoodsBean goods) {
        this.goods = goods;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public List<NormnameBean> getNormname() {
        return normname;
    }

    public void setNormname(List<NormnameBean> normname) {
        this.normname = normname;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public static class GoodsBean {
        /**
         * id : 19
         * gname : 善存 多维元素片(29) 60片 centrum 补充维生素及矿物质
         * is_norm : 1
         * price : 81.00
         * price1 : 81.00
         * price2 : 80.00
         * price3 : 78.00
         * price4 : 75.00
         * is_coll : 0
         */

        private String id;
        private String gname;

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        private String nums;
        private String is_norm;
        private String price;
        private String price1;
        private String price2;
        private String price3;
        private String price4;
        private int is_coll;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getIs_norm() {
            return is_norm;
        }

        public void setIs_norm(String is_norm) {
            this.is_norm = is_norm;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice1() {
            return price1;
        }

        public void setPrice1(String price1) {
            this.price1 = price1;
        }

        public String getPrice2() {
            return price2;
        }

        public void setPrice2(String price2) {
            this.price2 = price2;
        }

        public String getPrice3() {
            return price3;
        }

        public void setPrice3(String price3) {
            this.price3 = price3;
        }

        public String getPrice4() {
            return price4;
        }

        public void setPrice4(String price4) {
            this.price4 = price4;
        }

        public int getIs_coll() {
            return is_coll;
        }

        public void setIs_coll(int is_coll) {
            this.is_coll = is_coll;
        }
    }

    public static class NormnameBean {
        /**
         * nid : 9
         * title : 套餐
         * term : [{"tid":"19","title":"标准装（1盒）"},{"tid":"20","title":"套餐二（2盒）"}]
         */

        private String nid;
        private String title;
        private List<TermBean> term;

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<TermBean> getTerm() {
            return term;
        }

        public void setTerm(List<TermBean> term) {
            this.term = term;
        }

        public static class TermBean {
            /**
             * tid : 19
             * title : 标准装（1盒）
             */

            private String tid;
            private String title;

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
