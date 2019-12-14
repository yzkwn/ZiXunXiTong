package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.BusinessType;
import com.hhlt.konsultado.mapper.BusinessTypeMapper;
import com.hhlt.konsultado.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Autowired
    private BusinessTypeMapper businessTypeMapper;

    @Override
    public List<BusinessType> list() {
        return businessTypeMapper.list();
    }

    @Override
    public BusinessType get(String type) {
        return businessTypeMapper.get(type);
    }
}
