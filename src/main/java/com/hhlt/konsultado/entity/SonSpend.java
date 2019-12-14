package com.hhlt.konsultado.entity;

import java.math.BigDecimal;

public class SonSpend extends Spend {
    private String Maxtime;
    private BigDecimal yue;

    public BigDecimal getYue() {
        return yue;
    }

    public void setYue(BigDecimal yue) {
        this.yue = yue;
    }

    public String getMaxtime() {
        return Maxtime;
    }

    public void setMaxtime(String maxtime) {
        Maxtime = maxtime;
    }
}
