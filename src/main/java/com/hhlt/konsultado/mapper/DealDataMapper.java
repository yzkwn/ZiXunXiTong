package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.DealData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DealDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealData record);

    int insertSelective(DealData record);

    DealData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealData record);

    int updateByPrimaryKey(DealData record);

    List<DealData> list(Map<String, Object> map);

    Integer count(Map<String, Object> map);
}