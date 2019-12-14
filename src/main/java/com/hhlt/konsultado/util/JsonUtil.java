package com.hhlt.konsultado.util;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONWriter;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * json转换工具类
 *
 * @author yyb
 * @create 2019/6/10
 */
@Slf4j
public class JsonUtil {
    /**
     * object转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        JSONWriter writer = new JSONWriter();
        writer.writeObject(obj);
        return writer.toString();
    }

    /**
     * map转换成json字符串
     *
     * @param map
     * @return
     */
    public static String mapToString(Map map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(map);
    }

    /**
     * Json字符串转换成object
     *
     * @param text
     * @return
     */
    public static <T> T parse(String text) {
        JSONParser parser = new JSONParser(text);
        return (T) parser.parse();
    }
    /**
     * List<T> 转 json 保存到数据库
     */
    public static <T> String listToJson(List<T> ts) {
        String jsons = JSON.toJSONString(ts);
        return jsons;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts =  JSONArray.parseArray(jsonString, clazz);
        return ts;
    }

    /**
     * JSON字符串转换
     *
     * @param jsonStr
     * @return
     */
    public static String replaceJsonStr(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return "";
        }
        return jsonStr.replaceAll("amp;", "").replaceAll("&quot;", "\"").replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
    }


    public static void main(String[] args) throws UnsupportedEncodingException {

        String jsonStr = "{\"recId\":633760455,\"visitorId\":\"ff318a4dc8404f9da20db61e5e1ed50a\",\"visitorName\":\"北京北京|合肥官网\",\"curEnterTime\":\"2019-08-30 13:31:29\",\"curStayTime\":65,\"sourceIp\":\"36.110.199.135\",\"sourceProvince\":\"北京北京\",\"sourceIpInfo\":\"电信\",\"requestType\":\"rt_v\",\"endType\":\"et_v_e\",\"diaStartTime\":\"2019-08-30 13:31:30\",\"diaEndTime\":\"2019-08-30 13:32:34\",\"terminalType\":\"tt_pc\",\"visitorSendNum\":0,\"csSendNum\":1,\"sourceUrl\":\"http://www.baidu.com/s?wd=87L1\",\"sourceType\":\"搜索引擎\",\"searchEngine\":\"Baidu\",\"keyword\":\"87L1\",\"firstCsId\":\"李红艳[谦谦,李红艳]\",\"joinCsIds\":\"李红艳[谦谦,李红艳]\",\"dialogType\":\"dt_v_nm\",\"firstVisitTime\":\"2019-08-30 13:31:29\",\"preVisitTime\":\"\",\"totalVisitTime\":1,\"diaPage\":\"http://tp.honghailt.cn/cn/index.php?plan=pinpaijihua&unit=pinpaici&keyword=honghailongteng&e_creative=31693384556&e_keywordid=118270297009&e_keywordid2=118270297009\",\"curFirstViewPage\":\"http://tp.honghailt.cn/cn/index.php?plan=pinpaijihua&unit=pinpaici&keyword=honghailongteng&e_creative=31693384556&e_keywordid=118270297009&e_keywordid2=118270297009\",\"curVisitorPages\":1,\"preVisitPages\":\"\",\"operatingSystem\":\"Windows 7\",\"browser\":\"Firefox 43.0\",\"info\":\"\",\"siteName\":\"官网\",\"siteId\":116538,\"dialogs\":[{\"addTime\":\"2019-08-30 13:31:31\",\"dialogId\":\"\",\"id\":16916602863,\"recContent\":\"您好，亲，我们这边是专业的电商运营团队，请问有什么可以帮助您的呢？\",\"recId\":633760455,\"recType\":2,\"sender\":\"谦谦\",\"subType\":\"\"},{\"addTime\":\"2019-08-30 13:31:30\",\"dialogId\":\"\",\"id\":16916602861,\"recContent\":\" 谦谦 正在为您服务\",\"recId\":633760455,\"recType\":3,\"sender\":\"\",\"subType\":\"\"},{\"addTime\":\"2019-08-30 13:31:29\",\"dialogId\":\"\",\"id\":16916602834,\"recContent\":\"2019-08-30 13:31:29 从[搜索引擎,搜索:87L1] <a href=http://www.baidu.com/s?wd=87L1 title=http://www.baidu.com/s?wd=87L1 target=_blank>www.baidu.com<\\/a> 进入 <a _kskw=false _kspl=\\\"\\\" href=http://tp.honghailt.cn/cn/index.php?plan=pinpaijihua&unit=pinpaici&keyword=honghailongteng&e_creative=31693384556&e_keywordid=118270297009&e_keywordid2=118270297009 target=_blank title=\\\"http://tp.honghailt.cn/cn/index.php?plan=pinpaijihua&unit=pinpaici&keyword=honghailongteng&e_creative=31693384556&e_keywordid=118270297009&e_keywordid2=118270297009\\\">/cn/index.php<\\/a> \",\"recId\":633760455,\"recType\":4,\"sender\":\"\",\"subType\":\"\"}]}";

        Map<String,Object> objectMap = parse(jsonStr);

       /* List<Map> list = jsonToList(jsonStr,Map.class );*/
        System.out.println();
    }




}
