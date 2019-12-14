package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.PlatformInformation;
import com.hhlt.konsultado.mapper.PlatformInformationMapper;
import com.hhlt.konsultado.service.PlatformInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlatformInformationServiceImpl implements PlatformInformationService {

    @Autowired
    private PlatformInformationMapper  informationMapper;

    @Override
    public List<PlatformInformation> list() {
        return informationMapper.list();
    }

    @Override
    public List<PlatformInformation> listNew() {
        return informationMapper.listNew();
    }
}
