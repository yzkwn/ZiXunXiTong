package com.hhlt.konsultado.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.ChannelSpend;
import com.hhlt.konsultado.entity.ConsultingChannel;
import com.hhlt.konsultado.service.ChannelSpendService;
import com.hhlt.konsultado.service.ConsultingChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hhlt.konsultado.util.MapToObject.transMap2Bean;

@Controller
@RequestMapping("channelSpend")
public class ChannelSpendController {

    @Autowired
    private ConsultingChannelService channelService;
    @Autowired
    private ChannelSpendService spendService;

    @Autowired
    private ChannelSpendService channelSpendService;

    private static final Logger logger = LoggerFactory.getLogger(ChannelSpendController.class);

    @RequestMapping("list")
    public String listChannelSpendData(Model model) {
        List<ConsultingChannel> list = channelService.list();
        model.addAttribute("list", list);
        return "Spend/list-channelSpend";
    }

    @RequestMapping("listSearch")
    @ResponseBody
    @PreAuthorize("hasAuthority('channelSpend:query')")
    public Result listSearchChannelSpendData(@RequestParam Map<String, Object> map) {
        map.entrySet().forEach(i -> logger.info("key:" + i.getKey() + ",value:" + i.getValue()));
        Result result = new Result();
        List<ChannelSpend> list = spendService.list(map);
        list.forEach(i -> logger.info("channelSpend:{}", JSONObject.toJSONString(i)));
        result.setData(list);
        return result;
    }

    @RequestMapping("add")
    public String addView(Model model) {
        List<ConsultingChannel> list = channelService.list();
        model.addAttribute("list", list);
        return "Spend/add-channelSpend";
    }

    @RequestMapping("check")
    @ResponseBody
    @PreAuthorize("hasAuthority('channelSpend:check')")
    public Result check(@RequestParam Map<String, Object> map) {
        Result result = new Result();
        Map<String, Object> param = new HashMap<>();

        Boolean check = spendService.check(map);
        List<ChannelSpend> channelSpend = spendService.getByCId(map);

        param.put("check", check);
        param.put("channelSpend", channelSpend);
        result.setData(param);
        return result;
    }

    @RequestMapping("addData")
    @ResponseBody
    @PreAuthorize("hasAuthority('channelSpend:add')")
    public Result addChannelSpendData(@RequestParam Map<String, Object> map) {
        Result result = new Result();
        map.entrySet().forEach(i -> logger.info("key:" + i.getKey() + ",value:" + i.getValue()));
        ChannelSpend channelSpend = new ChannelSpend();
        if ("2".equals(map.get("tof"))) {
            transMap2Bean(map, channelSpend);
            channelSpend.setCreateTime(new Date());
            logger.info("channelSpend:" + JSONObject.toJSONString(channelSpend));
            spendService.add(channelSpend);
        } else {
            List<ChannelSpend> channelSpend2 = spendService.getByCId(map);
            transMap2Bean(map, channelSpend);
            channelSpend.setOpeningAmount(channelSpend2.get(0).getOpeningAmount());
            channelSpend.setCreateTime(new Date());
            logger.info("channelSpend:" + JSONObject.toJSONString(channelSpend));
            spendService.add(channelSpend);
        }
        result.setCode(1);
        return result;
    }

    @RequestMapping("/PopoutSerach")

    @ResponseBody
    public Result PopoutSerach(@RequestParam Map<String, Object> map) {
        Result result = new Result();
        String channelId = map.get("channelId").toString();
        int pageNo = Integer.parseInt(map.get("pageNum").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        map.put("pageNum", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //查询到渠道中的最大，最小时间
//        String maxT = channelSpendService.getMaxTimeById(Integer.valueOf(channelId));
//        String minT = channelSpendService.getMinTimeById(Integer.valueOf(channelId));
//        int chaTime = DateUtil.caculateTotalTime(maxT, minT);
//
//        //计算某一天的总花费
//        List<ChannelSpend> channelSpendList = new ArrayList<>();
//        for (int i = 0; i < chaTime; i++) {
//            String startTime = DateUtil.addDate(minT, i);
//            map.put("beginTime", startTime);
//            ChannelSpend channelSpend2 = channelSpendService.get(map);
//            ChannelSpend channelSpend3 = channelSpendService.balanceMoneyByChannelId(map);
//
//            if (null == channelSpend3) logger.info(startTime + " 前一日的余额: 0");
//            if (null != channelSpend3) {
//                if (null != channelSpend3.getBalance()) {
//                    logger.info(startTime + " 前一日的余额: " + channelSpend3.getBalance());
//                } else {
//                    channelSpend3.setBalance(new BigDecimal(0));
//                    logger.info(startTime + " 前一日的余额: 0");
//                }
//            }
//            if (null == channelSpend2) logger.info(startTime + " 这一天的充值:" + 0 + ",和消费:" + 0);
//            if (null != channelSpend2) {
//                if (null == channelSpend2.getRechargeAmount()) {
//                    channelSpend2.setRechargeAmount(new BigDecimal(0));
//                }
//                if (null == channelSpend2.getTotalCharger()) {
//                    channelSpend2.setTotalCharger(new BigDecimal(0));
//                }
//                logger.info(startTime + " 这一天的充值:" + channelSpend2.getRechargeAmount() + ",和消费:" + channelSpend2.getTotalCharger());
//            }
//
//            if (null == channelSpend3 && null != channelSpend2) {
//                channelSpend2.setBalance(channelSpend2.getOpeningAmount().add(channelSpend2.getRechargeAmount()).subtract(channelSpend2.getTotalCharger()));
//                logger.info("时间:" + startTime + ",channelSpend1:" + JSONObject.toJSONString(channelSpend2));
//                channelSpendList.add(channelSpend2);
//            }
//            if (null != channelSpend3 && null != channelSpend2) {
//                channelSpend2.setBalance(channelSpend3.getBalance().add(channelSpend2.getRechargeAmount()).subtract(channelSpend2.getTotalCharger()));
//                logger.info("时间:" + startTime + ",channelSpend2:" + JSONObject.toJSONString(channelSpend2));
//                channelSpendList.add(channelSpend2);
//            }
//        }
//        channelSpendList.forEach(i -> {
//            logger.info("1--->:" + JSONObject.toJSONString(i));
//        });
//        System.out.println("============================");


        int count = channelSpendService.selectCount(Integer.parseInt(channelId));
        List<ChannelSpend> channelSpends = channelSpendService.selectListshowMap(map);
//        channelSpends.forEach(i -> {
//            logger.info("2--->:" + JSONObject.toJSONString(i));
//        });
//        System.out.println("============================");
//        channelSpends.forEach(i ->{
//            channelSpendList.forEach(j ->{
//                if(i.getDate().equals(j.getDate())){
//                    i.setBalance(j.getBalance());
//                    i.setTotalCharger(j.getTotalCharger());
//                }
//            });
//        });
//        channelSpends.forEach(i -> {
//            logger.info("3--->:" + JSONObject.toJSONString(i));
//        });

        result.setData(channelSpends);
        result.setCount((long) count);
        return result;
    }
}
