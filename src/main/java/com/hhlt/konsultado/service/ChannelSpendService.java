package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.ChannelSpend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ChannelSpendService {

    Boolean check(Map<String, Object> map);

    List<ChannelSpend> getByCId(Map<String, Object> map);

    void add(ChannelSpend channelSpend);

    void update(ChannelSpend channelSpend);

    List<ChannelSpend> list(Map<String, Object> map);

    List<ChannelSpend> listBalanceMoney(Map<String, Object> map);

    List<ChannelSpend> listTotalMoney(Map<String, Object> map);

    ChannelSpend get(Map<String, Object> map);

    ChannelSpend balanceMoneyByChannelId(Map<String, Object> map);

//    List<ChannelSpend>selectListshow(Integer id,Integer pageNum,Integer pageSize);

    int selectCount(Integer id);

    String getMaxTimeById(Integer valueOf);

    String getMinTimeById(Integer valueOf);

    List<ChannelSpend> selectListshowMap(Map<String, Object> map);
}
