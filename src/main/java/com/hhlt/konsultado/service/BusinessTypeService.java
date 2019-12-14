package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.BusinessType;

import java.util.List;

public interface BusinessTypeService {

    List<BusinessType> list();

    BusinessType get(String type);
}
