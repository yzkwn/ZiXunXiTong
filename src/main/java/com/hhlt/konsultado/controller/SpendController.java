package com.hhlt.konsultado.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.*;
import com.hhlt.konsultado.response.ConsultCountResponse;
import com.hhlt.konsultado.service.*;
import com.hhlt.konsultado.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@Controller
@RequestMapping("/api/spend")
public class SpendController {

    @Autowired
    private SpendService spendService;
    @Autowired
    private ConsultingChannelService channelService;
    @Autowired
    private PlatformInformationService platformInformationService;
    @Autowired
    private ChannelSpendService channelSpendService;
    @Autowired
    private SpendSonServcie spendSonServcie;
    @Autowired
    private ConsultDataService consultDataService;
    @Autowired
    private BusinessTypeService businessTypeService;

    private static final Logger logger = LoggerFactory.getLogger(SpendController.class);
    private SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/selectSpendList")
    public String selectSpendList(Model model) throws ParseException {
        model.addAttribute("BusinessType", spendService.selectBusinessType());
        model.addAttribute("SpendChanne", spendService.selectSpendChannel());
//        model.addAttribute("spendList",spends);
        return "Spend/spend-saveList";
    }

    @RequestMapping("/saveSpend")
    @PreAuthorize("hasAuthority('jumpSaveSpend')")
    @ResponseBody
    public Result saveSpend(@RequestParam Map<String, Object> map) throws ParseException {
        SpendSon spendson = new SpendSon();
        Result result = new Result();
        Object business_type_id = map.get("business_type_id");
        //浏览量
        if (StringUtils.isNotEmpty(map.get("pageviews").toString()))
            spendson.setLooks(map.get("pageviews").toString());
        //访客数
        if (StringUtils.isNotEmpty(map.get("visitor").toString())) {
            spendson.setVisitors(map.get("visitor").toString());
        }
        //渠道id
        if (StringUtils.isNotEmpty(map.get("channels").toString()))
            spendson.setChannelId(map.get("channels").toString());

        //业务类型
        if (StringUtils.isNotEmpty(map.get("business_type_id").toString()))
            spendson.setTypeId(map.get("business_type_id").toString());

        // 当前日期
        if (StringUtils.isNotEmpty(map.get("data").toString()))
            spendson.setDate((map.get("data").toString()));
        int i = spendSonServcie.insertSelective(spendson);
        if (i > 0) {
            System.out.println("成功");
            result.setMsg("1");
        }
        result.setCode(0);
        return result;
    }

