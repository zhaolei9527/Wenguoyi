package com.wenguoyi.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/9
 * 功能描述：
 */
public class GoodsCangBean {
    /**
     * status : 1
     * code : 307
     * msg : 成功收藏！
     */
    private String status;
    private String code;
    private String msg;
    /**
     * is_have : 1
     */

    private String is_have;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIs_have() {
        return is_have;
    }

    public void setIs_have(String is_have) {
        this.is_have = is_have;
    }
}
