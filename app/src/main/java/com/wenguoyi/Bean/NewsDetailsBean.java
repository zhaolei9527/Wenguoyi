package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/4
 * 功能描述：
 */
public class NewsDetailsBean {

    /**
     * status : 1
     * msg : {"title":"电竞高保真 魔磁M660京东商城售890元","num":45,"addtime":"1474161153","id":"36"}
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
         * title : 电竞高保真 魔磁M660京东商城售890元
         * num : 45
         * addtime : 1474161153
         * id : 36
         */

        private String title;
        private int num;
        private String addtime;
        private String id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
