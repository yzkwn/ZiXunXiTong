package com.hhlt.konsultado.response;

import java.util.Date;

public class SpendDataResponse {

    private Integer id;

    // 时间
    private Date date;

    // 渠道 ID
    private String spendChannelId;

    // 业务类型名称
    private String businessType;

    // 状态
    private Boolean status;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    // 展现量
    private Long adPv;

    // 点击量
    private Long click;

    // 消耗
    private Double charge;

    // 咨询
    private String consult;

    // 文件id
    private String sysLogo;

    // 充值金额
    private Double rechargeAmount;

    //期初余额
    private Double beginningBalance;

    //渠道名称
    private String channel;

    // 平均花费
    private Double averageCost;
    //余额
    private Double balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSpendChannelId() {
        return spendChannelId;
    }

    public void setSpendChannelId(String spendChannelId) {
        this.spendChannelId = spendChannelId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
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

    public Long getAdPv() {
        return adPv;
    }

    public void setAdPv(Long adPv) {
        this.adPv = adPv;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    public String getSysLogo() {
        return sysLogo;
    }

    public void setSysLogo(String sysLogo) {
        this.sysLogo = sysLogo;
    }

    public Double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Double getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(Double beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(Double averageCost) {
        this.averageCost = averageCost;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
