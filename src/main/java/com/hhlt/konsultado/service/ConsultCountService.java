package com.hhlt.konsultado.service;

import com.hhlt.konsultado.response.ConsultCountResponse;
import com.hhlt.konsultado.response.CountPlatBusinessResponse;

import java.util.List;

public interface ConsultCountService {

    ConsultCountResponse consultCount(String id,String beginTime, String endTime);

    List<CountPlatBusinessResponse> countPlatformBusiness(String beginTime, String endTime); //

    List<CountPlatBusinessResponse> countChannelBusiness(String beginTime, String endTime);

}
