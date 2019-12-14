package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.mapper.ConversionRateMapper;
import com.hhlt.konsultado.response.PlatformRate;
import com.hhlt.konsultado.service.ConversionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversionRateServiceImpl implements ConversionRateService {
    @Autowired
    private ConversionRateMapper rateMapper;

    @Override
    public double avgRate(String consultDate) {
        return rateMapper.avgRate(consultDate);
    }

    @Override
    public List<PlatformRate> platMap(String consultDate) {
        return rateMapper.platMap(consultDate);
    }
}
