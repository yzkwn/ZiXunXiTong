package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.ConsultData;
import com.hhlt.konsultado.mapper.ConsultDataMapper;
import com.hhlt.konsultado.response.ConsultDataResponse;
import com.hhlt.konsultado.response.PlatformNum;
import com.hhlt.konsultado.service.ConsultDataService;
import com.hhlt.konsultado.util.ExcelUtil;
import com.hhlt.konsultado.util.MkdirUtil;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class ConsultDataServiceImpl implements ConsultDataService {

    private static final Logger logger = LoggerFactory.getLogger(ConsultDataServiceImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


    @Autowired
    private ConsultDataMapper dataMapper;

    @Value("${exportFile}")
    private String exportFile;

    @Value("${exportFileLinux}")
    private String exportFileLinux;

    @Override
    public void insert(ConsultData consultData) {
        dataMapper.insertSelective(consultData);
    }

    @Override
    public List<ConsultDataResponse> list(Map<String, Object> map) {
        return dataMapper.list(map);
    }

    @Override
    public Integer count(Map<String, Object> map) {
        return dataMapper.count(map);
    }

    @Override
    public int monthDataCount(String monthStr) {
        return dataMapper.monthDataCount(monthStr);
    }

    @Override
    public List<PlatformNum> platformNums(String consultDate) {
        return dataMapper.platformNums(consultDate);
    }

    @Override
    public String exportFile(Map<String, Object> map) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName = "咨询数据明细_" + System.nanoTime();
        HSSFSheet sheet = workbook.createSheet(sheetName);//工作表
        sheet.setDefaultColumnWidth(20);

        HSSFCell cell = null; //单元格
        HSSFRow titleRow = sheet.createRow(0); //行
        HSSFCellStyle columnTopStyle = ExcelUtil.getColumnTopStyle(workbook);//获取列头样式对象
        HSSFCellStyle style = ExcelUtil.getStyle(workbook); //设置单元格的样式

        String[] titles = {"咨询日期", "业务员","所属部门","客户姓名", "业务类型", "资讯渠道", "联系方式", "所在省市","市", "旺旺ID",
              "QQ",  "备注","接待人员", "客户等级", "电话咨询", "二次咨询" ,"产品类目", "微信","店铺名称", "店铺链接" };  //   ,
        //把已经写好的标题行写入excel文件中
        for (int i = 0; i < titles.length; i++) {
            cell = titleRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(columnTopStyle);
        }

        //数据写入到excel中
        HSSFRow row = null;
        List<ConsultDataResponse> list = dataMapper.excelList(map);
        for (int i = 0; i < list.size(); i++) {
            ConsultDataResponse consultDataResponse = list.get(i);
            row = sheet.createRow(i + 1);

            HSSFCell cell0 = row.createCell(0);
            String date = sdf.format(consultDataResponse.getConsultDate());
            cell0.setCellValue(date); //咨询日期
            cell0.setCellStyle(style);

            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(consultDataResponse.getSalesman()); //业务员
            cell1.setCellStyle(style);

            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(consultDataResponse.getPlatName()); //	所属部门
            cell2.setCellStyle(style);

            HSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(consultDataResponse.getName()); //客户姓名
            cell3.setCellStyle(style);

            HSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(consultDataResponse.getBusinessType()); //业务类型
            cell4.setCellStyle(style);

            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(consultDataResponse.getChannel()); //资讯渠道
            cell5.setCellStyle(style);

            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(consultDataResponse.getTelephone()); //联系方式
            cell6.setCellStyle(style);

            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(consultDataResponse.getProvince()); //所在省
            cell7.setCellStyle(style);

            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(consultDataResponse.getCity()); //所在市
            cell8.setCellStyle(style);

            HSSFCell cell9 = row.createCell(9);
            cell9.setCellValue(consultDataResponse.getWangwang()); //商家旺旺ID
            cell9.setCellStyle(style);

            HSSFCell cell10 = row.createCell(10);
            cell10.setCellValue(consultDataResponse.getQq()); //qq
            cell10.setCellStyle(style);

            HSSFCell cell11 = row.createCell(11);
            sheet.setColumnWidth(11, 60 * 256);  //设置列宽，20个字符宽
            cell11.setCellValue(consultDataResponse.getRemarkBeiZhu()); //备注
            cell11.setCellStyle(style);

            HSSFCell cell12 = row.createCell(12);
            cell12.setCellValue(consultDataResponse.getCurrentServicer()); //当前客服
            cell12.setCellStyle(style);

            HSSFCell cell13 = row.createCell(13);
            cell13.setCellValue(consultDataResponse.getLevelName()); //客户等级
            cell13.setCellStyle(style);

            HSSFCell cell14 = row.createCell(14);
            cell14.setCellValue(consultDataResponse.getIsTelephone() == false ? "" : "是"); //是否电话
            cell14.setCellStyle(style);

            HSSFCell cell15 = row.createCell(15);
            cell15.setCellValue(consultDataResponse.getIsTwice() == false ? "" : "是"); //是否二次
            cell15.setCellStyle(style);

            HSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(consultDataResponse.getRemark()); //产品类目
            cell16.setCellStyle(style);

            HSSFCell cell17 = row.createCell(17);
            cell17.setCellValue(consultDataResponse.getWeixin()); //微信
            cell17.setCellStyle(style);

            HSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(consultDataResponse.getStoreName()); //店铺名称
            cell18.setCellStyle(style);

            HSSFCell cell19 = row.createCell(19);
            cell19.setCellValue(consultDataResponse.getStoreUrl()); //店铺链接
            cell19.setCellStyle(style);
        }

//        String p = exportFile;
        String p = exportFileLinux;
        MkdirUtil.mkdir(p, sheetName, workbook,"xlsx");
        return p + File.separator + sheetName;
    }

    @Override
    public Integer deleteOne(String id) {
        return dataMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public ConsultDataResponse get(String id) {
        return dataMapper.get(Integer.valueOf(id));
    }

    @Override
    public void update(ConsultData consultData) {
        logger.info("更新数据库");
        dataMapper.updateByPrimaryKeySelective(consultData);
    }

    @Override
    public ConsultData telephone(String telephone, String qq,String weixin,String wangwang) {
        return dataMapper.telephone(telephone,qq,weixin,wangwang);
    }

    @Override
    public Integer countS(Map<String, Object> map) {
        return dataMapper.countS(map);
    }

    @Override
    public Integer countA(Map<String, Object> map) {
        return dataMapper.countA(map);
    }

    @Override
    public Integer countYouXiao(Map<String, Object> map) {
        return dataMapper.countYouXiao(map);
    }

}
