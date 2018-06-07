package com.wenguoyi.Bean;

import java.io.Serializable;

/**
 * sakura.liangdinvshen.Bean
 *
 * @author 赵磊
 * @date 2018/1/3
 * 功能描述：
 */
public class WXBean {

    /**
     * unionid : o9PFR0sYr1_qVr86eccntHNLZLeU
     * userID : om1Mu1hmcTef7IwCA0sBt8cfsEgM
     * icon : http://wx.qlogo.cn/mmopen/vi_32/gQXOjW4DB0XWyc7Rm66FNuevIRYTIGYcwIP4WyBticenDGBAJAGxROQiazySRtu2NdSUNUCaib8EmHaDibuGicsmCKw/0
     * expiresTime : 1514957660606
     * nickname : Sakura
     * token : 5_ZujVNYP7FaTfo8vvljU2F0AzZP5WrfT7_XL-vz3gpEYrG2s0bd7VX0q-0eKfJzD6hdgtBtGOIQumfxi_3Kcvv9I24Q27K9xyGRv4vJN9lxY
     * city :
     * gender : 0
     * province :
     * refresh_token : 5_S4DJj0ptavx2YKeVsmep4tBBbpmIvBfYjupHHO-5i0TpPkJl5_HExVrJvhse41uNG4TAFOm4dMS-uhojH4Ueg29TOZp6_jpZvwq14uYDyrM
     * openid : om1Mu1hmcTef7IwCA0sBt8cfsEgM
     * country : BB
     * expiresIn : 7200
     */

    private String unionid;
    private String userID;
    private String icon;
    private long expiresTime;
    private String nickname;
    private String token;
    private String city;
    private String gender;
    private String province;
    private String refresh_token;
    private String openid;
    private String country;
    private int expiresIn;

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
