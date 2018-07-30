package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/7/30
 * 功能描述：
 */
public class UserDetailBean {

    /**
     * status : 1
     * msg : {"id":"4","title":"A级会员","price":"398.00","img":"/Public/uploads/user/2018-05-19/2018_05_19_13_22_58_80510.jpg","content":"&lt;p&gt;　　红枣最突出的特点是维生素含量高。鲜枣维生素含量更丰富，但它有时令性。干枣虽维生素含量下降，但铁含量升高，且其营养更易吸收，更适合食疗。每天用干枣泡水，有保肝排毒的功效。&lt;/p&gt;&lt;p style=&quot;text-align: center;&quot;&gt;&lt;img src=&quot;/ueditor/php/upload/image/20180519/1526707381913005.jpg&quot; alt=&quot;红枣泡水喝功效不一般&quot; data-link=&quot;&quot;/&gt;&lt;span class=&quot;img_descr&quot;&gt;&lt;br/&gt;&lt;/span&gt;&lt;br/&gt;&lt;/p&gt;&lt;p style=&quot;text-align: center;&quot;&gt;&lt;span class=&quot;img_descr&quot;&gt;红枣泡水喝功效不一般&lt;/span&gt;&lt;/p&gt;&lt;p&gt;　　从口味上选择，建议泡茶时选用大个的红枣，口感甜，最好将其撕成几块再泡；熬粥、泡酒等则可随意选择个大或个小的枣。吃枣并非多多益善。中等大小的枣，一次食用最好别超15个。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","gu":"12.00","rugujin":"400.00"}
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
         * id : 4
         * title : A级会员
         * price : 398.00
         * img : /Public/uploads/user/2018-05-19/2018_05_19_13_22_58_80510.jpg
         * content : &lt;p&gt;　　红枣最突出的特点是维生素含量高。鲜枣维生素含量更丰富，但它有时令性。干枣虽维生素含量下降，但铁含量升高，且其营养更易吸收，更适合食疗。每天用干枣泡水，有保肝排毒的功效。&lt;/p&gt;&lt;p style=&quot;text-align: center;&quot;&gt;&lt;img src=&quot;/ueditor/php/upload/image/20180519/1526707381913005.jpg&quot; alt=&quot;红枣泡水喝功效不一般&quot; data-link=&quot;&quot;/&gt;&lt;span class=&quot;img_descr&quot;&gt;&lt;br/&gt;&lt;/span&gt;&lt;br/&gt;&lt;/p&gt;&lt;p style=&quot;text-align: center;&quot;&gt;&lt;span class=&quot;img_descr&quot;&gt;红枣泡水喝功效不一般&lt;/span&gt;&lt;/p&gt;&lt;p&gt;　　从口味上选择，建议泡茶时选用大个的红枣，口感甜，最好将其撕成几块再泡；熬粥、泡酒等则可随意选择个大或个小的枣。吃枣并非多多益善。中等大小的枣，一次食用最好别超15个。&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;
         * gu : 12.00
         * rugujin : 400.00
         */

        private String id;
        private String title;
        private String price;
        private String img;
        private String content;
        private String gu;
        private String rugujin;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGu() {
            return gu;
        }

        public void setGu(String gu) {
            this.gu = gu;
        }

        public String getRugujin() {
            return rugujin;
        }

        public void setRugujin(String rugujin) {
            this.rugujin = rugujin;
        }
    }
}
