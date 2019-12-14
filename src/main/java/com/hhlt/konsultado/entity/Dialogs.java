package com.hhlt.konsultado.entity;


import java.sql.Timestamp;

public class Dialogs {

    private long id;

    private Integer dialogId;

    private Timestamp addTime;

    private String recContent;

    private Integer recId;

    private Integer recType;

    private String sender;

    private String subType;

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

    public String getRecContent() {
        return recContent;
    }

    public void setRecContent(String recContent) {
        this.recContent = recContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getDialogId() {
        return dialogId;
    }

    public void setDialogId(Integer dialogId) {
        this.dialogId = dialogId;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getRecType() {
        return recType;
    }

    public void setRecType(Integer recType) {
        this.recType = recType;
    }


}
