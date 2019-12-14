package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.SpendSon;

import java.util.List;


public interface SpendSonServcie {

    int insertSelective(SpendSon record);

    List<SpendSon> selectList(Integer pageNum,Integer pageSize);

    int count();
}
