package com.wenguoyi.Bean;

import java.util.List;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/11
 * 功能描述：
 */
public class SuckleCartBean {
    /**
     * status : 1
     * cart : [{"gname":"安康信 依托考昔片 60mg*5片","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_17_20_50_85676.jpg","price":"85.00","nums":"500","val":"120mg|5片","id":"5","gid":"3","is_norm":"1","num":"1"},{"gname":"善存 多维元素片(29) 60片 centrum 补充维生素及矿物质","imgurl":"/Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg","price":"81.00","nums":"47","val":"标准装（1盒）","id":"6","gid":"19","is_norm":"1","num":"1"}]
     * total : 166
     */

    private int status;
    private int total;
    private List<CartBean> cart;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        /**
         * gname : 安康信 依托考昔片 60mg*5片
         * imgurl : /Public/uploads/user/2018-05-17/2018_05_17_17_20_50_85676.jpg
         * price : 85.00
         * nums : 500
         * val : 120mg|5片
         * id : 5
         * gid : 3
         * is_norm : 1
         * num : 1
         */
        private boolean isCheck = false;
        private String gname;
        private String imgurl;
        private String price;
        private String nums;
        private String val;
        private String id;
        private String gid;
        private String is_norm;
        private String num;

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

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

        public String getIs_norm() {
            return is_norm;
        }

        public void setIs_norm(String is_norm) {
            this.is_norm = is_norm;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
