package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.*;
import com.hhlt.konsultado.response.ConsultDataResponse;
import com.hhlt.konsultado.service.*;
import com.hhlt.konsultado.util.FenPeiUtil;
import com.hhlt.konsultado.util.LoginUser;
import com.hhlt.konsultado.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("consult")
public class ConsultDataController {
    private static final Logger logger = LoggerFactory.getLogger(ConsultDataController.class);

    @Autowired
    private BusinessTypeService businessTypeService;
    @Autowired
    private ConsultingChannelService channelService;
    @Autowired
    private CustomerLevelService levelService;
    @Autowired
    private PlatformInformationService informationService;
    @Autowired
    private ConsultDataService dataService;
    @Autowired
    private FenPeiUtil fenPei;

    @RequestMapping("/list")
    public String consultView(Model model) {
        List<BusinessType> businessTypes = businessTypeService.list();//业务类型
        List<ConsultingChannel> consultingChannels = channelService.list();//咨询渠道
        List<PlatformInformation> platformInformations = informationService.list();
//        List<ChannelChild> childList = childService.list();//子咨询渠道

        model.addAttribute("pl", platformInformations);
        model.addAttribute("business", businessTypes);
        model.addAttribute("channels", consultingChannels);
        return "consult/list-consult";
    }

    @RequestMapping("/listSearch")
    @ResponseBody
    @PreAuthorize("hasAuthority('consultData:query')")
    public Result listSearch(@RequestParam Map<String, Object> map) {
        LoginUser user = UserUtil.getLoginUser();

        Result result = new Result();
        Map<String, Object> param = new HashMap<>();

        int pageNo = Integer.parseInt(map.get("pageNo").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<ConsultDataResponse> dataList = dataService.list(map);
        Integer count = dataService.count(map);
        param.put("list", dataList);
        param.put("total", count);
        param.put("user", user);

        result.setCode(1);
        result.setData(param);
        return result;
    }


    @RequestMapping("/add")
    public String addConsultView(Model model, String username) {
        List<BusinessType> businessTypes = businessTypeService.list();//业务类型
        List<ConsultingChannel> consultingChannels = channelService.list();//咨询渠道
        List<CustomerLevel> customerLevels = levelService.list(); //客户类型
//        List<ChannelChild> childList = childService.list();//子咨询渠道
        model.addAttribute("business", businessTypes);
        model.addAttribute("channels", consultingChannels);
        model.addAttribute("levels", customerLevels);
//        model.addAttribute("childList", childList);
        model.addAttribute("username", username);

        return "consult/add-consult";
    }

    @RequestMapping("/addData")
    @ResponseBody
    @PreAuthorize("hasAuthority('consultData:add')")
    public Result addData(@RequestParam Map<String, Object> map) {
        Result result = new Result();
        map.entrySet().forEach(i -> logger.info("addData -----> key:" + i.getKey() + ",value:" + i.getValue()));
        Object qudao = map.get("qudao");
        Object telephone = map.get("telephone");
        Object qq = map.get("qq");
        Object weixin = map.get("weixin");
        Object wangwang = map.get("wangwang");
        if (telephone.equals("") && qq.equals("") && weixin.equals("") && wangwang.equals("")) {
            if (qudao != "请选择部门" && qudao != null) {
                logger.info("不走分配路径，直接选择分配部门");
                List<PlatformInformation> informations = informationService.list(); //部门信息
                map.put("planformId", fenPei.getPlatId(informations, qudao.toString()));
                fenPei.addOrUpdate(0, map);
                result.setCode(1);
                result.setData(qudao);
                return result;
            } else {
                result = fenPei.fenpei(map, 0); //0 是添加
                return result;
            }
        } else {
            ConsultData telephone1 = dataService.telephone(telephone.toString(), qq.toString(), weixin.toString(), wangwang.toString());
            if (telephone1 != null) {
                Date createTime = telephone1.getCreateTime();
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                String format = sd.format(createTime);
                result.setMsg("此用户在" + format + "咨询过");
                result.setCode(2);
                return result;
            } else {
                if (qudao != "请选择部门" && qudao != null) {
                    logger.info("不走分配路径，直接选择分配部门");
                    List<PlatformInformation> informations = informationService.list(); //部门信息
                    map.put("planformId", fenPei.getPlatId(informations, qudao.toString()));
                    fenPei.addOrUpdate(0, map);
                    result.setCode(1);
                    result.setData(qudao);
                    return result;
                } else {
                    result = fenPei.fenpei(map, 0); //0 是添加
                    return result;
                }
            }
        }
    }

    @RequestMapping("deleteOne")
    @ResponseBody
    @PreAuthorize("hasAuthority('consultData:del')")
    public Result deleteOne(String id) {
        Result result = new Result();
        int deleteId = dataService.deleteOne(id);
        if (deleteId > 0) {
            result.setCode(1);
        } else {
            result.setCode(0);
        }
        return result;
    }

    @RequestMapping("update")
    public String update(String id, Model model) {
        List<BusinessType> businessTypes = businessTypeService.list();//业务类型
        List<ConsultingChannel> consultingChannels = channelService.list();//咨询渠道
        List<CustomerLevel> customerLevels = levelService.list(); //客户类型
        ConsultDataResponse response = dataService.get(id);
//        List<ChannelChild> childList = childService.list();//子咨询渠道
//        model.addAttribute("childList", childList);
        model.addAttribute("business", businessTypes);
        model.addAttribute("channels", consultingChannels);
        model.addAttribute("levels", customerLevels);
        model.addAttribute("id", id);
        model.addAttribute("pv", response.getProvinceNum());
        model.addAttribute("cv", response.getCityNum());

        return "consult/update-consult";
    }

    @RequestMapping("get")
    @ResponseBody
    @PreAuthorize("hasAuthority('consultData:get')")
    public Result get(String id) {
        Result result = new Result();
        ConsultDataResponse consultDataResponse = dataService.get(id);
        result.setData(consultDataResponse);
        result.setCode(1);
        return result;
    }

    @RequestMapping("updateData")
    @ResponseBody
    public Result updateData(@RequestParam Map<String, Object> map) {
        Result result = new Result();
        map.entrySet().forEach(next -> logger.info("updateData ---->:" + next.getKey() + ": " + next.getValue()));

//        Result result = fenpei(map, 1); // 1是更新
        fenPei.updateDB(map); //不走分配
        result.setCode(1);
        return result;
    }

}
