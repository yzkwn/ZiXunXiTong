package com.hhlt.konsultado.controller;



import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.ConversionRate;
import com.hhlt.konsultado.entity.FixedDistribution;
import com.hhlt.konsultado.service.ConversRateService;
import com.hhlt.konsultado.service.FixedDistributionService;
import com.hhlt.konsultado.service.FixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/rate")
public class ConversRateController {

    @Autowired
    private ConversRateService conversRateService;

    @Autowired
    private FixedService fixedService;

    @Autowired
    private FixedDistributionService fixedDistributionService;

    // 跳转到转化率列表
    @GetMapping("/jumpRateList")
        public String jumpRateList(){
        return "ConversionRate/ConversionRateList";
    }

    @RequestMapping("/selectPlat")
    @ResponseBody
    public Result selectPlat(@RequestParam String data) throws ParseException {
        Result result = new Result();
        Map<String,Object> map = new HashMap<>();
        List<ConversionRate> list = conversRateService.selectPlat(data);
        FixedDistribution fixedDistribution = fixedDistributionService.selectData(data);
        map.put("list",list);
        map.put("fixed",fixedDistribution);
        result.setCode(list.size() ==0?1:0);
        result.setData(map);
        return result;
    }


    @PostMapping("/savePlat")
    @PreAuthorize("hasAuthority('sys : savePlat')")
    @ResponseBody
    public Result savePlat(@RequestParam Map<String,Object> map) throws ParseException {
        Result result = new Result();
        if("5".equals(map.get("platformId").toString())){
            FixedDistribution fixedDistribution = new FixedDistribution();
            fixedDistribution.setOperate1(Double.parseDouble(map.get("operate1").toString()));
            fixedDistribution.setOperate2(Double.parseDouble(map.get("operate2").toString()));
            fixedDistribution.setOperate3(Double.parseDouble(map.get("operate3").toString()));
            fixedDistribution.setOperate4(Double.parseDouble(map.get("operate4").toString()));
            fixedDistribution.setOperate5(Double.parseDouble(map.get("operate5").toString()));
            fixedDistribution.setOperate6(Double.parseDouble(map.get("operate6").toString()));
            fixedDistribution.setOperate7(Double.parseDouble(map.get("operate7").toString()));
            fixedDistribution.setDate(new SimpleDateFormat("yyyy-MM").parse(map.get("date").toString()));
            fixedDistribution.setCreateTime(new Date());
            Integer id = fixedService.insertSelective(fixedDistribution);
            result.setCode(0);
            result.setData(id);
        }else{
            ConversionRate conversionRate = new ConversionRate();
            conversionRate.setFixedId(Integer.parseInt(map.get("fixedId").toString()));
            conversionRate.setPlatformId(Integer.parseInt(map.get("platformId").toString()));
            conversionRate.setOperate1(Double.parseDouble(map.get("operate1").toString()));
            conversionRate.setOperate2(Double.parseDouble(map.get("operate2").toString()));
            conversionRate.setOperate3(Double.parseDouble(map.get("operate3").toString()));
            conversionRate.setOperate4(Double.parseDouble(map.get("operate4").toString()));
            conversionRate.setOperate5(Double.parseDouble(map.get("operate5").toString()));
            conversionRate.setOperate6(Double.parseDouble(map.get("operate6").toString()));
            conversionRate.setOperate7(Double.parseDouble(map.get("operate7").toString()));
            conversionRate.setDate(new SimpleDateFormat("yyyy-MM").parse(map.get("date").toString()));
            conversionRate.setCreateTime(new Date());
            result.setCode(conversRateService.addConversRate(conversionRate));
        }

        return result;
    }




}
