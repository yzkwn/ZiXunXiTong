package com.hhlt.konsultado.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Spend {
    private Integer id;

    private Date date;

    private String channelId;

    private String businessTypeId;

    private BigDecimal adpv;


    private BigDecimal click;

    private BigDecimal charge;

    private Integer pageviews;

    private Integer visitor;

    private Boolean status;

    private String createTime;

    private String updateTime;

    private Integer pageNum;

    private Integer pageSize;

    private String beginTime;

    private  String Type;

    private String sum;

    private int zixun;

    private Double chengben;

    private int cID;


    private int wuZiXun;

    private int youZiXun;

    public int getWuZiXun() {
        return wuZiXun;
    }

    public void setWuZiXun(int wuZiXun) {
        this.wuZiXun = wuZiXun;
    }

    public int getYouZiXun() {
        return youZiXun;
    }

    public void setYouZiXun(int youZiXun) {
        this.youZiXun = youZiXun;
    }

    public Double getYouXiaochengben() {
        return youXiaochengben;
    }

    public void setYouXiaochengben(Double youXiaochengben) {
        this.youXiaochengben = youXiaochengben;
    }

    private Double youXiaochengben;




    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getZixun() {
        return zixun;
    }

    public void setZixun(int zixun) {
        this.zixun = zixun;
    }

    public Double getChengben() {
        return chengben;
    }

    public void setChengben(Double chengben) {
        this.chengben = chengben;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(String businessTypeId) {
        this.businessTypeId = businessTypeId == null ? null : businessTypeId.trim();
    }

    public BigDecimal getAdpv() {
        return adpv;
    }

    public void setAdpv(BigDecimal adpv) {
        this.adpv = adpv;
    }

    public BigDecimal getClick() {
        return click;
    }

    public void setClick(BigDecimal click) {
        this.click = click;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}