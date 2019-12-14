package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.ConsultingChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ConsultingChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsultingChannel record);

    int insertSelective(ConsultingChannel record);

    ConsultingChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsultingChannel record);

    int updateByPrimaryKey(ConsultingChannel record);

    List<ConsultingChannel> list();

    ConsultingChannel selectByChannel(String channel);
}