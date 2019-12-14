package com.hhlt.konsultado.service;

import com.hhlt.konsultado.response.PlatformRate;

import java.util.List;

public interface ConversionRateService {
    double avgRate(String consultDate);

    List<PlatformRate> platMap(String consultDate);
}
