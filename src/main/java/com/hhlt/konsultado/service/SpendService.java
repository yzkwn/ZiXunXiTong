package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.BusinessType;
import com.hhlt.konsultado.entity.ConsultingChannel;
import com.hhlt.konsultado.entity.SonSpend;
import com.hhlt.konsultado.entity.Spend;
import com.hhlt.konsultado.response.ConsultCountResponse;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SpendService {

    //    int saveSpend(Map<String,Object> map) throws ParseException;
//
    List<BusinessType> selectBusinessType();

    //
    List<ConsultingChannel> selectSpendChannel();
//
    int insertSelective(Spend spend);


    //添加花费项
    int saveSpend(Map map) throws ParseException;

    //查询spend表所有(包含模糊查询)
    List<Spend> selectSpendList(Map map) throws ParseException;

    List<Spend> selectSpendListTwo();

    int selectCount(Map map);

    //分组查询（根据时间查询）
    List<SonSpend> selectSpendGroupBy(Map map);

    //分组查询（根据渠道查询）
    List<SonSpend> selectGroupByChannelId(Map map);


    Spend slectSpend(Spend record);

    List<ConsultCountResponse> consultCount(String type, String beginTime, String endTime);


    List<Spend> selectSpendListExcel(HashMap map);

    List<Spend>selectByPrimary();


}
