package com.hhlt.konsultado.mapper;

import com.hhlt.konsultado.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MsgCallbackMapper {

    Visitor selectVisitor();

    int insertVisitor(Map<String,Object> map);

    int insertDialogs(Map<String,Object> map);

    int insertBusinessCard(Map<String,Object> map);

    int updateBusinessCard(Map<String,Object> map);

    Recid selectKey(String  recid);

    int updateKey (String recid);

    Map<String,Object> selectByVisitorId(String visitorId);

    int insertdatalog(Map<String,Object> map);

    int insertrecid(String recid);

    List<BusinessCard> selectdiaologs();

    int updateflag(Visitor visitor);

    List<CardVisitor> insertYongHu();

    Visitor selectRecId(Long recId);

    int updateKeyVisitor(long recid);

    int insertSelective(Visitor visitor);

    int updateKey(Long id);

    Dialogs selectID(Long id);

    int insertSelectiveDialogs(Dialogs list);

    BusinessCard selectBusinessTypeKey(String visitorID);

    int updateBusinessTypeKey(String vid);

    int  updateKeyDialogs(Long dialogsID);
}
