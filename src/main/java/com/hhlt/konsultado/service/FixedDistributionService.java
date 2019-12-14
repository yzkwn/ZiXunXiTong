package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.FixedDistribution;

import java.text.ParseException;

public interface FixedDistributionService {

    FixedDistribution getByMonth(String monthStr);

    FixedDistribution selectData(String data) throws ParseException;
}
