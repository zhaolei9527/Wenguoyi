package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/5
 * 功能描述：
 */
public class GoodsFcateBean {

    /**
     * status : 1
     * msg : [{"id":"1","title":"中西药品"},{"id":"3","title":"滋补保健"},{"id":"5","title":"维生素钙"},{"id":"33","title":"药妆个护"},{"id":"34","title":"母婴专区"},{"id":"35","title":"医疗器械"},{"id":"54","title":"隐形眼镜"}]
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
         * id : 1
         * title : 中西药品
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
