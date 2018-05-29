package com.wenguoyi.Bean;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2017/12/24
 * 功能描述：
 */
public class StuBean {

    /**
     * stu : 1
     */

    private int stu;
    /**
     * code : 100
     * error_msg : no uid or bank_id or bank_num
     */

    private int code;
    private String error_msg;

    public String getQiandao_jifen() {
        return qiandao_jifen;
    }

    public void setQiandao_jifen(String qiandao_jifen) {
        this.qiandao_jifen = qiandao_jifen;
    }

    private String qiandao_jifen;


    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    private String res;


    public int getStu() {
        return stu;
    }

    public void setStu(int stu) {
        this.stu = stu;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
