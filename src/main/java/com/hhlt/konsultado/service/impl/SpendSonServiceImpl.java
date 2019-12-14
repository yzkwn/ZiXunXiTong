package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.SpendSon;
import com.hhlt.konsultado.mapper.SpendSonMapper;
import com.hhlt.konsultado.service.SpendSonServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendSonServiceImpl implements SpendSonServcie {
    @Autowired
    private SpendSonMapper spendSonMapper;
    @Override
    public int insertSelective(SpendSon record) {
        return spendSonMapper.insertSelective(record);
    }

    @Override
    public List<SpendSon> selectList(Integer pageNum,Integer pageSize) {
        return spendSonMapper.selectList(pageNum,pageSize);
    }

    @Override
    public int count() {
        return spendSonMapper.count();
    }
}
