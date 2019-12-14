package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.CustomerLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLevel record);

    int insertSelective(CustomerLevel record);

    CustomerLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLevel record);

    int updateByPrimaryKey(CustomerLevel record);

    List<CustomerLevel> list();
}