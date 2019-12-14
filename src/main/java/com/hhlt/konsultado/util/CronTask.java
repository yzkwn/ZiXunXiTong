package com.hhlt.konsultado.util;

import com.hhlt.konsultado.controller.MsgCallbackController;
import com.hhlt.konsultado.mapper.BusinessTypeMapper;
import com.hhlt.konsultado.mapper.ConsultingChannelMapper;
import com.hhlt.konsultado.mapper.MsgCallbackMapper;
import com.hhlt.konsultado.mapper.TokenMapper;
import com.hhlt.konsultado.service.MsgCallbackService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
@EnableScheduling   // 2.开启定时任务
public class CronTask {
    private Logger logger = LoggerFactory.getLogger(CronTask.class);
    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private MsgCallbackService msgCallbackService;
//    @Autowired
//    private FenPei fenPei;
//    @Autowired
//    private BusinessTypeMapper businessTypeMapper;
//    @Autowired
//    private ConsultingChannelMapper consultingChannelMapper;
//    @Autowired
//    private MsgCallbackMapper msgCallbackMapper;

    @Scheduled(cron = "0/5 * * * * *")
    public void deleteToken() {
//        logger.info("删除过期token的定时任务");
        tokenMapper.deleteExpiredToken();
    }
//
//    @Scheduled(cron = "0 0 0")
//   public List list() {
//        //每2分钟查询一下的地址 渠道 类型
//        List<BusinessCard> selectdiaologs = msgCallbackMapper.selectdiaologs();
//
//        HashMap map = new HashMap();
//        //区域地址
//        String a = null;
//        //渠道类型
//        String b = null;
//        String c = "(百度)";
//        String d = null;
//
//        Visitor visitor = new Visitor();
//        logger.info("集合—————————————》", selectdiaologs.size());
//        for (BusinessCard s : selectdiaologs) {
//            String substring1 = null;
//            //业务类型
//            String col1 = s.getCol1();
//            if (col1 == null){
//                continue;
//            }
//            String linkman = s.getLinkman();
//            String addtime = s.getAddtime();
//            String substring = addtime.substring(0, 10);
//            String[] split = linkman.split("\\|");
//            if (split.length == 2) {
//                a = split[0];
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + a.length());
//                if (a.length() >= 4) {
//                    if (a.indexOf("山东") != -1) {
//                        substring1 = a.substring(2, 4);//市
//                    }
//                    b = split[1];
//                    d = b + c;
//                    BusinessType businessType = businessTypeMapper.get(col1);
//                    ConsultingChannel consultingChannel = consultingChannelMapper.selectByChannel(d);
//                    if (businessType == null) {
//                        continue;
//                    } else {
//                        map.put("typeId", businessType.getId());
//                        map.put("channelName", d);//渠道
//                        map.put("typeName", col1);//业务
//                        map.put("province", a);//地址
//                        map.put("consultDate", substring);//时间
//                        map.put("city", substring1);//市
//                        map.put("channelId", consultingChannel.getId());
//                        Result result = fenPei.fenpei(map, 0); // 0是添加  1是更新
//                            int sum = 0;
//                            sum += 1;
//                        Long recId = s.getRecId();
//                        visitor.setFlag(1);
//                        visitor.setConnectionTimes(sum);
//                        visitor.setRecId(recId);
//                        int updateflag = msgCallbackService.updateflag(visitor);
//                    }
//                }
//            }
//        }
//
//
//        return selectdiaologs;
//    }


    // 推送历史数据接口
    @Scheduled(cron = "0 0 23 * * ?")
    @PostMapping("/liaotian")
    public String liaotian() throws UnsupportedEncodingException {
        String data = HttpUtils.doPost("http://qfak60.kuaishang.cn/bs/ksapi/repush.do?ak=d3e74583c12a4fe9bfd37e12b5e53e19&tt=1570608944660&kssign=8d804de83053cac21655259142029b2f672ae9d1&pt=HISTORYDATA&pu=http://kuaishangtong.honghailt.com/konsult/api/callback/msg", null, 5000);
        if (StringUtils.isBlank(data)) {
            logger.info("data++++++++++++++++++++++++++++++++++++++++:没有数据", data);
            return null;
        } else {
            MsgCallbackController msgCallbackController = new MsgCallbackController();
            String result = data.substring(5, data.length());
            String resultData = "";
            try {
                resultData = URLDecoder.decode(result, "UTF-8");
                logger.info("length:{}", resultData.length());
                msgCallbackController.comm(resultData);
            } catch (Exception e) {
                String ss = e.getMessage();
                logger.info("data:-------------------{},status:{}", data, ss);
            }
            logger.info("data:-------------------{},status:{}", data, "ok");
            return "ok";
        }
    }
}
