package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.ConversionRate;
import com.hhlt.konsultado.mapper.ConversionRateMapper;
import com.hhlt.konsultado.service.ConversRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ConversRateServiceImpl implements ConversRateService {

    @Autowired
    private ConversionRateMapper mapper;

    @Override
    public int addConversRate(ConversionRate conversionRate) {

        List<ConversionRate> list = mapper.selectConversionRate(new SimpleDateFormat("yyyy-MM-dd").format(conversionRate.getDate()));
        if(list.size() < 4){
           return mapper.insertSelective(conversionRate);
        }else{
            for(int i =0;i<list.size();i++){
                if(conversionRate.getPlatformId() == list.get(i).getPlatformId()){
                    conversionRate.setId(list.get(i).getId());
                  return mapper.updateByPrimaryKeySelective(conversionRate);
                }
            }
            return 0;
        }

    }

    @Override
    public List<ConversionRate> selectPlat(String data) throws ParseException {
        Date data1 = null;
        data1 = new SimpleDateFormat("yyyy-MM").parse(data);
        List<ConversionRate> list = mapper.selectConversionRate(new SimpleDateFormat("yyyy-MM-dd").format(data1));
        return list;
    }
}
