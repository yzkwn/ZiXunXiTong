package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.*;

import java.util.List;
import java.util.Map;

public interface MsgCallbackService {

    Visitor selectVisitor();

    int insertVisitor(Map<String,Object> map);

    int insertDialogs(Map<String,Object> map);

    int insertBusinessCard(Map<String,Object> map);

    int updateBusinessCard(Map<String,Object> map);

    Map<String,Object> selectByVisitorId(String visitorId);

    int insertdatalog(Map<String,Object> map);

    int insertrecid(String recid);

    List<BusinessCard> selectdiaologs();

    int updateflag(Visitor visitor);

    List<CardVisitor> insertYongHu();

    Recid selectKey(String  recid);

    int updateKey (String recid);

    Visitor selectRecId(Long recId);

    int updateKeyVisitor(Long recid);

    int insertSelective(Visitor visitor);

    int updateKey(Long id);

    Dialogs selectID(Long id);

    int insertSelectiveDialogs(Dialogs list);

    BusinessCard selectBusinessTypeKey(String visitorID);

    int updateBusinessTypeKey(String vid);

    int  updateKeyDialogs(Long dialogsID);

}
