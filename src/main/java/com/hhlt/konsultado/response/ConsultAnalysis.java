package com.hhlt.konsultado.response;


public class ConsultAnalysis {


    // 业务类型名称
    private String businessType;

    // 展现量
    private Long adPv;

    // 点击量
    private Long click;

    // 花费
    private Double charge;

    //咨询
    private Long consult;

    // 充值金额
    private Double rechargeAmount;

    // 期初余额
    private Double beginningBalance;


    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getAdPv() {
        return adPv;
    }

    public void setAdPv(Long adPv) {
        this.adPv = adPv;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Long getConsult() {
        return consult;
    }

    public void setConsult(Long consult) {
        this.consult = consult;
    }

    public Double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Double getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(Double beginningBalance) {
        this.beginningBalance = beginningBalance;
    }
}
