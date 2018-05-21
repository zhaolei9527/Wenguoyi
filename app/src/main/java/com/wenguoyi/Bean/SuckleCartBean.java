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
     * stu : 1
     * res : [{"id":"3","gid":"3","number":"1","title":"重磅 我有全色纯山羊绒衫女 秋冬韩版加厚款慵懒宽松套头针织毛衣","price":"12","img":"/Public/uploads/goods/img/2017-10-13/59e05e24ec608.jpg","kucun":"1212"},{"id":"6","gid":"4","number":"1","title":"基础会员：学费980元/人，一次培训课程","price":"980","img":"/Public/uploads/goods/img/2017-11-03/59fc4868b2f11.jpg","kucun":"128"},{"id":"7","gid":"3","number":"1","title":"重磅 我有全色纯山羊绒衫女 秋冬韩版加厚款慵懒宽松套头针织毛衣","price":"12","img":"/Public/uploads/goods/img/2017-10-13/59e05e24ec608.jpg","kucun":"1212"}]
     */

    private int stu;
    private List<ResBean> res;

    public int getStu() {
        return stu;
    }

    public void setStu(int stu) {
        this.stu = stu;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * id : 3
         * gid : 3
         * number : 1
         * title : 重磅 我有全色纯山羊绒衫女 秋冬韩版加厚款慵懒宽松套头针织毛衣
         * price : 12
         * img : /Public/uploads/goods/img/2017-10-13/59e05e24ec608.jpg
         * kucun : 1212
         */

        private String id;
        private String gid;
        private String number;
        private String title;
        private String price;
        private String img;
        private String kucun;
        private boolean isCheck = false;


        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getKucun() {
            return kucun;
        }

        public void setKucun(String kucun) {
            this.kucun = kucun;
        }
    }
}
