package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.CustomerLevel;
import com.hhlt.konsultado.mapper.CustomerLevelMapper;
import com.hhlt.konsultado.service.CustomerLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLevelServiceImpl implements CustomerLevelService {

    @Autowired
    private CustomerLevelMapper customerLevelMapper;

    @Override
    public List<CustomerLevel> list() {
        return customerLevelMapper.list();
    }
}
