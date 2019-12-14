package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.SysFile;
import com.hhlt.konsultado.mapper.SysFileMapper;
import com.hhlt.konsultado.service.SysFileSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysFileServiceImpl implements SysFileSerivce {

    @Autowired
    private SysFileMapper mapper;

    @Override
    public int insertSysFile(SysFile sysFile) {
        return mapper.insertSysFile(sysFile);
    }
}
