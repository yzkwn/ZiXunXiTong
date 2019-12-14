package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.FixedDistribution;
import com.hhlt.konsultado.mapper.FixedDistributionMapper;
import com.hhlt.konsultado.service.FixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class FixedServiceImpl implements FixedService {

    @Autowired
    private FixedDistributionMapper mapper;

    @Override
    public int insertSelective(FixedDistribution fixedDistribution) {
        FixedDistribution fixedDistribution1 = mapper.selectData(new SimpleDateFormat("yyyy-MM-dd").format(fixedDistribution.getDate()));
        if(null == fixedDistribution1){
            mapper.insertSelective(fixedDistribution);
            return  fixedDistribution.getId();
        }else{
            fixedDistribution.setId(fixedDistribution1.getId());
            mapper.updateByPrimaryKeySelective(fixedDistribution);
            return fixedDistribution.getId();
        }

    }
}
