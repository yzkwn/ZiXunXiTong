package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.DealData;
import com.hhlt.konsultado.mapper.DealDataMapper;
import com.hhlt.konsultado.service.ImportExcelService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportExcelServiceImpl implements ImportExcelService {

    @Autowired
    private DealDataMapper dealMapper;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(ImportExcelServiceImpl.class);

    @Override
    public void importDealData(String path) throws IOException, ParseException {
        InputStream is = new FileInputStream(path);
        // HSSFWorkbook 标识整个excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        int size = hssfWorkbook.getNumberOfSheets(); //文件的条数

        for (int numSheet = 0; numSheet < size; numSheet++) {
            // HSSFSheet 标识某一页
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                break;
            }
            // 处理当前页，循环读取每一行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                // HSSFRow表示行
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int minColIx = hssfRow.getFirstCellNum();
                int maxColIx = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                // 遍历改行，获取处理每个cell元素
                DealData product = new DealData();
                for (int colIx = minColIx; colIx <= maxColIx; colIx++) {
                    // HSSFCell 表示单元格
                    HSSFCell cell = hssfRow.getCell(colIx);
                    if (cell == null) {
                        rowList.add("");
                    } else {
                        rowList.add(getStringVal(cell)); //改造poi默认的toString（）方法如下
                    }
                }
                if ("".equals(rowList.get(0))) {
                    return;
                }
                product.setSaleTeam(rowList.get(0));  //销售团队
                String date = rowList.get(2);
                logger.info("签约日期：{}", date);
                Date date1 = sdf.parse(rowList.get(2));
                product.setSignDate(date1); //签约日期
                product.setSaleDeputy(rowList.get(3)); //销售代表
                product.setDealProject(rowList.get(4)); //成交项目
                product.setContact(rowList.get(5)); //联系人

                product.setProvince(rowList.get(6)); //省份
                product.setCity(rowList.get(7)); //城市
                product.setTelephone(rowList.get(8)); //联系电话
                product.setDealMoney(new BigDecimal(rowList.get(9))); //成交金额
                product.setPaymentCycle(Integer.valueOf(rowList.get(10))); //缴费周期

                product.setStoreCategory(rowList.get(11)); //店铺类目
                product.setStoreLevel(rowList.get(12)); //店铺等级
                product.setChannel(rowList.get(13)); //渠道
                product.setWangwangId(rowList.get(14)); //旺旺id
                product.setStoreUrl(rowList.get(15)); //店铺链接

                product.setBeizhu(rowList.get(16));//备注
                product.setArrivalStatus(rowList.get(17));//到账情况
                product.setType(rowList.get(18));
                product.setOrderId(rowList.get(19));//orderId

                dealMapper.insertSelective(product);  //将商品插入数据库

            }

        }
    }

    /**
     * 改造poi默认的toString（）方法如下
     *
     * @param @param  cell
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getStringVal
     * @Description: 1.对于不熟悉的类型，或者为空则返回""控制串
     * 2.如果是数字，则修改单元格类型为String，然后返回String，这样就保证数字不被格式化了
     */
    public static String getStringVal(HSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }
}
