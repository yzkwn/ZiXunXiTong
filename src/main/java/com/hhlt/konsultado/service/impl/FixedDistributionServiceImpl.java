package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.FixedDistribution;
import com.hhlt.konsultado.mapper.FixedDistributionMapper;
import com.hhlt.konsultado.service.FixedDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FixedDistributionServiceImpl implements FixedDistributionService {
    @Autowired
    private FixedDistributionMapper mapper;

    @Override
    public FixedDistribution getByMonth(String monthStr) {
        return mapper.getByMonth(monthStr);
    }

    @Override
    public FixedDistribution selectData (String date) throws ParseException {
        Date data1 = null;
        data1 = new SimpleDateFormat("yyyy-MM").parse(date);
        return mapper.selectData(new SimpleDateFormat("yyyy-MM-dd").format(data1));
    }
}
