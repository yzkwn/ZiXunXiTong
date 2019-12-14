package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.ConsultData;
import com.hhlt.konsultado.response.ConsultDataResponse;
import com.hhlt.konsultado.response.PlatformNum;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ConsultDataService {

    void insert(ConsultData consultData);

    List<ConsultDataResponse> list(Map<String, Object> map);

    Integer count(Map<String, Object> map);

    int monthDataCount(String monthStr);

    List<PlatformNum> platformNums(String consultDate);

    String exportFile(Map<String, Object> map) throws IOException;

    Integer deleteOne(String id);

    ConsultDataResponse get(String id);

    void update(ConsultData consultData);

    ConsultData telephone(String telephone,String qq,String weixin,String wangwang);

    Integer countS(Map<String, Object> map);

    Integer  countA(Map<String, Object> map);

    Integer countYouXiao(Map<String, Object> map);
}
