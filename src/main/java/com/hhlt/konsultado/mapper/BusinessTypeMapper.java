package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.BusinessType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BusinessTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessType record);

    int insertSelective(BusinessType record);

    BusinessType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessType record);

    int updateByPrimaryKey(BusinessType record);

    List<BusinessType> list();

    BusinessType get(String type);
}