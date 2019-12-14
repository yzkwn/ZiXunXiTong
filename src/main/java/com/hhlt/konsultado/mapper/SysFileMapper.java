package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysFileMapper {

    int insertSysFile(SysFile sysFile);
}
