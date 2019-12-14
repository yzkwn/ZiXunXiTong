package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SpendChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpendChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpendChannel record);

    int insertSelective(SpendChannel record);

    SpendChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpendChannel record);

    int updateByPrimaryKey(SpendChannel record);

    List<SpendChannel> list();

    SpendChannel selectByChannel(String channel);
}