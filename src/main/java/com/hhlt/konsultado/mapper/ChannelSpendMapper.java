package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.ChannelSpend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ChannelSpendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChannelSpend record);

    int insertSelective(ChannelSpend record);

    ChannelSpend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChannelSpend record);

    int updateByPrimaryKey(ChannelSpend record);

    int check(Map<String, Object> map);

    List<ChannelSpend> getByCId(Map<String, Object> map);

    List<ChannelSpend> list(Map<String, Object> map);

    List<ChannelSpend> listBalanceMoney(Map<String, Object> map); //<!--开始时间之前的余额-->

    List<ChannelSpend> listTotalMoney(Map<String, Object> map); // <!--一段时间的充值和总花费-->


    ChannelSpend balanceMoneyByChannelId(Map<String, Object> map);

    ChannelSpend get(Map<String, Object> map);

//    List<ChannelSpend> selectListshow(Integer id, Integer pageNum, Integer pageSize);

    int selectCount(Integer id);

    String getMaxTimeById(Integer id);

    String getMinTimeById(Integer id);

    List<ChannelSpend> selectListshowMap(Map<String, Object> map);
}