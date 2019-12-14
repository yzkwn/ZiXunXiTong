package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.DealData;
import com.hhlt.konsultado.mapper.DealDataMapper;
import com.hhlt.konsultado.service.DealDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DealDataServiceImpl implements DealDataService {
    @Autowired
    private DealDataMapper dealDataMapper;

    @Override
    public List<DealData> list(Map<String, Object> map) {
        return dealDataMapper.list(map);
    }

    @Override
    public Integer count(Map<String, Object> map) {
        return dealDataMapper.count(map);
    }
}
