package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.DealData;

import java.util.List;
import java.util.Map;

public interface DealDataService {

    List<DealData> list(Map<String, Object> map);

    Integer count(Map<String, Object> map);

}
