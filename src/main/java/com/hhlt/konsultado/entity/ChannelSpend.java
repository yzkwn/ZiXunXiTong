package com.hhlt.konsultado.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChannelSpend {
    private Integer id;

    private Date date;

    private Integer channelId;

    private String channel;

    private Boolean status;

    private BigDecimal openingAmount;

    private BigDecimal rechargeAmount;

    private BigDecimal balance; //餘額

    private BigDecimal totalCharger;//總花費

    private Date createTime;

    private Date updateTime;

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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getOpeningAmount() {
        return openingAmount;
    }

    public void setOpeningAmount(BigDecimal openingAmount) {
        this.openingAmount = openingAmount;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalCharger() {
        return totalCharger;
    }

    public void setTotalCharger(BigDecimal totalCharger) {
        this.totalCharger = totalCharger;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}