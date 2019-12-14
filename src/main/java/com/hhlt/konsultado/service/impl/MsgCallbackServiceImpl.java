package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.*;
import com.hhlt.konsultado.mapper.MsgCallbackMapper;
import com.hhlt.konsultado.service.MsgCallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MsgCallbackServiceImpl implements MsgCallbackService {
    @Autowired
    private MsgCallbackMapper mapper;

    @Override
    public Visitor selectVisitor() {
        return mapper.selectVisitor();
    }

    @Override
    public int insertVisitor(Map<String,Object> map) {
        return mapper.insertVisitor(map);
    }

    @Override
    public int insertDialogs(Map<String,Object> map) {
        return mapper.insertDialogs(map);
    }

    @Override
    public int insertBusinessCard(Map<String,Object> map) {
        return mapper.insertBusinessCard(map);
    }

    @Override
    public int updateBusinessCard(Map<String, Object> map) {
        return mapper.updateBusinessCard(map);
    }

    @Override
    public Map<String, Object> selectByVisitorId(String visitorId) {
        return mapper.selectByVisitorId(visitorId);
    }

    @Override
    public int insertdatalog(Map<String, Object> map) {
        return mapper.insertdatalog(map);
    }

    @Override
    public int insertrecid(String recid) {
        return mapper.insertrecid(recid);
    }

    @Override
    public List<BusinessCard> selectdiaologs() {
        return mapper.selectdiaologs();
    }

    @Override
    public int updateflag(Visitor visitor) {
        return mapper.updateflag(visitor);
    }

    @Override
    public List<CardVisitor> insertYongHu() {
        return mapper.insertYongHu();
    }

    @Override
    public Recid selectKey(String recid) {
        return mapper.selectKey(recid);
    }

    @Override
    public int updateKey(String recid) {
        return mapper.updateKey(recid);
    }

    @Override
    public Visitor selectRecId(Long recId) {
        return mapper.selectRecId(recId);
    }

    @Override
    public int updateKeyVisitor(Long recid) {
        return mapper.updateKeyVisitor(recid);
    }

    @Override
    public int insertSelective(Visitor visitor) {
        return mapper.insertSelective(visitor);
    }

    @Override
    public int updateKey(Long id) {
        return mapper.updateKey(id);
    }

    @Override
    public Dialogs selectID(Long id) {
        return mapper.selectID(id);
    }

    @Override
    public int insertSelectiveDialogs(Dialogs list) {
        return mapper.insertSelectiveDialogs(list);
    }

    @Override
    public BusinessCard selectBusinessTypeKey(String visitorID) {
        return mapper.selectBusinessTypeKey(visitorID);
    }

    @Override
    public int updateBusinessTypeKey(String vid) {
        return mapper.updateBusinessTypeKey(vid);
    }

    @Override
    public int updateKeyDialogs(Long dialogsID) {
        return mapper.updateKeyDialogs(dialogsID);
    }
}
