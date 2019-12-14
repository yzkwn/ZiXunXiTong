package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.SonSpend;
import com.hhlt.konsultado.entity.Spend;
import com.hhlt.konsultado.response.ConsultCountResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface SpendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Spend record);

    int insertSelective(Spend record);

    Spend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Spend record);

    int updateByPrimaryKey(Spend record);

    //    List<Spend> selectSpendList(Spend record);
    List<Spend> selectSpendList(Map map);

    List<Spend> selectSpendListTwo();


    int selectCount(Map map);

    List<SonSpend> selectSpendGroupBy(Spend record);

    List<SonSpend> selectGroupByChannelId(Spend record);

    List<String> colSelect(@Param("colselect") String colSelect);

    Spend slectSpend(Spend record);

    List<ConsultCountResponse> consultCount(String type, String beginTime, String endTime);


    List<Spend> selectSpendListExcel(HashMap map);

    List<Spend>selectByPrimary();
}