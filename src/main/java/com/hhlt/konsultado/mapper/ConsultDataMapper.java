package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.ConsultData;
import com.hhlt.konsultado.response.ConsultCountResponse;
import com.hhlt.konsultado.response.ConsultDataResponse;
import com.hhlt.konsultado.response.CountPlatBusinessResponse;
import com.hhlt.konsultado.response.PlatformNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConsultDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsultData record);

    int insertSelective(ConsultData record);

    ConsultData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsultData record);

    int updateByPrimaryKey(ConsultData record);

    List<ConsultDataResponse> list(Map<String, Object> map);

    List<ConsultDataResponse> excelList(Map<String, Object> map);

    Integer count(Map<String, Object> map);

    int monthDataCount(String monthStr);

    List<PlatformNum> platformNums(String consultDate);

    ConsultDataResponse get(Integer valueOf);

    ConsultCountResponse consultCount(String id, String beginTime, String endTime);

    List<CountPlatBusinessResponse> countPlatformBusiness(String beginTime, String endTime);

    List<CountPlatBusinessResponse> countChannelBusiness(String beginTime, String endTime);

    ConsultData telephone(String telephone,String qq,String weixin,String wangwang);

    Integer countS(Map<String, Object> map);

   Integer  countA(Map<String, Object> map);

    Integer countYouXiao(Map<String, Object> map);


}