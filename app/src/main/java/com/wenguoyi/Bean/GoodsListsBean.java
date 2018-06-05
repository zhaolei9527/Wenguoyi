package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/5
 * 功能描述：
 */
public class GoodsListsBean {
    /**
     * status : 1
     * msg : [{"id":"5","gname":"碧生源 常菁茶（原减肥茶）2.5g*60袋","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_09_41_23_97964.jpg","price":"80.60"},{"id":"11","gname":"伊可新 维生素AD滴剂(1岁以上) 30粒 促小儿钙 吸收","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_10_36_09_82787.jpg","price":"54.60"},{"id":"19","gname":"善存 多维元素片(29) 60片 centrum 补充维生素及矿物质","imgurl":"/Public/uploads/goods/2018-05-28/2018_05_28_09_33_53_82652.jpg","price":"81.00"},{"id":"17","gname":"李施德林进口漱口水 除口臭 去牙渍 口气清新 除异味防蛀 天然橙味 500ml","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_16_30_10_82204.jpg","price":"26.80"},{"id":"8","gname":"禾博士 氨糖软骨素加钙片 1.0g*60片","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_09_51_23_61146.jpg","price":"69.30"},{"id":"7","gname":"草晶华 黄芪破壁中药饮片 2g*20袋 草本精华*6件","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_09_48_04_56928.jpg","price":"41.60"},{"id":"9","gname":"汤臣倍健 维生素B族片100片*2瓶","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_10_31_24_53395.jpg","price":"36.90"},{"id":"16","gname":"黑人（DARLIE）茶倍健 牙膏 190g 龙井绿茶","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_16_27_44_63940.jpg","price":"14.50"},{"id":"1","gname":"太极 藿香正气胶囊 0.3g*24粒","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_14_26_16_84187.jpg","price":"18.50"},{"id":"2","gname":"爱西特 药用炭片 0.3g*100片","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_15_50_54_74162.jpg","price":"30.50"},{"id":"3","gname":"安康信 依托考昔片 60mg*5片","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_17_20_50_85676.jpg","price":"45.00"},{"id":"4","gname":"维固力(Viartril-S) 硫酸氨基葡萄糖胶囊 0.25g*10粒","imgurl":"/Public/uploads/user/2018-05-17/2018_05_17_17_33_16_25619.jpg","price":"56.80"},{"id":"6","gname":"修正 左旋肉碱茶多酚荷叶片减肥胶囊 70片/瓶","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_09_45_04_21420.jpg","price":"125.60"},{"id":"10","gname":"康恩贝 B族维生素 0.7g*100片","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_10_33_43_83611.jpg","price":"56.30"},{"id":"13","gname":"哈药六牌 钙加锌口服液 10ml*12支","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_10_44_45_33818.jpg","price":"22.60"},{"id":"14","gname":"曼秀雷敦（Mentholatum）控油冰爽洁面泡沫150ml（男士洗面奶 洁面乳 洁面）","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_16_21_06_64810.jpg","price":"13.00"},{"id":"15","gname":"满婷清螨皂【控油祛痘皂】*100g除螨控油祛痘深层清洁","imgurl":"/Public/uploads/goods/2018-05-20/2018_05_20_16_25_36_41953.jpg","price":"5.50"},{"id":"18","gname":"康恩贝维生素B族片男女性成人维B复合VB6维生素B群b1 b2 b6 b12","imgurl":"/Public/uploads/goods/2018-05-26/2018_05_26_22_44_11_40780.jpg","price":"43.00"}]
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
         * gname : 碧生源 常菁茶（原减肥茶）2.5g*60袋
         * imgurl : /Public/uploads/goods/2018-05-20/2018_05_20_09_41_23_97964.jpg
         * price : 80.60
         */

        private String id;
        private String gname;
        private String imgurl;
        private String price;

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