    @RequestMapping("/selectSpendListSearch")
    @PreAuthorize("hasAuthority('selectSpendListSearch')")
    @ResponseBody
    public HashMap selectSpendListSearch(@RequestParam Map<String, Object> map) throws ParseException {
        HashMap map1 = new HashMap();
        map.entrySet().forEach(i -> logger.info("key:" + i.getKey() + ",value:" + i.getValue()));
        Object typeId = map.get("typeId");
        Object channelId = map.get("channelId");
        Integer sumadPv = 0; //总展现量
        Integer sumclick = 0;//总点击量p
        double sumcharge = 0.0;//总消费
        double chengben = 0.0;//总成本
        Integer zixun = 0;
        //Integer integerNull = 0;
        Integer integerNullSum = 0;
        String strt = null;
        String st = null;
        String youxiaoCb = null;
        //Integer youxiaoZiXun = 0;
        Integer youxiaoZiXunSum = 0;
        //Integer integer2 = 0;//其他
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        map.put("pageNum", (pageNum - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Spend> spendList = spendService.selectSpendList(map);
        for (Spend s : spendList) {
            double v = s.getCharge().doubleValue();
            Object beginTime = map.get("beginTime");
            Object endTime = map.get("endTime");
            Date date = s.getDate();
            String format = dayFmt.format(date);
            int i1 = DateUtil.caculateTotalTime(beginTime.toString(), endTime.toString());
            for (int i = 0; i < i1 + 1; i++) {
                String time = DateUtil.addDate(beginTime.toString(), i);
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("time", time);
                if (format.equals(time)) {
                    BusinessType businessType1 = businessTypeService.get(s.getBusinessTypeId().toString());
                    map2.put("typeId", businessType1.getId());
                    map2.put("channelId", s.getcID());
                    Integer integer = consultDataService.countS(map2);//每天的总咨询量
                    if (integer == 0) {
                        s.setChengben(v);
                        s.setZixun(0);
                    } else {
                        double v1 = v / integer;
                        DecimalFormat df = new DecimalFormat("#.00");
                        String str = df.format(v1);
                        s.setChengben(Double.parseDouble(str));
                        s.setZixun(integer);
                    }
                    HashMap m = new HashMap();


                    Integer integerNull = consultDataService.countA(map2);//无效咨询量

                    if (integerNull == 0) {
                        s.setWuZiXun(0);
                    }else {
                        s.setWuZiXun(integerNull);
                    }
                    Integer youxiaoZiXun = consultDataService.countYouXiao(map2);//有效咨询量
                    if (youxiaoZiXun == 0) {
                        s.setYouZiXun(0);
                        s.setYouXiaochengben(v);
                    } else {
                        double v11 = v / youxiaoZiXun;
                        DecimalFormat df = new DecimalFormat("#.00");
                        String YouxiaoCb = df.format(v11);
                        s.setYouXiaochengben(Double.parseDouble(YouxiaoCb));//有效咨询量成本
                        s.setYouZiXun(youxiaoZiXun);
                    }
                }
            }
            sumadPv += s.getAdpv().intValue();
            sumclick += s.getClick().intValue();
            sumcharge += s.getCharge().doubleValue();
            chengben += s.getChengben();
            zixun += s.getZixun();
            integerNullSum += s.getWuZiXun();
            youxiaoZiXunSum += s.getYouZiXun();
        }

        double w = sumcharge / zixun;
        DecimalFormat df = new DecimalFormat("#.00");
        strt = df.format(w);
        double w1 = sumcharge / youxiaoZiXunSum;
        st = df.format(w1);

//        double youxiaoCB = sumcharge / youxiaoZiXunSum;
//        if (youxiaoCB == Infinity) {
//            map1.put("youxiaoCb", 0);//有效成本
//        } else {
//            youxiaoCb = df.format(youxiaoCB);
//            map1.put("youxiaoCb", youxiaoCb);//有效成本
//        }

        int i = spendService.selectCount(map);
        logger.info("输出_________" + JSONObject.toJSONString(spendList));
        map1.put("spendList", spendList);
        map1.put("i", Long.valueOf(i));
        map1.put("sumadPv", sumadPv);
        map1.put("sumclick", sumclick);
        map1.put("sumcharge", sumcharge);
        map1.put("zixun", zixun);
        map1.put("chengben", strt);
        map1.put("integerNullSum", integerNullSum);//无效咨询量总和
        map1.put("youxiaoZiXunCB", st);//有效咨询量成本
        map1.put("youxiaoZiXunSum", youxiaoZiXunSum);//有效咨询量总和
        map1.put("youxiaoCb", youxiaoCb);//有效成本
        return map1;
    }


    /**
     * 添加的模态框链接
     *
     * @return
     */
    @RequestMapping("/jumpSaveSpend")
    public String jumpSaveSpend(Model model) {
        model.addAttribute("BusinessType", spendService.selectBusinessType());
        model.addAttribute("SpendChanne", spendService.selectSpendChannel());
        model.addAttribute("platformInformation", platformInformationService.list());
        return "Spend/spend-add";
    }


    /**
     * 跳转到咨询数据分析
     */
    @RequestMapping("/jumpAnalysisList")
    public String jumpAnalysisList(Model model) {
        model.addAttribute("channel", channelService.list());
        return "ConsultAnalysis/ConsultAnalysis-list";
    }


    /*
     * 分组查询进入的页面
     * */
    @RequestMapping("/selectSpendGroupByList")
    public String selectSpendGrouByTimeList(Model model) throws ParseException {
        //model.addAttribute("BusinessType", spendService.selectBusinessType());
        model.addAttribute("SpendChanne", spendService.selectSpendChannel());
        return "Spend/spend_GroupByDateList";
    }


    /*
     * 分组查询根据时间的数据展示  1
     * */
    @RequestMapping("/selectSpendGrouByTimeSearch")
    @ResponseBody
    public HashMap selectSpendGrouByTimeSearch(@RequestParam Map<String, Object> map) {
        map.entrySet().forEach(i -> logger.info("key:" + i.getKey() + ",value:" + i.getValue()));
        HashMap map1 = new HashMap();
        Integer sumadPv = 0; //总展现量
        Integer sumclick = 0;//总点击量p
        Integer sumcharge = 0;//总消费
        Integer sumVisitor = 0;//总访客数
        Integer sumPageviews = 0;//总浏览量
        Double sumYue = 0.0;//总余额

        List<SonSpend> spendList = spendService.selectSpendGroupBy(map);
        List<ChannelSpend> channelSpendList = channelSpendService.listBalanceMoney(map); //<!--查询开始时间之前的余额-->
        List<ChannelSpend> channelSpends = channelSpendService.listTotalMoney(map);// <!--一段时间的充值和总花费-->
        spendList.forEach(i -> logger.info("花费数据：" + JSONObject.toJSONString(i)));
        logger.info("----------------------------------------------------------------");
        channelSpends.forEach(i -> logger.info("一段时间的充值和总花费--->:" + JSONObject.toJSONString(i)));
        logger.info("----------------------------------------------------------------");
        channelSpendList.forEach(i -> logger.info("查询开始时间之前的余额--->:" + JSONObject.toJSONString(i)));
        logger.info("----------------------------------------------------------------");

        for (SonSpend sonSpend : spendList) {
            if (channelSpendList.size() == 0) { //之前没有余额数据
                for (ChannelSpend channelSpend : channelSpends) {
                    if (sonSpend.getChannelId().equals(channelSpend.getChannel()))
                        sonSpend.setYue(channelSpend.getOpeningAmount().add(channelSpend.getRechargeAmount()).subtract(channelSpend.getTotalCharger()));
                }
            } else {
                for (ChannelSpend channelSpend1 : channelSpendList) {
                    for (ChannelSpend channelSpend : channelSpends) {
                        if (sonSpend.getChannelId().equals(channelSpend.getChannel())
                                && sonSpend.getChannelId().equals(channelSpend1.getChannel()))
                            sonSpend.setYue(channelSpend1.getOpeningAmount().add(channelSpend.getRechargeAmount()).subtract(channelSpend.getTotalCharger()));
                    }
                }
            }
        }

        for (int i = 0; i < spendList.size(); i++) {
            sumadPv += spendList.get(i).getAdpv().intValue();
            sumclick += spendList.get(i).getClick().intValue();
            sumcharge += spendList.get(i).getCharge().intValue();
            if (spendList.get(i).getVisitor() != null) {
                sumVisitor += spendList.get(i).getVisitor().intValue();
            }

            sumPageviews += spendList.get(i).getPageviews().intValue();
            sumYue += spendList.get(i).getYue().doubleValue();
        }

        map1.put("spendList", spendList);
        map1.put("sumadPv", sumadPv);
        map1.put("sumclick", sumclick);
        map1.put("sumcharge", sumcharge);
        map1.put("sumVisitor", sumVisitor);
        map1.put("sumPageviews", sumPageviews);
        map1.put("sumYue", sumYue);
        return map1;
    }


    /*
     * 分组查询根据渠道id  2
     * */
    @RequestMapping("/selectSpendGrouByChannelIdSearch")
    @ResponseBody
    public HashMap selectSpendGrouByChannelIdSearch(@RequestParam Map<String, Object> map) throws ParseException {
        map.entrySet().forEach(i -> logger.info("key:" + i.getKey() + ",---> value:" + i.getValue()));
        String beginTime = map.get("beginTime").toString();
        String endTime = map.get("endTime").toString();
        int chaTime = caculateTotalTime(endTime, beginTime);
        logger.info("beginTime：" + beginTime + ",endTime:" + endTime + ",chaTime:" + chaTime);

        String format = null;
        HashMap map1 = new HashMap();
        Integer sumadpv = 0;
        Integer sumclick = 0;
        Integer sumcharge = 0;
        Integer sumVisitor = 0;
        Integer sumPageviews = 0;
        Double yue = 0.0;

        List<SonSpend> spendList = spendService.selectGroupByChannelId(map);
        spendList.forEach(i -> logger.info("花费数据--->：" + JSONObject.toJSONString(i)));
        logger.info("----------------------------------------------------------------");

        List<ChannelSpend> channelSpends = new ArrayList<>();
        for (int i = 0; i <= chaTime; i++) {
            String stime = addDate(beginTime, i);
            map.put("beginTime", stime);

            ChannelSpend channelSpendList = channelSpendService.balanceMoneyByChannelId(map); //<!--前一日的余额-->
            ChannelSpend channelSpend = channelSpendService.get(map);            //求这一天的充值和消费

            if (null == channelSpendList) logger.info(stime + " 前一日的余额: 0");
            if (null != channelSpendList) {
                if (null != channelSpendList.getBalance()) {
                    logger.info(stime + " 前一日的余额: " + channelSpendList.getBalance());
                } else {
                    channelSpendList.setBalance(new BigDecimal(0));
                    logger.info(stime + " 前一日的余额: 0");
                }
            }
            if (null == channelSpend) logger.info(stime + " 这一天的充值:" + 0 + ",和消费:" + 0);
            if (null != channelSpend) {
                if (null == channelSpend.getRechargeAmount()) {
                    channelSpend.setRechargeAmount(new BigDecimal(0));
                }
                if (null == channelSpend.getTotalCharger()) {
                    channelSpend.setTotalCharger(new BigDecimal(0));
                }
                logger.info(stime + " 这一天的充值:" + channelSpend.getRechargeAmount() + ",和消费:" + channelSpend.getTotalCharger());
            }

            if (null == channelSpendList && null != channelSpend) {
                channelSpend.setBalance(channelSpend.getOpeningAmount().add(channelSpend.getRechargeAmount()).subtract(channelSpend.getTotalCharger()));
                logger.info("时间:" + stime + ",channelSpend1:" + JSONObject.toJSONString(channelSpend));
                channelSpends.add(channelSpend);
            }
            if (null != channelSpendList && null != channelSpend) {
                channelSpend.setBalance(channelSpendList.getBalance().add(channelSpend.getRechargeAmount()).subtract(channelSpend.getTotalCharger()));
                logger.info("时间:" + stime + ",channelSpend2:" + JSONObject.toJSONString(channelSpend));
                channelSpends.add(channelSpend);
            }
        }

        spendList.forEach(j -> {
            channelSpends.forEach(k -> {
                if (j.getDate().equals(k.getDate())) {
                    j.setYue(k.getBalance());
                }
            });
        });

        for (int i = 0; i < spendList.size(); i++) {
            sumadpv += spendList.get(i).getAdpv().intValue();
            sumclick += spendList.get(i).getClick().intValue();
            sumcharge += spendList.get(i).getCharge().intValue();//花费
            if (spendList.get(i).getVisitor() != null) {
                sumVisitor += spendList.get(i).getVisitor().intValue();//访客数
            } else {
                sumVisitor = 0;
            }
            if (spendList.get(i).getPageviews() != null) {
                sumPageviews += spendList.get(i).getPageviews().intValue();//浏览量
            } else {
                sumPageviews = 0;
            }
            if (spendList.get(i).getYue() != null) {
                yue += spendList.get(i).getYue().doubleValue();//余额
            }

            Date date = spendList.get(i).getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            format = simpleDateFormat.format(date);
        }

        map1.put("spendList", spendList);
        map1.put("sumadPv", sumadpv);
        map1.put("sumclick", sumclick);
        map1.put("sumcharge", sumcharge);
        map1.put("sumVisitor", sumVisitor);
        map1.put("sumPageviews", sumPageviews);
        map1.put("date", format);
        map1.put("yue", yue);
        return map1;
    }

    @RequestMapping("/selectSpendGrouByTypeSearch")
    @ResponseBody
    public HashMap selectSpendGrouByTypeSearch(@RequestParam Map map) {
        String format = null;
        HashMap map1 = new HashMap();
        Integer sumadpv = 0;
        Integer sumclick = 0;
        Integer sumcharge = 0;
        Integer sumVisitor = 0;
        Integer sumPageviews1 = 0;
        Integer sumPageviews2 = 0;
        Integer sumPageviews3 = 0;
        Integer sumPageviews4 = 0;
        Integer sumPageviews5 = 0;
        Integer sumPageviews6 = 0;
        List<ConsultCountResponse> consultCountResponse = spendService.consultCount(map.get("typeId").toString(), map.get("beginTime").toString(), map.get("endTime").toString());
        for (int i = 0; i < consultCountResponse.size(); i++) {
            sumadpv += consultCountResponse.get(i).getBeijingBd().intValue();
            sumclick += consultCountResponse.get(i).getBeijing360().intValue();
            sumcharge += consultCountResponse.get(i).getQingdaoBd().intValue();
            sumVisitor += consultCountResponse.get(i).getQingdao360().intValue();
            sumPageviews1 += consultCountResponse.get(i).getJinri().intValue();
            sumPageviews2 += consultCountResponse.get(i).getZyyz().intValue();
            sumPageviews3 += consultCountResponse.get(i).getDgpt().intValue();
            sumPageviews4 += consultCountResponse.get(i).getBjService1().intValue();
            sumPageviews5 += consultCountResponse.get(i).getZywdqd().intValue();
            sumPageviews6 += consultCountResponse.get(i).getKdfwsc().intValue();
        }
        map1.put("sumadPv", sumadpv);
        map1.put("sumclick", sumclick);
        map1.put("sumcharge", sumcharge);
        map1.put("sumVisitor", sumVisitor);
        map1.put("sumPageviews1", sumPageviews1);
        map1.put("sumPageviews2", sumPageviews2);
        map1.put("sumPageviews3", sumPageviews3);
        map1.put("sumPageviews4", sumPageviews4);
        map1.put("sumPageviews5", sumPageviews5);
        map1.put("sumPageviews6", sumPageviews6);
        map1.put("consultCountResponse", consultCountResponse);
        return map1;
    }

    /**
     * 计算两个日期相差几天
     *
     * @param startTime ： 开始时间  最新的时间在前
     * @param endTime   ： 结束时间
     * @return
     */
    public int caculateTotalTime(String startTime, String endTime) {
        Date date1 = null;
        Date date = null;
        Long l = 0L;
        try {
            date = dayFmt.parse(startTime);
            long ts = date.getTime();
            date1 = dayFmt.parse(endTime);
            long ts1 = date1.getTime();

            l = (ts - ts1) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l.intValue();
    }

    //日期往后加几天
    public String addDate(String now, int day) {
        Calendar fromCal = Calendar.getInstance();
        try {
            Date date = dayFmt.parse(now);
            fromCal.setTime(date);
            fromCal.add(Calendar.DATE, day);
            return dayFmt.format(fromCal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
