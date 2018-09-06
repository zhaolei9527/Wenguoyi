package com.wenguoyi.Bean;

/**
 * com.wenguoyi.Bean
 *
 * @author 赵磊
 * @date 2018/7/30
 * 功能描述：
 */
public class IsTxBean {

    /**
     * is_tx : 0
     * status : 1
     * sxf : null
     * stime : 2018/08/02
     * etime : 2018/08/05
     */

    private int is_tx;
    private int status;
    private String sxf;
    private String stime;
    private String etime;
    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


    public int getIs_tx() {
        return is_tx;
    }

    public void setIs_tx(int is_tx) {
        this.is_tx = is_tx;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSxf() {
        return sxf;
    }

    public void setSxf(String sxf) {
        this.sxf = sxf;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
