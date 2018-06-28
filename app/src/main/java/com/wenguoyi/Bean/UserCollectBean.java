package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/28
 * 功能描述：
 */
public class UserCollectBean {

    /**
     * status : 1
     * msg : [{"id":"5","gid":"8","gname":"禾博士 氨糖软骨素加钙片 1.0g*60片","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_09_51_23_61146.jpg","price":"67.30"},{"id":"4","gid":"2","gname":"爱西特 药用炭片 0.3g*100片","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_15_50_54_74162.jpg","price":"28.50"},{"id":"3","gid":"1","gname":"太极 藿香正气胶囊 0.3g*24粒","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_14_26_16_84187.jpg","price":"16.50"},{"id":"2","gid":"3","gname":"安康信 依托考昔片 60mg*5片","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_17_20_50_85676.jpg","price":"42.00"}]
     * fy : 0
     */

    private int status;
    private int fy;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFy() {
        return fy;
    }

    public void setFy(int fy) {
        this.fy = fy;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * id : 5
         * gid : 8
         * gname : 禾博士 氨糖软骨素加钙片 1.0g*60片
         * imgurl : /Public/uploads/goods/2018-05-20/2018_05_20_09_51_23_61146.jpg
         * price : 67.30
         */

        private String id;
        private String gid;
        private String gname;
        private String imgurl;
        private String price;

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
    }
}
