package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SpendSon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpendSonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpendSon record);

    int insertSelective(SpendSon record);

    SpendSon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpendSon record);

    int updateByPrimaryKey(SpendSon record);

    List<SpendSon> selectList(Integer pageNum,Integer pageSize);

    int count();
}