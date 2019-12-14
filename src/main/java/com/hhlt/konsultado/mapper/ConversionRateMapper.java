package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.ConversionRate;
import com.hhlt.konsultado.response.PlatformRate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConversionRateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConversionRate record);

    int insertSelective(ConversionRate record);

    ConversionRate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConversionRate record);

    int updateByPrimaryKey(ConversionRate record);

    List<ConversionRate> selectConversionRate(String createTime);

    double avgRate(String consultDate);

    List<PlatformRate> platMap(String consultDate);
}