package com.hhlt.konsultado.entity;

public class SpendSon {
    private Integer id;

    private String date;

    private String channelId;

    private String typeId;

    private String looks;

    private String visitors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getLooks() {
        return looks;
    }

    public void setLooks(String looks) {
        this.looks = looks == null ? null : looks.trim();
    }

    public String getVisitors() {
        return visitors;
    }

    public void setVisitors(String visitors) {
        this.visitors = visitors == null ? null : visitors.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}