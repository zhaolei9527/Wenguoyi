package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/4
 * 功能描述：
 */
public class WenYiBean {


    /**
     * status : 1
     * yisheng : [{"id":"3","kid":"7","zid":"2","name":"昝朝元","head":"/Public/uploads/news/2018-05-18/5afeea0301541.jpg","keshi":"皮肤性病科","zhicheng":"副主任医师"},{"id":"5","kid":"4","zid":"2","name":"李隆文","head":"/Public/uploads/news/2018-05-18/5afeea618dcf2.jpg","keshi":"神经内科","zhicheng":"副主任医师"},{"id":"4","kid":"1","zid":"2","name":"刘国英","head":"/Public/uploads/news/2018-05-18/5afeea2e0e9ff.jpg","keshi":"妇产科","zhicheng":"副主任医师"},{"id":"2","kid":"11","zid":"4","name":"何玉宝","head":"/Public/uploads/news/2018-05-18/5afee9cdaac00.png","keshi":"骨科","zhicheng":"主治医师"},{"id":"1","kid":"5","zid":"1","name":"谭剑敏","head":"/Public/uploads/news/2018-05-18/5afee952a8c9c.jpg","keshi":"泌尿外科","zhicheng":"主任医师"}]
     * lunbo : [{"imgurl":"/Public/uploads/news/2018-05-21/5b024fa5cb1d4.png"},{"imgurl":"/Public/uploads/news/2018-05-21/5b024f4e297d6.png"}]
     * keshi : [{"id":"1","title":"妇产科"},{"id":"2","title":"儿科"},{"id":"3","title":"综合门诊"},{"id":"4","title":"神经内科"},{"id":"5","title":"泌尿外科"},{"id":"6","title":"中医科"}]
     * cart_num : 0
     */

    private int status;
    private int cart_num;
    private List<YishengBean> yisheng;
    private List<LunboBean> lunbo;
    private List<KeshiBean> keshi;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCart_num() {
        return cart_num;
    }

    public void setCart_num(int cart_num) {
        this.cart_num = cart_num;
    }

    public List<YishengBean> getYisheng() {
        return yisheng;
    }

    public void setYisheng(List<YishengBean> yisheng) {
        this.yisheng = yisheng;
    }

    public List<LunboBean> getLunbo() {
        return lunbo;
    }

    public void setLunbo(List<LunboBean> lunbo) {
        this.lunbo = lunbo;
    }

    public List<KeshiBean> getKeshi() {
        return keshi;
    }

    public void setKeshi(List<KeshiBean> keshi) {
        this.keshi = keshi;
    }

    public static class YishengBean {
        /**
         * id : 3
         * kid : 7
         * zid : 2
         * name : 昝朝元
         * head : /Public/uploads/news/2018-05-18/5afeea0301541.jpg
         * keshi : 皮肤性病科
         * zhicheng : 副主任医师
         */

        private String id;
        private String kid;
        private String zid;
        private String name;
        private String head;
        private String keshi;
        private String zhicheng;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

    public static class LunboBean {
        /**
         * imgurl : /Public/uploads/news/2018-05-21/5b024fa5cb1d4.png
         */

        private String imgurl;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

    public static class KeshiBean {
        /**
         * id : 1
         * title : 妇产科
         */

        private String id;
        private String title;

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
    }
}
