package com.hhlt.konsultado.entity;

import lombok.ToString;

import java.util.Date;

@ToString
public class ConsultData {
    private Integer id;

    private String name;

    private Date consultDate;

    private Integer typeId;

    private Integer channelId;

//    private Integer channelChildId;

    private String telephone;

    private String province;

    private String provinceNum;

    private String city;

    private String cityNum;

    private String wangwang;

    private String storeName;

    private String storeUrl;

    private String weixin;

    private String qq;

    private String remark;

    private String remarkBeiZhu;

    private Integer levelId;

    private Boolean isTelephone;

    private Boolean isTwice;

    private String currentServicer;

    private Integer planformId;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private String salesman;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(Date consultDate) {
        this.consultDate = consultDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang == null ? null : wangwang.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl == null ? null : storeUrl.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Boolean getIsTelephone() {
        return isTelephone;
    }

    public void setIsTelephone(Boolean isTelephone) {
        this.isTelephone = isTelephone;
    }

    public Boolean getIsTwice() {
        return isTwice;
    }

    public void setIsTwice(Boolean isTwice) {
        this.isTwice = isTwice;
    }

    public String getCurrentServicer() {
        return currentServicer;
    }

    public void setCurrentServicer(String currentServicer) {
        this.currentServicer = currentServicer == null ? null : currentServicer.trim();
    }

    public Integer getPlanformId() {
        return planformId;
    }

    public void setPlanformId(Integer planformId) {
        this.planformId = planformId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarkBeiZhu() {
        return remarkBeiZhu;
    }

    public void setRemarkBeiZhu(String remarkBeiZhu) {
        this.remarkBeiZhu = remarkBeiZhu;
    }

    public String getProvinceNum() {
        return provinceNum;
    }

    public void setProvinceNum(String provinceNum) {
        this.provinceNum = provinceNum;
    }

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    //    public Integer getChannelChildId() {
//        return channelChildId;
//    }
//
//    public void setChannelChildId(Integer channelChildId) {
//        this.channelChildId = channelChildId;
//    }
}