package com.hhlt.konsultado.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hhlt.konsultado.entity.BusinessCard;
import com.hhlt.konsultado.entity.Dialogs;
import com.hhlt.konsultado.entity.Recid;
import com.hhlt.konsultado.entity.Visitor;

import com.hhlt.konsultado.service.MsgCallbackService;
import com.hhlt.konsultado.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/callback")
public class MsgCallbackController {
    private static final Logger logger = LoggerFactory.getLogger(MsgCallbackController.class);

    private static final Gson gson = new Gson();

    @Autowired
    private MsgCallbackService msgCallbackService;
    //    @Autowired
//    private dialogsCopyMapper dialogsCopyMapper;

//    @Autowired
//    private VisitorCopyMapper visitorCopyMapper;


    @GetMapping("/selectVisitor")
    public Visitor selectVisitor() {
        Visitor visitor = new Visitor();
        visitor.setBrowser("111");
        //visitor.setRecId("22222");
        //int i = msgCallbackService.insertVisitor(visitor);
        return msgCallbackService.selectVisitor();
    }

    // 聊天数据
    @PostMapping("/msg")
    //@RequestMapping()
    public Object msgcallback(@RequestBody String data) throws UnsupportedEncodingException {
        logger.info("data:-------------------" + data);
        String result = data.substring(5, data.length());
        String resultData = "";
        try {
            resultData = URLDecoder.decode(result, "UTF-8");
            logger.info("length:{}", resultData.length());
            Map<String, Object> map = JsonUtil.parse(resultData);
            if (map != null) {
                int i = msgCallbackService.insertVisitor(map);
                if (i>0){
                    logger.info("添加成功-----------");
                    if (null != map.get("dialogs")) {
                        List<Map<String, Object>> mapList = (List<Map<String, Object>>) map.get("dialogs");
                        for (Map<String, Object> map1 : mapList) {
                            int i1 = msgCallbackService.insertDialogs(map1);
                            if (i1 > 1){
                                logger.info("成功:--------------------------");
                            }
                        }
                    }
                }
            } else {
                comm(resultData);
//                List<Visitor> lis = JSONObject.parseArray(resultData, Visitor.class);
//                for (int i = 0; i < lis.size(); i++) {
//                    String s = lis.get(i).getRecId().toString();
//                    Recid recid = msgCallbackService.selectKey(s);
//                    if (recid != null) {
//                        int i1 = msgCallbackService.updateKey(s);
//                        if (i1 > 0) {
//                            logger.info("修改成功----------------------");
//                        }
//                    } else {
//                        int insertrecid = msgCallbackService.insertrecid(lis.get(i).getRecId().toString());
//                        if (insertrecid > 1) {
//                            logger.info("成功:--------------------------");
//                        }
//                    }
//                    Visitor visitorCopy = msgCallbackService.selectRecId(lis.get(i).getRecId());
//                    if (visitorCopy != null) {
//                        int i1 = msgCallbackService.updateKeyVisitor(lis.get(i).getRecId());
//                        if (i1 > 0) {
//                            logger.info("修改成功:--------------------------");
//                        }
//                    } else {
//                        int i11 = msgCallbackService.insertSelective(lis.get(i));
//                        if (i11 > 0) {
//                            logger.info("成功:--------------------------");
//                        }
//                    }
//                    if (null != lis.get(i).getDialogs()) {
//                        //List<dialogsCopy> dialogs = lis.get(i).getDialogs();
//                        for (Dialogs list : lis.get(i).getDialogs()) {
//                            Long id = list.getId();
//                            Dialogs dialogsCopy = msgCallbackService.selectID(id);
//                            if (dialogsCopy != null) {
//                                int i1 = msgCallbackService.updateKeyDialogs(id);
//                                if (i1 > 0) {
//                                    logger.info("成功:--------------------------");
//                                }
//                            } else {
//                                int i11 = msgCallbackService.insertSelectiveDialogs(list);
//                                if (i11 > 1) {
//                                    logger.info("成功:--------------------------");
//                                }
//                            }
//
//                        }
//                    }
//                }
            }
        } catch (Exception e) {
            String ss = e.getMessage();
            logger.info("data:-------------------{},CatchStatus--------->:{}", data, ss);
        }
        logger.info("data:-------------------{},StatusOK------>:{}", data, "ok");
        Object ok = "ok";
        return ok;
    }


    // 推送历史数据接口
    @PostMapping("/liaotian")
    public String liaotian(@RequestBody String data) throws UnsupportedEncodingException {
        String result = data.substring(5, data.length());
        String resultData = "";
        try {
            resultData = URLDecoder.decode(result, "UTF-8");
            logger.info("length:{}", resultData.length());
            comm(resultData);
        } catch (Exception e) {
            String ss = e.getMessage();
            logger.info("data:-------------------{},status:{}", data, ss);
        }
        logger.info("data:-------------------{},status:{}", data, "ok");
        return "ok";
    }


    // 名片数据
    @PostMapping("/businesscard")
    public Object businesscard(@RequestBody String data) {
        String result = data.substring(5, data.length());
        try {
            String resultData = URLDecoder.decode(result, "UTF-8");
            Map<String, Object> map = JsonUtil.parse(resultData);
            Object visitorId = map.get("visitorId");
            BusinessCard businessCard = msgCallbackService.selectBusinessTypeKey(visitorId.toString());
            if (businessCard != null){
                msgCallbackService.updateBusinessCard(map);
                logger.info("修改成功----------------------");
            }else{
                msgCallbackService.insertBusinessCard(map);
                logger.info("添加成功----------------------");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Object ok = "ok";
        return ok;
    }


    public void comm(String resultData) {
        List<Visitor> lis = JSONObject.parseArray(resultData, Visitor.class);
        for (int i = 0; i < lis.size(); i++) {
            String s = lis.get(i).getRecId().toString();
            Recid recid = msgCallbackService.selectKey(s);
            if (recid != null) {
                int i1 = msgCallbackService.updateKey(s);
                if (i1 > 0) {
                    logger.info("修改成功----------------------");
                }
            } else {
                int insertrecid = msgCallbackService.insertrecid(lis.get(i).getRecId().toString());
                if (insertrecid > 1) {
                    logger.info("成功:--------------------------");
                }
            }
            Visitor visitorCopy = msgCallbackService.selectRecId(lis.get(i).getRecId());
            if (visitorCopy != null) {
                int i1 = msgCallbackService.updateKeyVisitor(lis.get(i).getRecId());
                if (i1 > 0) {
                    logger.info("修改成功:--------------------------");
                }
            } else {
                int i11 = msgCallbackService.insertSelective(lis.get(i));
                if (i11 > 0) {
                    logger.info("成功:--------------------------");
                }
            }
            if (null != lis.get(i).getDialogs()) {
                //List<dialogsCopy> dialogs = lis.get(i).getDialogs();
                for (Dialogs list : lis.get(i).getDialogs()) {
                    Long id = list.getId();
                    Dialogs dialogsCopy = msgCallbackService.selectID(id);
                    if (dialogsCopy != null) {
                        int i1 = msgCallbackService.updateKeyDialogs(id);
                        if (i1 > 0) {
                            logger.info("成功:--------------------------");
                        }
                    } else {
                        int i11 = msgCallbackService.insertSelectiveDialogs(list);
                        if (i11 > 1) {
                            logger.info("成功:--------------------------");
                        }
                    }

                }
            }
        }
    }
}
