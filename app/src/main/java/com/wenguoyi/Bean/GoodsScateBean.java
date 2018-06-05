package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/5
 * 功能描述：
 */
public class GoodsScateBean {

    /**
     * status : 1
     * msg : [{"id":"7","title":"心脑血管","tcate":[{"id":"17","title":"脑血管病","imgurl":"/Public/uploads/goodscate/2018-05-15/5afadf85d52d6.png"},{"id":"16","title":"冠心病","imgurl":"/Public/uploads/goodscate/2018-05-15/5afadf236c498.png"},{"id":"14","title":"高血压","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa95890895e.png"}]},{"id":"6","title":"风湿骨科","tcate":[{"id":"13","title":"颈椎病","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa95550b088.png"},{"id":"12","title":"关节炎","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa950f246ca.png"},{"id":"11","title":"痛风","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa94f46f7db.png"}]},{"id":"2","title":"消化系统","tcate":[{"id":"10","title":"肠胃溃疡","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa94be4a1d2.png"},{"id":"9","title":"腹泻","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa9486e26ab.png"},{"id":"8","title":"胃炎","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa942f6431d.png"},{"id":"4","title":"消化不良","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa82ccc1134.png"}]}]
     */

    private int status;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * id : 7
         * title : 心脑血管
         * tcate : [{"id":"17","title":"脑血管病","imgurl":"/Public/uploads/goodscate/2018-05-15/5afadf85d52d6.png"},{"id":"16","title":"冠心病","imgurl":"/Public/uploads/goodscate/2018-05-15/5afadf236c498.png"},{"id":"14","title":"高血压","imgurl":"/Public/uploads/goodscate/2018-05-15/5afa95890895e.png"}]
         */

        private String id;
        private String title;
        private List<TcateBean> tcate;

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

        public List<TcateBean> getTcate() {
            return tcate;
        }

        public void setTcate(List<TcateBean> tcate) {
            this.tcate = tcate;
        }

        public static class TcateBean {
            /**
             * id : 17
             * title : 脑血管病
             * imgurl : /Public/uploads/goodscate/2018-05-15/5afadf85d52d6.png
             */

            private String id;
            private String title;
            private String imgurl;

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

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }
        }
    }
}
