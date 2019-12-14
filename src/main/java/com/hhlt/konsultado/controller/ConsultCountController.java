package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.BusinessType;
import com.hhlt.konsultado.entity.Spend;
import com.hhlt.konsultado.response.ConsultCountResponse;
import com.hhlt.konsultado.response.CountPlatBusinessResponse;
import com.hhlt.konsultado.service.*;
import com.hhlt.konsultado.util.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/count")
public class ConsultCountController {

    @Autowired
    private ConsultCountService consultCountService;

    @Autowired
    private ConsultingChannelService consultingChannelService;

    @Autowired
    private SpendService spendService;

    @Autowired
    private BusinessTypeService businessTypeService;

    @Autowired
    private ConsultDataService consultDataService;


    private SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/jumpChannel")
    public String jumpChannel(Model model) {
        model.addAttribute("channel", consultingChannelService.list());
        return "ConsultCount/ConsultCount-list";
    }


    @RequestMapping("/jumpPlatformBusiness")
    public String jumpPlatformBusiness() {
        return "ConsultCount/CountPlatBusiness-list";
    }

    @RequestMapping("/jumpChannelBusiness")
    public String jumpChannelBusiness() {
        return "ConsultCount/CountChannelBusiness-list";
    }

    /**
     * 渠道业务咨询统计
     */
    @RequestMapping("/countChannelBusiness")
    @PreAuthorize("hasAuthority('jumpPlatformBusiness')")
    @ResponseBody
    public Result countChannelBusiness(String beginTime, String endTime) {
        Result result = new Result();
        List<CountPlatBusinessResponse> rsp = consultCountService.countChannelBusiness(beginTime, endTime);


        ConsultCountResponse c = consultCountService.consultCount(null, beginTime, endTime);
        Long consult = c.getBeijingBd() + c.getBeijing360() + c.getQingdaoBd() + c.getQingdao360() + c.getJinri() + c.getZyyz()
                + c.getDgpt() + c.getBjService1() + c.getZywdqd() + c.getKdfwsc() + c.getQita();

        for (int i = 0; i < rsp.size(); i++) {
            if (i == rsp.size() - 1) {
                rsp.get(i).setRatio("100%");
                break;
            }
            Double d = 0.0;
            if (consult == 0) {
                d = Double.parseDouble(String.valueOf(rsp.get(i).getTotal()));
            } else {
                d = Double.parseDouble(String.valueOf(rsp.get(i).getTotal())) / Double.parseDouble(String.valueOf(consult));
            }
            BigDecimal b = new BigDecimal(d);
            d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            rsp.get(i).setRatio(d * 100 + "%");
        }

        List<Map<String, Object>> data = rsp.stream()
                .map(this::toMap)
                .collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("name", "产品咨询占比");
        map.put("throughTrain", percentNum(Double.parseDouble(data.get(data.size() - 1).get("throughTrain").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("zz", percentNum(Double.parseDouble(data.get(data.size() - 1).get("zz").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("dOperating", percentNum(Double.parseDouble(data.get(data.size() - 1).get("dOperating").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("jdOperating", percentNum(Double.parseDouble(data.get(data.size() - 1).get("jdOperating").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("cjtj", percentNum(Double.parseDouble(data.get(data.size() - 1).get("cjtj").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("jzt", percentNum(Double.parseDouble(data.get(data.size() - 1).get("jzt").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("pdd", percentNum(Double.parseDouble(data.get(data.size() - 1).get("pdd").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("decorate", percentNum(Double.parseDouble(data.get(data.size() - 1).get("decorate").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("photography", percentNum(Double.parseDouble(data.get(data.size() - 1).get("photography").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("server", percentNum(Double.parseDouble(data.get(data.size() - 1).get("server").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("content", percentNum(Double.parseDouble(data.get(data.size() - 1).get("content").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("software", percentNum(Double.parseDouble(data.get(data.size() - 1).get("software").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("shopDiagnosis", percentNum(Double.parseDouble(data.get(data.size() - 1).get("shopDiagnosis").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("training", percentNum(Double.parseDouble(data.get(data.size() - 1).get("training").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("drz", percentNum(Double.parseDouble(data.get(data.size() - 1).get("drz").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("other", percentNum(Double.parseDouble(data.get(data.size() - 1).get("other").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        map.put("total", percentNum(Double.parseDouble(data.get(data.size() - 1).get("total").toString()), Double.parseDouble(data.get(data.size() - 1).get("total").toString())));
        data.add(map);
        result.setCode(0);
        result.setData(data);
        return result;
    }

    private String percentNum(Double num, Double num1) {
        Double d = 0.0;
        if (num1 == 0) {
            d = num;
        } else {
            d = num / num1;
        }
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d * 100 + "%";
    }

    private Map<String, Object> toMap(CountPlatBusinessResponse rsp) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", rsp.getName());
        map.put("throughTrain", rsp.getThroughTrain());
        map.put("zz", rsp.getZz());
        map.put("dOperating", rsp.getdOperating());
        map.put("jdOperating", rsp.getJdOperating());
        map.put("cjtj", rsp.getCjtj());
        map.put("jzt", rsp.getJzt());
        map.put("pdd", rsp.getPdd());
        map.put("decorate", rsp.getDecorate());
        map.put("photography", rsp.getPhotography());
        map.put("server", rsp.getServer());
        map.put("content", rsp.getContent());
        map.put("software", rsp.getSoftware());
        map.put("shopDiagnosis", rsp.getShopDiagnosis());
        map.put("training", rsp.getTraining());
        map.put("drz", rsp.getDrz());
        map.put("other", rsp.getOther());
        map.put("total", rsp.getTotal());
        map.put("ratio", rsp.getRatio());
        return map;
    }

    /**
     * 平台业务咨询统计
     */
    @RequestMapping("/countPlatformBusiness")
    @PreAuthorize("hasAuthority('countChannelBusiness')")
    @ResponseBody
    public Result countPlatformBusiness(String beginTime, String endTime) {
        Result result = new Result();
        List<CountPlatBusinessResponse> countPlatBusinessResponse = consultCountService.countPlatformBusiness(beginTime, endTime);
        result.setCode(0);
        result.setData(countPlatBusinessResponse);
        return result;
    }

    @RequestMapping("/exportPlatformBusiness")
    public void exportPlatformBusiness(HttpServletResponse response, String beginTime, String endTime, String lx) {
        List<CountPlatBusinessResponse> list = null;
        if ("channel".equals(lx)) {
            list = consultCountService.countChannelBusiness(beginTime, endTime);
        }
        if ("plat".equals(lx)) {
            list = consultCountService.countPlatformBusiness(beginTime, endTime);
        }
        XSSFWorkbook hssfworkbook = new XSSFWorkbook();
        XSSFFont font = hssfworkbook.createFont();//创建字体样式
        font.setFontName("宋体");//使用宋体
        font.setFontHeightInPoints((short) 11);//字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        XSSFCellStyle style1 = hssfworkbook.createCellStyle();// 创建单元格样式style
        style1.setFont(font);//将字体注入
        style1.setWrapText(true);// 自动换行
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style1.setFillForegroundColor(IndexedColors.WHITE.index);// 设置单元格的背景颜色
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style1.setBorderTop((short) 1);// 边框的大小
        style1.setBorderBottom((short) 1);
        style1.setBorderLeft((short) 1);
        style1.setBorderRight((short) 1);

        XSSFFont font1 = hssfworkbook.createFont();//创建字体样式
        font1.setFontName("宋体");//使用宋体
        font1.setFontHeightInPoints((short) 11);//字体大小
        XSSFCellStyle style = hssfworkbook.createCellStyle();// 创建单元格样式style
        style.setFont(font1);//将字体注入
        style.setWrapText(true);// 自动换行
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setFillForegroundColor(IndexedColors.WHITE.index);// 设置单元格的背景颜色
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderTop((short) 1);// 边框的大小
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);


        XSSFSheet sheet = hssfworkbook.createSheet("全网选词");
        sheet.setColumnWidth(0, 0);
        sheet.setColumnWidth(1, 13 * 256);
        sheet.setColumnWidth(2, 13 * 256);
        sheet.setColumnWidth(3, 23 * 256);//4、生成sheet中一行，从0开始
        sheet.setColumnWidth(4, 13 * 256);
        sheet.setColumnWidth(5, 13 * 256);
        sheet.setColumnWidth(6, 13 * 256);
        sheet.setColumnWidth(7, 13 * 256);

        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
        XSSFCell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式

        cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
        cell.setCellValue("平台名称");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式

        cell = row.createCell(2);
        cell.setCellValue("直通车");
        cell.setCellStyle(style1);

        cell = row.createCell(3);
        cell.setCellValue("钻展");
        cell.setCellStyle(style1);

        cell = row.createCell(4);
        cell.setCellValue("代运营");
        cell.setCellStyle(style1);

        cell = row.createCell(5);
        cell.setCellValue("京东代运营");
        cell.setCellStyle(style1);

        cell = row.createCell(6);
        cell.setCellValue("超级推荐");
        cell.setCellStyle(style1);

        cell = row.createCell(7);
        cell.setCellValue("京准通");
        cell.setCellStyle(style1);


        cell = row.createCell(8);
        cell.setCellValue("拼多多");
        cell.setCellStyle(style1);

        cell = row.createCell(9);
        cell.setCellValue("装修");
        cell.setCellStyle(style1);

        cell = row.createCell(10);
        cell.setCellValue("摄影");
        cell.setCellStyle(style1);

        cell = row.createCell(11);
        cell.setCellValue("客服");
        cell.setCellStyle(style1);

        cell = row.createCell(12);
        cell.setCellValue("内容");
        cell.setCellStyle(style1);

        cell = row.createCell(13);
        cell.setCellValue("软件");
        cell.setCellStyle(style1);

        cell = row.createCell(14);
        cell.setCellValue("店铺诊断");
        cell.setCellStyle(style1);

        cell = row.createCell(15);
        cell.setCellValue("培训");
        cell.setCellStyle(style1);

        cell = row.createCell(16);
        cell.setCellValue("代入驻");
        cell.setCellStyle(style1);

        cell = row.createCell(17);
        cell.setCellValue("其他");
        cell.setCellStyle(style1);

        cell = row.createCell(18);
        cell.setCellValue("合计");
        cell.setCellStyle(style1);

        for (int i = 1; i <= list.size(); i++) {
            row = sheet.createRow(i);
            row.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
            cell = row.createCell(i);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式

            cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
            cell.setCellValue(list.get(i - 1).getName());//设置单元格中内容
            cell.setCellStyle(style);//设置单元格样式

            cell = row.createCell(2);
            cell.setCellValue(list.get(i - 1).getThroughTrain());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(list.get(i - 1).getZz());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(list.get(i - 1).getdOperating());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(list.get(i - 1).getJdOperating());
            cell.setCellStyle(style);


            cell = row.createCell(6);
            cell.setCellValue(list.get(i - 1).getCjtj());
            cell.setCellStyle(style);


            cell = row.createCell(7);
            cell.setCellValue(list.get(i - 1).getJzt());
            cell.setCellStyle(style);


            cell = row.createCell(8);
            cell.setCellValue(list.get(i - 1).getPdd());
            cell.setCellStyle(style);

            cell = row.createCell(9);
            cell.setCellValue(list.get(i - 1).getDecorate());
            cell.setCellStyle(style);

            cell = row.createCell(10);
            cell.setCellValue(list.get(i - 1).getPhotography());
            cell.setCellStyle(style);

            cell = row.createCell(11);
            cell.setCellValue(list.get(i - 1).getServer());
            cell.setCellStyle(style);

            cell = row.createCell(12);
            cell.setCellValue(list.get(i - 1).getContent());
            cell.setCellStyle(style);


            cell = row.createCell(13);
            cell.setCellValue(list.get(i - 1).getSoftware());
            cell.setCellStyle(style);

            cell = row.createCell(14);
            cell.setCellValue(list.get(i - 1).getShopDiagnosis());
            cell.setCellStyle(style);

            cell = row.createCell(15);
            cell.setCellValue(list.get(i - 1).getTraining());
            cell.setCellStyle(style);

            cell = row.createCell(16);
            cell.setCellValue(list.get(i - 1).getDrz());
            cell.setCellStyle(style);

            cell = row.createCell(17);
            cell.setCellValue(list.get(i - 1).getOther());
            cell.setCellStyle(style);

            cell = row.createCell(18);
            cell.setCellValue(list.get(i - 1).getTotal());
            cell.setCellStyle(style);

        }


        //响应到客户端
        try {
            if ("channel".equals(lx)) {
                this.setResponseHeader(response, "渠道业务咨询统计.xlsx");
            }
            if ("plat".equals(lx)) {
                this.setResponseHeader(response, "平台业务咨询统计.xlsx");
            }

            OutputStream os = response.getOutputStream();
            hssfworkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/costdatashow")
    public void costdatashow(HttpServletResponse response, String beginTime, String endTime, String businessType1, String lx, String channel) {
        List<Spend> list = null;
        HashMap map = new HashMap();
        map.put("beginTime", beginTime);
        map.put("typeId", businessType1);
        map.put("channelId", channel);
        map.put("endTime",endTime);
//        map.put("pageSize",null);
        list = spendService.selectSpendListExcel(map);


        List list2 = new ArrayList();

        for (Spend s : list) {
            double v = s.getCharge().doubleValue();
            Date date = s.getDate();
            String format = dayFmt.format(date);
            int i1 = DateUtil.caculateTotalTime(beginTime.toString(), endTime.toString());
            for (int i = 0; i < i1 + 1; i++) {
                String time = DateUtil.addDate(beginTime.toString(), i);
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("time", time);
                if (format.equals(time)) {
                    //List<BusinessType> list = businessTypeService.list();
                    BusinessType businessType11 = businessTypeService.get(s.getBusinessTypeId().toString());
                    // for (BusinessType businessType : list) {
                    map2.put("typeId", businessType11.getId());
                    map2.put("channelId", s.getcID());
                    Integer integer = consultDataService.countS(map2);//每天的咨询量
                    list2.add(integer);
                    if (integer == 0) {
                        s.setChengben(v);
                        s.setZixun(0);
                    } else {
                        double v1 = v / integer;
                        DecimalFormat df = new DecimalFormat("#.00");
                        String str = df.format(v1);
                        s.setChengben(Double.parseDouble(str));
                        s.setZixun(integer);
                    }
                    Integer integerNull = consultDataService.countA(map2);//无效咨询量
                    if (integerNull == 0) {
                        s.setWuZiXun(0);
                    }else {
                        s.setWuZiXun(integerNull);
                    }
                    Integer youxiaoZiXun = consultDataService.countYouXiao(map2);//有效咨询量
                    if (youxiaoZiXun == 0) {
                        s.setYouZiXun(0);
                        s.setYouXiaochengben(v);
                    } else {
                        double v11 = v / youxiaoZiXun;
                        DecimalFormat df = new DecimalFormat("#.00");
                        String YouxiaoCb = df.format(v11);
                        s.setYouXiaochengben(Double.parseDouble(YouxiaoCb));//有效咨询量成本
                        s.setYouZiXun(youxiaoZiXun);
                    }
                }
            }
        }


        XSSFWorkbook hssfworkbook = new XSSFWorkbook();
        XSSFFont font = hssfworkbook.createFont();//创建字体样式
        font.setFontName("宋体");//使用宋体
        font.setFontHeightInPoints((short) 11);//字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        XSSFCellStyle style1 = hssfworkbook.createCellStyle();// 创建单元格样式style
        style1.setFont(font);//将字体注入
        style1.setWrapText(true);// 自动换行
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style1.setFillForegroundColor(IndexedColors.WHITE.index);// 设置单元格的背景颜色
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style1.setBorderTop((short) 1);// 边框的大小
        style1.setBorderBottom((short) 1);
        style1.setBorderLeft((short) 1);
        style1.setBorderRight((short) 1);

        XSSFFont font1 = hssfworkbook.createFont();//创建字体样式
        font1.setFontName("宋体");//使用宋体
        font1.setFontHeightInPoints((short) 11);//字体大小
        XSSFCellStyle style = hssfworkbook.createCellStyle();// 创建单元格样式style
        style.setFont(font1);//将字体注入
        style.setWrapText(true);// 自动换行
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setFillForegroundColor(IndexedColors.WHITE.index);// 设置单元格的背景颜色
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderTop((short) 1);// 边框的大小
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);


        XSSFSheet sheet = hssfworkbook.createSheet("全网选词");
        sheet.setColumnWidth(0, 0);
        sheet.setColumnWidth(1, 13 * 256);
        sheet.setColumnWidth(2, 13 * 256);
        sheet.setColumnWidth(3, 23 * 256);//4、生成sheet中一行，从0开始
        sheet.setColumnWidth(4, 13 * 256);
        sheet.setColumnWidth(5, 13 * 256);
        sheet.setColumnWidth(6, 13 * 256);
        sheet.setColumnWidth(7, 13 * 256);

        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
        XSSFCell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式

        cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
        cell.setCellValue("日期");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式

        cell = row.createCell(2);
        cell.setCellValue("渠道");
        cell.setCellStyle(style1);

        cell = row.createCell(3);
        cell.setCellValue("业务类型");
        cell.setCellStyle(style1);

        cell = row.createCell(4);
        cell.setCellValue("展现数量");
        cell.setCellStyle(style1);

        cell = row.createCell(5);
        cell.setCellValue("点击量");
        cell.setCellStyle(style1);

        cell = row.createCell(6);
        cell.setCellValue("当日消耗");
        cell.setCellStyle(style1);

        cell = row.createCell(7);
        cell.setCellValue("咨询量");
        cell.setCellStyle(style1);


        cell = row.createCell(8);
        cell.setCellValue("成本");
        cell.setCellStyle(style1);

        cell = row.createCell(9);
        cell.setCellValue("无效咨询量");
        cell.setCellStyle(style1);

        cell = row.createCell(10);
        cell.setCellValue("有效咨询量");
        cell.setCellStyle(style1);

        cell = row.createCell(11);
        cell.setCellValue("有效咨询量成本");
        cell.setCellStyle(style1);

        //  XSSFRow row1 = sheet.createRow(0);
        //        row1.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
        //        XSSFCell cell = row1.createCell(0);
        //        cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
        //        cell.setCellValue("合计");//设置单元格中内容
        //        cell.setCellStyle(style1);//设置单元格样式
        //        List<SonSpend> spendList = spendService.selectSpendGroupBy(map);
        //        for (int i = 0; i < spendList.size(); i++) {
        //            sumadPv += spendList.get(i).getAdpv().intValue();
        //            sumclick += spendList.get(i).getClick().intValue();
        //            sumcharge += spendList.get(i).getCharge().intValue();
        //            sumVisitor += spendList.get(i).getVisitor().intValue();
        //            sumPageviews += spendList.get(i).getPageviews().intValue();
        //            sumYue += spendList.get(i).getYue().doubleValue();
        //        }

        for (int i = 1; i <= list.size(); i++) {
            row = sheet.createRow(i);
            row.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
            cell = row.createCell(i);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式

            cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
            try {
                cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(list.get(i - 1).getDate()));//设置单元格中内容
            } catch (Exception e) {
                e.printStackTrace();
            }
            cell.setCellStyle(style);//设置单元格样式

            cell = row.createCell(2);
            cell.setCellValue(list.get(i - 1).getChannelId());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(list.get(i - 1).getBusinessTypeId());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(list.get(i - 1).getAdpv().toString());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(list.get(i - 1).getClick().toString());
            cell.setCellStyle(style);


            cell = row.createCell(6);
            cell.setCellValue(list.get(i - 1).getCharge().toString());
            cell.setCellStyle(style);


            cell = row.createCell(7);
            cell.setCellValue(list.get(i - 1).getZixun());
            cell.setCellStyle(style);

            Double chengben = list.get(i - 1).getChengben();
            if(chengben == null){
                cell = row.createCell(8);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }else{
                cell = row.createCell(8);
                cell.setCellValue(chengben);
                cell.setCellStyle(style);
            }



            cell = row.createCell(9);
            cell.setCellValue(list.get(i - 1).getWuZiXun());
            cell.setCellStyle(style);


            cell = row.createCell(10);
            cell.setCellValue(list.get(i - 1).getYouZiXun());
            cell.setCellStyle(style);


            Double YouXiaoChengben = list.get(i - 1).getYouXiaochengben();
            if (YouXiaoChengben == null){
                cell = row.createCell(11);
                cell.setCellValue("");
                cell.setCellStyle(style);
            }else {
                cell = row.createCell(11);
                cell.setCellValue(list.get(i - 1).getYouXiaochengben());
                cell.setCellStyle(style);
            }

        }


        //响应到客户端
        try {
            if ("channel".equals(lx)) {
                this.setResponseHeader(response, "花费列表统计.xlsx");
            }
            OutputStream os = response.getOutputStream();
            hssfworkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 咨询渠道 数据统计
     *
     * @param
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping("/consultCount")
    @PreAuthorize("hasAuthority('consultCount:query')")
    @ResponseBody
    public Result consultCount(String channel, String beginTime, String endTime) {
        Result result = new Result();
        ConsultCountResponse c = consultCountService.consultCount(channel, beginTime, endTime);
        result.setCode(0);
        c.setTotal(c.getAmp()+c.getBeijingBd() + c.getBeijing360() + c.getQingdaoBd() + c.getQingdao360() + c.getJinri() + c.getZyyz() + c.getDgpt()
                + c.getBjService1() + c.getZywdqd() + +c.getKdfwsc() + c.getQita());
        List<ConsultCountResponse> consultCountResponses = Lists.newArrayList();
        consultCountResponses.add(c);
        result.setData(consultCountResponses);
        return result;
    }


    @RequestMapping("/exportChannel")
    public void exportChannel(HttpServletResponse response, String channel, String beginTime, String endTime) {
        ConsultCountResponse c = consultCountService.consultCount(channel, beginTime, endTime);
        c.setTotal(c.getAmp()+c.getBeijingBd() + c.getBeijing360() + c.getQingdaoBd() + c.getQingdao360() + c.getJinri() + c.getZyyz()
                + c.getDgpt() + c.getBjService1() + c.getZywdqd() + c.getKdfwsc() + c.getQita());
        List<ConsultCountResponse> list = Lists.newArrayList();
        list.add(c);

        XSSFWorkbook hssfworkbook = new XSSFWorkbook();
        XSSFFont font = hssfworkbook.createFont();//创建字体样式
        font.setFontName("宋体");//使用宋体
        font.setFontHeightInPoints((short) 11);//字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        XSSFCellStyle style1 = hssfworkbook.createCellStyle();// 创建单元格样式style
        style1.setFont(font);//将字体注入
        style1.setWrapText(true);// 自动换行
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style1.setFillForegroundColor(IndexedColors.WHITE.index);// 设置单元格的背景颜色
        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style1.setBorderTop((short) 1);// 边框的大小
        style1.setBorderBottom((short) 1);
        style1.setBorderLeft((short) 1);
        style1.setBorderRight((short) 1);

        XSSFFont font1 = hssfworkbook.createFont();//创建字体样式
        font1.setFontName("宋体");//使用宋体
        font1.setFontHeightInPoints((short) 11);//字体大小
        XSSFCellStyle style = hssfworkbook.createCellStyle();// 创建单元格样式style
        style.setFont(font1);//将字体注入
        style.setWrapText(true);// 自动换行
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setFillForegroundColor(IndexedColors.WHITE.index);// 设置单元格的背景颜色
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderTop((short) 1);// 边框的大小
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);


        XSSFSheet sheet = hssfworkbook.createSheet("全网选词");
        sheet.setColumnWidth(0, 0);
        sheet.setColumnWidth(1, 13 * 256);
        sheet.setColumnWidth(2, 13 * 256);
        sheet.setColumnWidth(3, 23 * 256);//4、生成sheet中一行，从0开始
        sheet.setColumnWidth(4, 13 * 256);
        sheet.setColumnWidth(5, 13 * 256);
        sheet.setColumnWidth(6, 13 * 256);
        sheet.setColumnWidth(7, 13 * 256);

        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
        XSSFCell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式

        cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
        cell.setCellValue("渠道");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式

        cell = row.createCell(2);
        cell.setCellValue("官网");
        cell.setCellStyle(style1);

        cell = row.createCell(3);
        cell.setCellValue("北京官网(360)");
        cell.setCellStyle(style1);

        cell = row.createCell(4);
        cell.setCellValue("青岛官网");
        cell.setCellStyle(style1);

        cell = row.createCell(5);
        cell.setCellValue("青岛官网(360)");
        cell.setCellStyle(style1);

        cell = row.createCell(6);
        cell.setCellValue("今日头条");
        cell.setCellStyle(style1);


        cell = row.createCell(7);
        cell.setCellValue("自营网店瓣装女装");
        cell.setCellStyle(style1);

        cell = row.createCell(8);
        cell.setCellValue("订购平台");
        cell.setCellStyle(style1);


        cell = row.createCell(9);
        cell.setCellValue("洪海服务市场");
        cell.setCellStyle(style1);


//        cell = row.createCell(10);
//        cell.setCellValue("京麦服务市场");
//        cell.setCellStyle(style1);

        cell = row.createCell(10);
        cell.setCellValue("自营网店洪海青岛");
        cell.setCellStyle(style1);

//        cell = row.createCell(12);
//        cell.setCellValue("思路网");
//        cell.setCellStyle(style1);
//
//        cell = row.createCell(13);
//        cell.setCellValue("京标标");
//        cell.setCellStyle(style1);

        cell = row.createCell(11);
        cell.setCellValue("科大服务市场");
        cell.setCellStyle(style1);

        cell = row.createCell(12);
        cell.setCellValue("AMP营销拍档");
        cell.setCellStyle(style1);

        cell = row.createCell(13);
        cell.setCellValue("其他");
        cell.setCellStyle(style1);

        cell = row.createCell(14);
        cell.setCellValue("合计");
        cell.setCellStyle(style1);

        for (int i = 1; i <= list.size(); i++) {
            row = sheet.createRow(i);
            row.setHeight((short) 300);// 设定行的高度//5、创建row中的单元格，从0开始
            cell = row.createCell(i);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式

            cell = row.createCell(1);//从第1个单元格开始，设置每个单元格样式
            cell.setCellValue(list.get(i - 1).getQudao());//设置单元格中内容
            cell.setCellStyle(style);//设置单元格样式

            cell = row.createCell(2);
            cell.setCellValue(list.get(i - 1).getBeijingBd());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(list.get(i - 1).getBeijing360());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(list.get(i - 1).getQingdaoBd());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(list.get(i - 1).getQingdao360());
            cell.setCellStyle(style);


            cell = row.createCell(6);
            cell.setCellValue(list.get(i - 1).getJinri());
            cell.setCellStyle(style);


            cell = row.createCell(7);
            cell.setCellValue(list.get(i - 1).getZyyz());
            cell.setCellStyle(style);

            cell = row.createCell(8);
            cell.setCellValue(list.get(i - 1).getDgpt());
            cell.setCellStyle(style);

            cell = row.createCell(9);
            cell.setCellValue(list.get(i - 1).getBjService1());
            cell.setCellStyle(style);


//            cell = row.createCell(10);
//            cell.setCellValue(list.get(i-1).getJmService1());
//            cell.setCellStyle(style);

            cell = row.createCell(10);
            cell.setCellValue(list.get(i - 1).getZywdqd());
            cell.setCellStyle(style);

//            cell = row.createCell(12);
//            cell.setCellValue(list.get(i-1).getThinkingNet());
//            cell.setCellStyle(style);
//
//            cell = row.createCell(13);
//            cell.setCellValue(list.get(i-1).getJbb());
//            cell.setCellStyle(style);

            cell = row.createCell(11);
            cell.setCellValue(list.get(i - 1).getKdfwsc());
            cell.setCellStyle(style);

            cell = row.createCell(12);
            cell.setCellValue(list.get(i - 1).getAmp());
            cell.setCellStyle(style);

            cell = row.createCell(13);
            cell.setCellValue(list.get(i - 1).getQita());
            cell.setCellStyle(style);

            cell = row.createCell(14);
            cell.setCellValue(list.get(i - 1).getTotal());
            cell.setCellStyle(style);

        }


        //响应到客户端
        try {
            this.setResponseHeader(response, "咨询渠道数据统计.xlsx");
            OutputStream os = response.getOutputStream();
            hssfworkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
