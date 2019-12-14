package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.mapper.ConsultDataMapper;
import com.hhlt.konsultado.mapper.SpendMapper;
import com.hhlt.konsultado.response.ConsultCountResponse;
import com.hhlt.konsultado.response.CountPlatBusinessResponse;
import com.hhlt.konsultado.service.ConsultCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultCountServiceImpl implements ConsultCountService {

    @Autowired
    private ConsultDataMapper mapper;

    @Override
    public ConsultCountResponse consultCount(String id,String beginTime, String endTime) {
        return mapper.consultCount(id,beginTime,endTime);
    }

    @Override
    public List<CountPlatBusinessResponse> countPlatformBusiness(String beginTime, String endTime) {
        return mapper.countPlatformBusiness(beginTime,endTime);
    }

    @Override
    public List<CountPlatBusinessResponse> countChannelBusiness(String beginTime, String endTime) {
        return mapper.countChannelBusiness(beginTime,endTime);
    }
}
