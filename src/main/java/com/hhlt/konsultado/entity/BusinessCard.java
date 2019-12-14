package com.hhlt.konsultado.entity;

public class BusinessCard {

    private String compId;

    private Long recId; // 访客回话信息的唯一标识

    private String col1;

    private String compName;

    private String cusType;

    private String lastChangeTime;

    private String addtime;

    private String linkman;

    private String mobile;

    private String qq;

    private String siteId;

    private String visitorId;

    private String loginName;

    private String userName;

    private String nickName;

    private Integer flag;

    private Integer connectionTimes;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getConnectionTimes() {
        return connectionTimes;
    }

    public void setConnectionTimes(Integer connectionTimes) {
        this.connectionTimes = connectionTimes;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }
    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public String getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(String lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }
}
