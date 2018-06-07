package com.wenguoyi.Bean;

import java.util.List;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/6/7
 * 功能描述：
 */
public class UserMytjBean {

    /**
     * status : 1
     * msg : [{"id":"8","level_id":"1","headimg":"http://thirdwx.qlogo.cn/mmopen/8NziaiaYPIAK1fLlInArolQzsU6YGyLSKyib9RC4GhgR8wGq8kPrGA39cTQZaOhqzruEmmVhbsh6ibqCtVphBltnZvweTIyAQrqG/132","nickname":"飞羽","addtime":"1528081367","type":"D级合伙人"}]
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
         * id : 8
         * level_id : 1
         * headimg : http://thirdwx.qlogo.cn/mmopen/8NziaiaYPIAK1fLlInArolQzsU6YGyLSKyib9RC4GhgR8wGq8kPrGA39cTQZaOhqzruEmmVhbsh6ibqCtVphBltnZvweTIyAQrqG/132
         * nickname : 飞羽
         * addtime : 1528081367
         * type : D级合伙人
         */

        private String id;
        private String level_id;
        private String headimg;
        private String nickname;
        private String addtime;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLevel_id() {
            return level_id;
        }

        public void setLevel_id(String level_id) {
            this.level_id = level_id;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
