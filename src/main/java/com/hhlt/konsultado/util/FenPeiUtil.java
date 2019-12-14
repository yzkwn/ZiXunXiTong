package com.hhlt.konsultado.util;

import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.ConsultData;
import com.hhlt.konsultado.entity.FixedDistribution;
import com.hhlt.konsultado.entity.PlatformInformation;
import com.hhlt.konsultado.response.PlatformNum;
import com.hhlt.konsultado.response.PlatformRate;
import com.hhlt.konsultado.service.ConsultDataService;
import com.hhlt.konsultado.service.ConversionRateService;
import com.hhlt.konsultado.service.FixedDistributionService;
import com.hhlt.konsultado.service.PlatformInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.hhlt.konsultado.util.MapToObject.transMap2Bean;

@Component
public class FenPeiUtil {

    @Autowired
    private PlatformInformationService informationService;
    @Autowired
    private FixedDistributionService fixedService;
    @Autowired
    private ConsultDataService dataService;
    @Autowired
    private ConversionRateService rateService;

    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
    DecimalFormat df = new DecimalFormat("#.00");
    private static final Logger logger = LoggerFactory.getLogger(FenPeiUtil.class);

    public Result fenpei(Map<String, Object> map, int integer) {
        Result result = new Result();
        try {
            List<PlatformInformation> informations = informationService.list(); //部门信息

            List<String> keywordProvince = Arrays.asList("山东省", "河北省", "天津", "北京", "安徽省"); //特殊省份
//        List<String> keywordType = Arrays.asList("软件", "内容", "客服", "代运营", "直通车", "钻展");  // 特殊业务类型
            List<String> keywordChannel = Arrays.asList("今日头条", "自营网店瓣装女装", "自营网店洪海青岛");//特殊的咨询渠道

            String channel = map.get("channelName").toString();//咨询渠道
            String type = map.get("typeName").toString(); //业务类型

            //咨询日期比转化率存储日期晚一个月
            String consultDate = map.get("consultDate").toString().substring(0, 7);
            Date parse = sdf2.parse(consultDate);
            parse.setMonth(parse.getMonth() - 1);
            String trueDate = sdf2.format(parse);//查询转化率真正的日期
            FixedDistribution fD = fixedService.getByMonth(trueDate);//根据服务日期 查询出上个月的固定转化率

            if (keywordChannel.contains(channel)) {
                logger.info("咨询渠道为：-------------> {}", channel);
                map.put("planformId", getPlatId(informations, "青岛SMB"));
                addOrUpdate(integer, map);
                result.setCode(1);
                result.setData("青岛SMB");
            } else {
                logger.info("咨询渠道为：-------------> {}", channel);
                if ("直通车".equals(type) || "钻展".equals(type) || "京东代运营".equals(type)) {
                    logger.info("业务类型为：-------------> {}", type);
                    map.put("planformId", getPlatId(informations, "济南平台"));
                    addOrUpdate(integer, map);
                    result.setCode(1);
                    result.setData("济南平台");
                } else if ("软件".equals(type)) {
                    logger.info("业务类型为：-------------> {}", type);
                    map.put("planformId", getPlatId(informations, "济南软件"));
                    addOrUpdate(integer, map);
                    result.setCode(1);
                    result.setData("济南软件");
                } else if ("代运营".equals(type) && ! channel.equals("洪海服务市场")) {
                    logger.info("业务类型为：-------------> {}", type);
                    result = daiYunYing(map, informations, keywordProvince, integer, fD.getOperate1(), channel);//代运营转化率
                } else if ("代入驻".equals(type) && "官网".equals(channel) || "代入驻".equals(type) && "青岛官网".equals(channel)) {
                    logger.info("业务类型为：-------------> {},咨询渠道为：---------->{}", type, channel);
                    map.put("planformId", getPlatId(informations, "合道共创"));
                    addOrUpdate(integer, map);
                    result.setCode(1);
                    result.setData("合道共创");
                } else {
                    logger.info("业务类型为：-------------> {}", type);
                    // 其他渠道，其他业务类型，直接分配给青岛SMB部门。
                    map.put("planformId", getPlatId(informations, "青岛SMB"));
                    addOrUpdate(integer, map);
                    result.setCode(1);
                    result.setData("青岛SMB");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;

    }

    //代运营的按照 所在省市地域进行分配
    public Result daiYunYing(Map<String, Object> map, List<PlatformInformation> informations, List<String> keywordProvince,
                             Integer integer, Double fD, String channel) {
        Result result = new Result();
        String province = map.get("province").toString();//省份

        if ("山东省".equals(province)) {
            String city = map.get("city").toString();
            if ("青岛".equals(city) || "威海".equals(city) || "烟台".equals(city)) {
                logger.info("山东 胶州半岛区域：{}", map.get("city").toString());
                map.put("planformId", getPlatId(informations, "青岛平台"));
                addOrUpdate(integer, map);
                result.setCode(1);
                result.setData("青岛平台");
            } else {
                logger.info("山东 胶州半岛以外区域：{}", map.get("city").toString());
                map.put("planformId", getPlatId(informations, "济南平台"));
                addOrUpdate(integer, map);
                result.setCode(1);
                result.setData("济南平台");
            }
        } else if ("河北省".equals(province) || "天津".equals(province) || "北京".equals(province)) {
            logger.info("province:" + province);
            map.put("planformId", getPlatId(informations, "北京平台"));
            addOrUpdate(integer, map);
            result.setCode(1);
            result.setData("北京平台");
        } else if ("安徽省".equals(province)) {
            logger.info("province:" + province);
            map.put("planformId", getPlatId(informations, "合肥平台"));
            addOrUpdate(integer, map);
            result.setCode(1);
            result.setData("合肥平台");
        } /*else if ("浙江省".equals(province) && "官网".equals(channel) || "浙江省".equals(province) && "青岛官网".equals(channel)) {
            String city = map.get("city").toString();
            if ("杭州".equals(city)) {
                map.put("planformId", getPlatId(informations, "杭州平台"));
                addOrUpdate(integer, map);
                result.setCode(1);
                result.setData("杭州平台");
            }else{
                return otherType(map, informations, fD, integer); //代运营转化率
            }
        } */else if (!keywordProvince.contains(province)) {
            logger.info("其他省市 province:" + province);
            //        各个平台代运营分配咨询数=总咨询数*0.18+总咨询数*0.28/4+总咨询数*0.28*（上月转化率-平均转化率）/（平均转化率/4）
            return otherType(map, informations, fD, integer); //代运营转化率
        }
        return result;
    }

    //其他的省市
    //(1.先获取当月的 2.调用getTRUEDate方法获取上月的转化率 3.求出总咨询数 4.上月转化率 倒序排序)
    public Result otherType(Map<String, Object> map, List<PlatformInformation> informations, Double num, int integer) {
        Result result = new Result();
        String consultDate = map.get("consultDate").toString().substring(0, 7);    //[)
        String trueDate = getTrueDate(consultDate);

        int count = dataService.monthDataCount(consultDate); //按月的总咨询数据
        logger.info("本月的总咨询数据：" + count);
        int totalCount;
        if (integer == 1) { //更新
            totalCount = count - 1;
        } else {
            totalCount = count;
        }
        logger.info("更新之后的本月的总咨询数据：" + totalCount);
        // double avgRate = rateService.avgRate(trueDate) + num;     //按月份的平均转化率(num:上月份固定转化率)
        double avgRate = rateService.avgRate(trueDate); //按月份的平均转化率(num:上月份固定转化率)
        logger.info("本月的平均转化率：" + avgRate);

        List<PlatformRate> platList = rateService.platMap(trueDate);// 按本月份的平台上月转化率  倒序
        //platList.forEach(p -> p.setRate(p.getRate() + num));
        // platList.forEach(p -> p.setRate(p.getRate()));
        platList.forEach(i -> logger.info("本月的平台转化率 --> 平台名：" + i.getPlatformName() + ", 转化率：" + i.getRate()));
        logger.info("-----------------------------------------------");
        List<PlatformNum> platformNums = getPlatformNum(consultDate, platList); //按月份 平台已经分配的数据量   包括其他部门的，需要去掉   并按照转化率倒序
//代运营前4条
        if (totalCount <= 4) {
            for (PlatformRate platformRate : platList) {
                for (PlatformNum platformNum : platformNums) {
                    if (platformRate.getPlatformName().equals(platformNum.getPlatformName()) && platformNum.getNumber() == 0) {
                        String platName = getPlatName(informations, platformNum.getPlatformName());
                        map.put("planformId", getPlatId(informations, platformNum.getPlatformName()));
                        addOrUpdate(integer, map);
                        result.setCode(1);
                        result.setData(platName);
                        return result;
                    }
                }
            }
        } else {
            Map<String, Long> platMap = new HashMap<>(); //每个平台应该分的咨询数
            platList.forEach(platformRate ->
                    platMap.put(platformRate.getPlatformName(),
                            Math.round(Double.valueOf(df.format(totalCount * num + totalCount * (1 - num * 4) / 4 + totalCount * (1 - num * 4) * (platformRate.getRate() - avgRate) / (avgRate * 4))))));
            platMap.entrySet().forEach(next -> logger.info("本月的各平台应该分配的咨询数 --> 平台名:" + next.getKey() + ",应该分的代运营咨询数：" + next.getValue()));
            logger.info("-----------------------------------------------");

            //计算  部门应分配的和已经分配的差值 最大者
//            Map<String, Double> platCha = new HashMap<>();
            for (PlatformNum platformNum : platformNums) {
                for (Map.Entry<String, Long> next : platMap.entrySet()) {
                    if (platformNum.getPlatformName().equals(next.getKey())) {
                        logger.info("平台名:" + platformNum.getPlatformName() + ",已经分的代运营咨询数：" + platformNum.getNumber() + ",应该分的代运营咨询数：" + next.getValue());
                        logger.info("-----------------------------------------------");
//                        platCha.put(platformNum.getPlatformName(), Double.valueOf(df.format(next.getValue() - platformNum.getNumber()))); //应给分配的和已经分配的差值
                        platformNum.setShouldNumber(next.getValue());
                        platformNum.setChaZhi(next.getValue() - platformNum.getNumber()); ////应给分配的和已经分配的差值
                    }
                }
            }
//            platCha.entrySet().forEach(next -> logger.info("平台名:" + next.getKey() + ",代运营分配的差值：" + next.getValue()));
            platformNums.forEach(next -> logger.info("平台名:" + next.getPlatformName() + ",已经分的代运营咨询数：" + next.getNumber() +
                    ",应该分的代运营咨询数：" + next.getShouldNumber() + ",差值：" + next.getChaZhi() + ",转化率：" + next.getRate()));
            logger.info("-----------------------------------------------");


            //第一按照差值进行排序，第二按照转化率进行排序
            Comparator<PlatformNum> bya = Comparator.comparing(PlatformNum::getChaZhi).reversed();//按照差值倒序
            Comparator<PlatformNum> byb = Comparator.comparing(PlatformNum::getRate).reversed();//按照转化率倒序
            Collections.sort(platformNums, bya.thenComparing(byb));//将aList按照a字段先倒序再按照B字段进行倒序排列
            platformNums.forEach(next -> logger.info("按照排序规则之后的 -----> 平台名:" + next.getPlatformName() + ",已经分的代运营咨询数：" + next.getNumber() +
                    ",应该分的代运营咨询数：" + next.getShouldNumber() + ",差值：" + next.getChaZhi() + ",转化率：" + next.getRate()));

            String platName = getPlatName(informations, platformNums.get(0).getPlatformName());
            map.put("planformId", getPlatId(informations, platformNums.get(0).getPlatformName()));
            addOrUpdate(integer, map);
            result.setCode(1);
            result.setData(platName);
        }
        return result;
    }

    //各个部门已经分配的咨询数 从未分配的数量设为0（上月四大平台的转化率和咨询数）
    private List<PlatformNum> getPlatformNum(String consultDate, List<PlatformRate> platList) {
        List<PlatformNum> platformNums = dataService.platformNums(consultDate);    //按月份查出所有平台 平台已经分配的数据量   包括其他部门的，需要去掉
        List<PlatformInformation> informations = informationService.listNew(); //查出四大平台的
        List<PlatformNum> list = new ArrayList<>();

        for (PlatformInformation information : informations) {
            PlatformNum platformNum = new PlatformNum();
            platformNum.setNumber(0L);
            platformNum.setPlatId(information.getId());
            platformNum.setPlatformName(information.getPlatformName());
            list.add(platformNum);
        }
        //如果相等就把分配的数量放进去
        for (PlatformNum platformNum : list) {
            for (PlatformNum num : platformNums) {
                if (num.getPlatformName().equals(platformNum.getPlatformName())) {
                    platformNum.setNumber(num.getNumber());
                }
            }
        }
        list.forEach(i -> logger.info("本月的各平台已经分配的咨询数 --> 平台名：" + i.getPlatformName() + ", 代运营分配的咨询数：" + i.getNumber() + ", 转化率：" + i.getRate()));
        logger.info("---------==========================---------");

        list.removeIf(next -> !next.getPlatformName().contains("平台"));

        for (PlatformNum platformNum : list) {
            for (PlatformRate platformRate : platList) {
                if (platformNum.getPlatformName().equals(platformRate.getPlatformName())) {
                    platformNum.setRate(platformRate.getRate());
                }
            }
        }
        //按照转化率排序
        Collections.sort(list, new Comparator<PlatformNum>() {
            @Override
            public int compare(PlatformNum o1, PlatformNum o2) {
                return -(o1.getRate().compareTo(o2.getRate()));//按照转化率倒序
            }
        });

        list.forEach(i -> logger.info("本月的各平台已经分配的咨询数 --> 平台名：" + i.getPlatformName() + ", 代运营分配的咨询数：" + i.getNumber() + ", 转化率：" + i.getRate()));
        logger.info("-----------------------------------------------");
        return list;
    }

    //获取平台名称
    private String getPlatName(List<PlatformInformation> informations, String platName) {
        for (PlatformInformation information : informations) {
            if (platName.equals(information.getPlatformName())) {
                return information.getPlatformName();
            }
        }
        return "";
    }

    //获取平台id
    public int getPlatId(List<PlatformInformation> informations, String platName) {
        //部门信息,渠道名字
        for (PlatformInformation information : informations) {
            if (platName.equals(information.getPlatformName())) {
                return information.getId();
            }
        }
        return 0;
    }

    //添加数据库
    private void insertDB(Map<String, Object> map) {
        ConsultData consultData = new ConsultData();
        transMap2Bean(map, consultData);
        consultData.setCreateTime(new Date());
        logger.info("咨询数据 consultData:{}", consultData);
        dataService.insert(consultData);
    }

    //更新数据库
    public void updateDB(Map<String, Object> map) {
        ConsultData consultData = new ConsultData();
        transMap2Bean(map, consultData);
        consultData.setUpdateTime(new Date());
        logger.info("咨询数据 consultData:{}", consultData);
        dataService.update(consultData);
    }

    //判断是更新还是添加到数据库
    public void addOrUpdate(int integer, Map<String, Object> map) {
        //0 添加到数据库，1 更新到数据库
        if (integer == 0) {
            insertDB(map);
        } else if (integer == 1) {
            updateDB(map);
        }
    }

    //算出咨询月份上一个月的转换率日期 （例如：9月 就要 9-1  算出8月的，也就是真正的真正的日期）
    private String getTrueDate(String consultDate) {
        try {
            Date parse = sdf2.parse(consultDate);
            parse.setMonth(parse.getMonth() - 1);
            String trueDate = sdf2.format(parse);//查询转化率真正的日期
            return trueDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


}
