package com.hhlt.konsultado.response;

public class PlatformNum {
    private Integer platId;
    private String platformName;
    private Long number; //已经分配的
    private Double rate;
    private Long chaZhi;//差值
    private Long shouldNumber;//应该分配的

    public Integer getPlatId() {
        return platId;
    }

    public void setPlatId(Integer platId) {
        this.platId = platId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getChaZhi() {
        return chaZhi;
    }

    public void setChaZhi(Long chaZhi) {
        this.chaZhi = chaZhi;
    }

    public Long getShouldNumber() {
        return shouldNumber;
    }

    public void setShouldNumber(Long shouldNumber) {
        this.shouldNumber = shouldNumber;
    }


}
