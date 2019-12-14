package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.PlatformInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlatformInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatformInformation record);

    int insertSelective(PlatformInformation record);

    PlatformInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformInformation record);

    int updateByPrimaryKey(PlatformInformation record);

    List<PlatformInformation> list();

    List<PlatformInformation> listNew();
}