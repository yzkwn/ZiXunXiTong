package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.ChannelChild;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelChildMapper {

    List<ChannelChild> list();
}
