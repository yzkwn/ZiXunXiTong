package com.hhlt.konsultado.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DealData {
    private Integer id;

    private String orderId;

    private Date signDate;

    private String saleTeam;

    private String saleDeputy;

    private String dealProject;

    private String contact;

    private String province;

    private String city;

    private String telephone;

    private BigDecimal dealMoney;

    private Integer paymentCycle;

    private String storeCategory;

    private String storeLevel;

    private String channel;

    private String wangwangId;

    private String storeUrl;

    private String beizhu;

    private String arrivalStatus;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSaleTeam() {
        return saleTeam;
    }

    public void setSaleTeam(String saleTeam) {
        this.saleTeam = saleTeam == null ? null : saleTeam.trim();
    }

    public String getSaleDeputy() {
        return saleDeputy;
    }

    public void setSaleDeputy(String saleDeputy) {
        this.saleDeputy = saleDeputy == null ? null : saleDeputy.trim();
    }

    public String getDealProject() {
        return dealProject;
    }

    public void setDealProject(String dealProject) {
        this.dealProject = dealProject == null ? null : dealProject.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public BigDecimal getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Integer getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(Integer paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory == null ? null : storeCategory.trim();
    }

    public String getStoreLevel() {
        return storeLevel;
    }

    public void setStoreLevel(String storeLevel) {
        this.storeLevel = storeLevel == null ? null : storeLevel.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getWangwangId() {
        return wangwangId;
    }

    public void setWangwangId(String wangwangId) {
        this.wangwangId = wangwangId == null ? null : wangwangId.trim();
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl == null ? null : storeUrl.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public String getArrivalStatus() {
        return arrivalStatus;
    }

    public void setArrivalStatus(String arrivalStatus) {
        this.arrivalStatus = arrivalStatus == null ? null : arrivalStatus.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}