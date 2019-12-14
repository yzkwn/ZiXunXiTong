package com.hhlt.konsultado.entity;

import java.sql.Timestamp;
import java.util.List;

public class CardVisitor {
    private String compId;
    private String msn;



    private String col1;

    private String compName;

    private String cusType;

    private String lastChangeTime;

    private String addtime;

    private String linkman;

    private String mobile;

    private String qq;

    private String siteId;

    private String visitorId;

    private String loginName;

    private String userName;

    private String nickName;

    private Integer flag;

    private Integer connectionTimes;
    private Long recId; // 访客回话信息的唯一标识


    private String visitorName; // 访客名称

    private Timestamp curEnterTime; // 当前进入网站时间

    private Integer curStayTime; // 访客停留时间

    private String sourceIp; // 访客来源 IP

    private String sourceProvince;

    private String sourceIpInfo; // 访客来源 IP 信息（网络接入商）

    private String requestType; // 对话请求方式（rt_v：访客请求，rt_c：客服请求，rt_ct：本公司跨站点转接，rt_ot：跨公司转接）

    private String endType; // 对话结束方式（et_v_e：访客主动结束，et_c_e：客服主动结束，et_c_r

    private Timestamp diaStartTime; // 对话开始时间

    private Timestamp diaEndTime; // 对话结束时间

    private String terminalType; // 终端类型（tt_pc：电脑，tt_ppc：平板电脑，tt_mb：手机）

    private Integer visitorSendNum; // 访客发送消息数

    private Integer csSendNum; // 客服发送消息数

    private String sourceUrl; // 来源网页

    private String sourceType; // 来源类型

    private String searchEngine; // 搜索引擎

    private String keyword; // 搜索关键词

    private String firstCsId; // 初次接待客服

    private String joinCsIds; // 参与接待客服

    private String dialogType; // 对话类型（dt_v_ov：仅访问网站，dt_v_nm：访客无消息，dt_c_na：

    private Timestamp firstVisitTime; // 最初访问时间

    private Timestamp preVisitTime; // 上一次访问时间

    private Integer totalVisitTime; // 累计来访次数

    private String diaPage; // 发起对话网址

    private String curFirstViewPage; // 本次最初访问网页

    private Integer curVisitorPages;

    private Integer preVisitPages;

    private String operatingSystem;

    private String browser;

    private String info;

    private String siteName;

    private List<Dialogs> dialogs;

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public String getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(String lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Timestamp getCurEnterTime() {
        return curEnterTime;
    }

    public void setCurEnterTime(Timestamp curEnterTime) {
        this.curEnterTime = curEnterTime;
    }

    public Integer getCurStayTime() {
        return curStayTime;
    }

    public void setCurStayTime(Integer curStayTime) {
        this.curStayTime = curStayTime;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getSourceProvince() {
        return sourceProvince;
    }

    public void setSourceProvince(String sourceProvince) {
        this.sourceProvince = sourceProvince;
    }

    public String getSourceIpInfo() {
        return sourceIpInfo;
    }

    public void setSourceIpInfo(String sourceIpInfo) {
        this.sourceIpInfo = sourceIpInfo;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public Timestamp getDiaStartTime() {
        return diaStartTime;
    }

    public void setDiaStartTime(Timestamp diaStartTime) {
        this.diaStartTime = diaStartTime;
    }

    public Timestamp getDiaEndTime() {
        return diaEndTime;
    }

    public void setDiaEndTime(Timestamp diaEndTime) {
        this.diaEndTime = diaEndTime;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public Integer getVisitorSendNum() {
        return visitorSendNum;
    }

    public void setVisitorSendNum(Integer visitorSendNum) {
        this.visitorSendNum = visitorSendNum;
    }

    public Integer getCsSendNum() {
        return csSendNum;
    }

    public void setCsSendNum(Integer csSendNum) {
        this.csSendNum = csSendNum;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFirstCsId() {
        return firstCsId;
    }

    public void setFirstCsId(String firstCsId) {
        this.firstCsId = firstCsId;
    }

    public String getJoinCsIds() {
        return joinCsIds;
    }

    public void setJoinCsIds(String joinCsIds) {
        this.joinCsIds = joinCsIds;
    }

    public String getDialogType() {
        return dialogType;
    }

    public void setDialogType(String dialogType) {
        this.dialogType = dialogType;
    }

    public Timestamp getFirstVisitTime() {
        return firstVisitTime;
    }

    public void setFirstVisitTime(Timestamp firstVisitTime) {
        this.firstVisitTime = firstVisitTime;
    }

    public Timestamp getPreVisitTime() {
        return preVisitTime;
    }

    public void setPreVisitTime(Timestamp preVisitTime) {
        this.preVisitTime = preVisitTime;
    }

    public Integer getTotalVisitTime() {
        return totalVisitTime;
    }

    public void setTotalVisitTime(Integer totalVisitTime) {
        this.totalVisitTime = totalVisitTime;
    }

    public String getDiaPage() {
        return diaPage;
    }

    public void setDiaPage(String diaPage) {
        this.diaPage = diaPage;
    }

    public String getCurFirstViewPage() {
        return curFirstViewPage;
    }

    public void setCurFirstViewPage(String curFirstViewPage) {
        this.curFirstViewPage = curFirstViewPage;
    }

    public Integer getCurVisitorPages() {
        return curVisitorPages;
    }

    public void setCurVisitorPages(Integer curVisitorPages) {
        this.curVisitorPages = curVisitorPages;
    }

    public Integer getPreVisitPages() {
        return preVisitPages;
    }

    public void setPreVisitPages(Integer preVisitPages) {
        this.preVisitPages = preVisitPages;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<Dialogs> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialogs> dialogs) {
        this.dialogs = dialogs;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }
}
