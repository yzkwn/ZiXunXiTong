package com.hhlt.konsultado.response;

public class PlatformRate {
    private Integer platId;
    private String platformName;
    private Double rate;

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
}
