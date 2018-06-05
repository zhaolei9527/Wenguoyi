package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/5
 * 功能描述：
 */
public class NewsListBean {
    /**
     * status : 1
     * msg : [{"id":"53","title":"我们就是这么同日军拼命的：九死一生的抗战飞行员自述"},{"id":"43","title":"东营开展两节期间专利执法检查 进商场现场讲解"},{"id":"42","title":" 性能超980 七彩虹GTX1070商城仅3299元"},{"id":"36","title":"电竞高保真 魔磁M660京东商城售890元"},{"id":"64","title":"老司机教你装修收尾怎么做？"},{"id":"63","title":"超级连接故宫博物院：采取新方法，迎接新公众"},{"id":"61","title":"为什么人们比以前老得慢了"},{"id":"59","title":"万万没想到这个习惯能防癌"},{"id":"57","title":"山王庄兵马俑，汉代王公陪葬一套别墅和重装备保安队伍"},{"id":"54","title":"你知道江西有多少座\u201c世界级\u201d名山？"},{"id":"51","title":"父母最好的决策是放手 理想的高考志愿自己做主"},{"id":"39","title":" 畅玩主流游戏 北影GTX960商城售1299元"}]
     * fy : 0
     * newscate : [{"id":"1","cate_name":"讲堂"},{"id":"2","cate_name":"直播"},{"id":"15","cate_name":"养生"},{"id":"16","cate_name":"饮食"},{"id":"17","cate_name":"慢病调理"},{"id":"18","cate_name":"常见病"},{"id":"19","cate_name":"育儿亲子"},{"id":"20","cate_name":"两性健康"},{"id":"21","cate_name":"减肥"},{"id":"22","cate_name":"美容"}]
     */

    private int status;
    private int fy;
    private List<MsgBean> msg;
    private List<NewscateBean> newscate;

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

    public List<NewscateBean> getNewscate() {
        return newscate;
    }

    public void setNewscate(List<NewscateBean> newscate) {
        this.newscate = newscate;
    }

    public static class MsgBean {
        /**
         * id : 53
         * title : 我们就是这么同日军拼命的：九死一生的抗战飞行员自述
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

    public static class NewscateBean {
        /**
         * id : 1
         * cate_name : 讲堂
         */

        private String id;
        private String cate_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
