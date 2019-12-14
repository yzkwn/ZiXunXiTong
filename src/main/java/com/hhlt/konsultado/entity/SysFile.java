package com.hhlt.konsultado.entity;

public class SysFile {

    private Integer id;

    private String url;

    private String sysLogo;

    private String originalName;

    private String currentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSysLogo() {
        return sysLogo;
    }

    public void setSysLogo(String sysLogo) {
        this.sysLogo = sysLogo;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }
}
