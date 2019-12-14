package com.hhlt.konsultado.entity;

import java.util.List;

public class SysPermission {
    private Integer id;

    private Integer parentid;

    private String name;

    private String css;

    private String href;

    private Integer type;

    private String permission;

    private Integer sort;

    private List<SysPermission> child;

    public List<SysPermission> getChild() {
        return child;
    }

    public void setChild(List<SysPermission> child) {
        this.child = child;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css == null ? null : css.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}