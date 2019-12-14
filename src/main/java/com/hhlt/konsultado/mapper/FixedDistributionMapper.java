package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.FixedDistribution;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FixedDistributionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FixedDistribution record);

    int insertSelective(FixedDistribution record);

    FixedDistribution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FixedDistribution record);

    int updateByPrimaryKey(FixedDistribution record);

    FixedDistribution getByMonth(String monthStr);

    FixedDistribution selectData(String data);
}