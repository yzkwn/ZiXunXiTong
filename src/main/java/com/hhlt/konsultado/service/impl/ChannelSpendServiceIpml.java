package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.ChannelSpend;
import com.hhlt.konsultado.mapper.ChannelSpendMapper;
import com.hhlt.konsultado.service.ChannelSpendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChannelSpendServiceIpml implements ChannelSpendService {

    @Autowired
    private ChannelSpendMapper channelSpendMapper;
    private static final Logger logger = LoggerFactory.getLogger(ChannelSpendService.class);

    @Override
    public Boolean check(Map<String, Object> map) {
        int count = channelSpendMapper.check(map);
        return count >= 1;
    }

    @Override
    public List<ChannelSpend> getByCId(Map<String, Object> map) {
        return channelSpendMapper.getByCId(map);
    }

    @Override
    public void add(ChannelSpend channelSpend) {
        channelSpendMapper.insertSelective(channelSpend);
    }

    @Override
    public void update(ChannelSpend channelSpend) {
        channelSpendMapper.updateByPrimaryKeySelective(channelSpend);
    }

    @Override
    public List<ChannelSpend> list(Map<String, Object> map) {
        return channelSpendMapper.list(map);
    }

    @Override
    public List<ChannelSpend> listBalanceMoney(Map<String, Object> map) {
        map.entrySet().forEach(i -> logger.info("listBalanceMoney----->:" + i.getKey() + " : " + i.getValue()));
        return channelSpendMapper.listBalanceMoney(map);
    }

    @Override
    public List<ChannelSpend> listTotalMoney(Map<String, Object> map) {
        map.entrySet().forEach(i -> logger.info("listTotalMoney----->:" + i.getKey() + " : " + i.getValue()));
        return channelSpendMapper.listTotalMoney(map);
    }

    @Override
    public ChannelSpend get(Map<String, Object> map) {
        return channelSpendMapper.get(map);
    }

    @Override
    public ChannelSpend balanceMoneyByChannelId(Map<String, Object> map) {
        return channelSpendMapper.balanceMoneyByChannelId(map);
    }

//    @Override
//    public List<ChannelSpend> selectListshow(Integer id,Integer pageNum,Integer pageSize) {
//        return channelSpendMapper.selectListshow(id,pageNum,pageSize);
//    }

    @Override
    public int selectCount(Integer id) {
        return channelSpendMapper.selectCount(id);
    }

    @Override
    public String getMaxTimeById(Integer valueOf) {
        return  channelSpendMapper.getMaxTimeById(valueOf);
    }

    @Override
    public String getMinTimeById(Integer valueOf) {
        return  channelSpendMapper.getMinTimeById(valueOf);
    }

    @Override
    public List<ChannelSpend> selectListshowMap(Map<String, Object> map) {
        return channelSpendMapper.selectListshowMap(map);
    }


}
