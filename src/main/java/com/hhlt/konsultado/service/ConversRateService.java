package com.hhlt.konsultado.service;


import com.hhlt.konsultado.entity.ConversionRate;

import java.text.ParseException;
import java.util.List;

public interface ConversRateService {

     int addConversRate(ConversionRate conversionRate);

     List<ConversionRate> selectPlat(String data) throws ParseException;
}
