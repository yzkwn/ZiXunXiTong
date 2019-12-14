package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.BusinessType;
import com.hhlt.konsultado.entity.ConsultingChannel;
import com.hhlt.konsultado.entity.SonSpend;
import com.hhlt.konsultado.entity.Spend;
import com.hhlt.konsultado.mapper.BusinessTypeMapper;
import com.hhlt.konsultado.mapper.SpendChannelMapper;
import com.hhlt.konsultado.mapper.SpendMapper;
import com.hhlt.konsultado.mapper.SpendSonMapper;
import com.hhlt.konsultado.response.ConsultCountResponse;
import com.hhlt.konsultado.service.ConsultingChannelService;
import com.hhlt.konsultado.service.SpendService;
import com.hhlt.konsultado.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpendServiceImpl implements SpendService {

    @Autowired
    private SpendMapper mapper;
    @Autowired
    private BusinessTypeMapper businessTypeMapper;
    @Autowired
    private SpendChannelMapper spendChannelMapper;
    @Autowired
    private SpendSonMapper spendSonMapper;

    @Autowired
    private ConsultingChannelService consultingChannelService;

    @Value("${exportFile}")
    private String exportFile;
    @Value("${exportFileLinux}")
    private String exportFileLinux;

    @Override
    public int saveSpend(Map map) throws ParseException {

        if (StringUtils.isNotEmpty(map.get("adPv").toString()) && StringUtils.isNotEmpty(map.get("click").toString()) && StringUtils.isNotEmpty(map.get("charge").toString())) {
            Spend spend = new Spend();
            //展现量              StringUtils.isNotEmpty(str)等价于 str != null && str.length > 0。
            if (StringUtils.isNotEmpty(map.get("adPv").toString()))
                spend.setAdpv(new BigDecimal(map.get("adPv").toString()));


            //点击
            if (StringUtils.isNotEmpty(map.get("click").toString()))
                spend.setClick(new BigDecimal(map.get("click").toString()));


            //当日消耗
            if (StringUtils.isNotEmpty(map.get("charge").toString()))
                spend.setCharge(new BigDecimal(map.get("charge").toString()));


            //当前日期
            if (StringUtils.isNotEmpty(map.get("data").toString()))
                spend.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("data").toString()));

            //渠道id
            if (StringUtils.isNotEmpty(map.get("channel_id").toString()))
                spend.setChannelId(map.get("channel_id").toString());


            //业务类型id
            if (StringUtils.isNotEmpty(map.get("business_type_id").toString()))
                spend.setBusinessTypeId(map.get("business_type_id").toString());

            spend.setCreateTime(DateUtil.getDateTime());
            return mapper.insertSelective(spend);
        } else {
            return 0;
        }
    }

    @Override
    public List<Spend> selectSpendList(Map map) {
//        Object pageNum = map.get("pageNum");
//        Object pageSize = map.get("pageSize");
//        Spend spend = new Spend();
//        if (StringUtils.isNotEmpty(map.get("beginTime").toString())) {
//            try {
//                spend.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("beginTime").toString()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        if (StringUtils.isNotEmpty(map.get("typeId").toString()))
//            spend.setBusinessTypeId(map.get("typeId").toString());
//        if (StringUtils.isNotEmpty(map.get("channelId").toString()))
//            spend.setChannelId(map.get("channelId").toString());
//        if (pageNum != null) {
//            spend.setPageNum(Integer.valueOf(map.get("pageNum").toString()));
//        }
//        if (null != pageSize) {
//            spend.setPageSize(Integer.valueOf(map.get("pageSize").toString()));
//        }
//        logger.info("spend:" + JSONObject.toJSONString(spend));
        return mapper.selectSpendList(map);
    }

    @Override
    public List<Spend> selectSpendListTwo() {
        return mapper.selectSpendListTwo();
    }

    @Override
    public int selectCount(Map map) {
        return mapper.selectCount(map);
    }

    @Override
    public List<SonSpend> selectSpendGroupBy(Map map) {
        Spend spend = new Spend();
        if (StringUtils.isNotEmpty(map.get("beginTime").toString())) {
            spend.setBeginTime(map.get("beginTime").toString());
        }
        if (StringUtils.isNotEmpty(map.get("endTime").toString())) {
            spend.setEndTime(map.get("endTime").toString());
        }
        return mapper.selectSpendGroupBy(spend);
    }

    @Override
    public List<SonSpend> selectGroupByChannelId(Map map) {
        Spend spend = new Spend();
        if (StringUtils.isNotEmpty(map.get("beginTime").toString())) {
            spend.setBeginTime(map.get("beginTime").toString());
        }
        if (StringUtils.isNotEmpty(map.get("endTime").toString())) {
            spend.setEndTime(map.get("endTime").toString());
        }
        if (StringUtils.isNotEmpty(map.get("channelId").toString())) {
            spend.setChannelId(map.get("channelId").toString());
        }
        return mapper.selectGroupByChannelId(spend);
    }

    @Override
    public Spend slectSpend(Spend record) {
        return mapper.slectSpend(record);
    }

    @Override
    public List<ConsultCountResponse> consultCount(String type, String beginTime, String endTime) {
        return mapper.consultCount(type, beginTime, endTime);
    }

    @Override
    public List<Spend> selectSpendListExcel(HashMap map) {
        return mapper.selectSpendListExcel(map);
    }

    @Override
    public List<Spend> selectByPrimary() {
        return mapper.selectByPrimary();
    }


    @Override
    public List<BusinessType> selectBusinessType() {
        return businessTypeMapper.list();
    }

    @Override
    public List<ConsultingChannel> selectSpendChannel() {
        return consultingChannelService.list();
    }

    @Override
    public int insertSelective(Spend spend) {
        return mapper.insertSelective(spend);
    }


}
