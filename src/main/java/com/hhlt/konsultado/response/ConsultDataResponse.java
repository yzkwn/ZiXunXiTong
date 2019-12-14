package com.hhlt.konsultado.response;

import com.hhlt.konsultado.entity.ConsultData;

public class ConsultDataResponse extends ConsultData {

    private String businessType;
    private String levelName;
    private String channel;
    private String platName;
//    private String childChannel;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

//    public String getChildChannel() {
//        return childChannel;
//    }
//
//    public void setChildChannel(String childChannel) {
//        this.childChannel = childChannel;
//    }
}
