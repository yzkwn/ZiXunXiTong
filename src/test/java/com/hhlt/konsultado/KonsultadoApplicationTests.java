package com.hhlt.konsultado;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.log.LogInputStream;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KonsultadoApplicationTests {
    private static final Logger Logger = LoggerFactory.getLogger(KonsultadoApplicationTests.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


//    @Test
//    public void contextLoads() throws ParseException, IOException {
//        List<Map<String, String>> list = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            Map<String, String> map = new HashMap<>();
//            map.put("orderId", i + "--orderId");
//            map.put("beibei", i + "--beibei");
//            list.add(map);
//        }
//        exportExcel(list);
//
//    }
//
//    public void mkdir(String localExportExcelPath, String sheetName, HSSFWorkbook workbook, String type) throws IOException {
//        FileOutputStream outputStream = null;
//        try {
//            File folder = new File(localExportExcelPath);  //文件目录
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
//            String fileName = sheetName + "." + type;
//            String savePath = folder + File.separator + fileName;
//            outputStream = new FileOutputStream(savePath);
//            workbook.write(outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (outputStream != null) {
//                outputStream.flush();
//                outputStream.close();
//            }
//        }
//    }
//
//    public void exportExcel(List<Map<String, String>> list) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        String sheetName = "错误明细_" + System.nanoTime();
//        HSSFSheet sheet = workbook.createSheet(sheetName);//工作表
//        sheet.setDefaultColumnWidth(20);
//
//        HSSFCell cell = null; //单元格
//        HSSFRow titleRow = sheet.createRow(0); //行
//        String[] titles = {"订单id", "备注"};
//
//        //把已经写好的标题行写入excel文件中
//        for (int i = 0; i < titles.length; i++) {
//            cell = titleRow.createCell(i);
//            cell.setCellValue(titles[i]);
//        }
//
//        //数据写入到excel中
//        HSSFRow row = null;
//        for (int i = 0; i < list.size(); i++) {
//            row = sheet.createRow(i + 1);
//            Map<String, String> map = list.get(i);
//
//            HSSFCell cell0 = row.createCell(0);
//            cell0.setCellValue(map.get("orderId"));
//            HSSFCell cell1 = row.createCell(1);
//            cell1.setCellValue(map.get("beibei"));
//
//        }
//
//        String p = "/hhlt/excel";
//        mkdir(p, sheetName, workbook, "xlsx");
//    }
//
//    @Test
//    public void test() throws IOException {
//        String filePath = "C:\\Users\\Administrator\\Desktop\\AMap_adcode_citycode.xlsx";
//        String columns[] = {"中文名", "adcode"};
//        List<Map<String, String>> list = new POIUtil().readExcel(filePath, columns);
//        list.forEach(i -> {
//            Area area = new Area();
//            String name = i.get("中文名");
//            String code = i.get("adcode");
//            area.setCode(code);
//            area.setName(name);
//            areaMapper.insertSelective(area);
//        });
////        System.out.println(strL);
//    }
//
//    public static String getStringVal(XSSFCell cell) {
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_BOOLEAN:
//                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
//            case Cell.CELL_TYPE_FORMULA:
//                return cell.getCellFormula();
//            case Cell.CELL_TYPE_NUMERIC:
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                return cell.getStringCellValue();
//            case Cell.CELL_TYPE_STRING:
//                return cell.getStringCellValue();
//            default:
//                return "";
//        }
//    }
//
//    @Test
//    public void readExcel() throws ParseException {
//        ReadExcel obj = new ReadExcel();
//        File file = new File("C:\\Users\\Administrator\\Desktop\\data\\2018.xls");
//        List excelList = obj.readExcel(file);
//        System.out.println("list中的数据打印出来");
//        int j = 0;
//        int k = 0;
//        List listF = (List) excelList.get(0);
//        listF.forEach(i -> System.out.print(i));
//        System.out.println();
//        for (int i = 1; i < excelList.size(); i++) {
//            List list = (List) excelList.get(i);
//            if (list.size() == 25) {
//                j++;
//            } else {
//                k++;
//            }
//
//
//            DataCopy data = new DataCopy();
//            list.forEach(h -> {
//                System.out.print(h + "-");
//            });
//            System.out.println();
//
//            if (!",".equals(String.valueOf(list.get(1)))) {
//                data.setName(String.valueOf(list.get(1)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(2)))) {
//                data.setConsultDate(sdf.parse( "20"+String.valueOf(list.get(2)).split(",")[0]));
//            }
//            if (!",".equals(String.valueOf(list.get(3)))) {
//                data.setTypeId(String.valueOf(list.get(3)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(4)))) {
//                data.setChannelId(String.valueOf(list.get(4)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(5)))) {
//                data.setTelephone(String.valueOf(list.get(5)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(6)))) {
//                data.setProvince(String.valueOf(list.get(6)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(8)))) {
//                data.setCity(String.valueOf(list.get(8)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(10)))) {
//                data.setWangwang(String.valueOf(list.get(10)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(11)))) {
//                data.setStoreName(String.valueOf(list.get(11)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(12)))) {
//                data.setStoreUrl(String.valueOf(list.get(12)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(13)))) {
//                data.setWeixin(String.valueOf(list.get(13)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(14)))) {
//                data.setQq(String.valueOf(list.get(14)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(15)))) {
//                data.setRemark(String.valueOf(list.get(15)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(16)))) {
//                data.setRemarkBeizhu(String.valueOf(list.get(16)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(17)))) {
//                data.setLevelId(String.valueOf(list.get(17)).split(",")[0]);
//            }
//            if (",".equals(String.valueOf(list.get(18)))) {
//                data.setIsTelephone(false);
//            } else {
//                data.setIsTelephone(true);
//            }
//            if (",".equals(String.valueOf(list.get(19)))) {
//                data.setIsTwice(false);
//            } else {
//                data.setIsTwice(true);
//            }
//            if (!",".equals(String.valueOf(list.get(20)))) {
//                data.setCurrentServicer(String.valueOf(list.get(20)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(21)))) {
//                data.setPlanformId(String.valueOf(list.get(21)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(22)))) {
//                data.setPlanformId(String.valueOf(list.get(21)).split(",")[0]);
//            }
//            if (!",".equals(String.valueOf(list.get(23)))) {
//                data.setStatus(true);
//            }else{
//                data.setStatus(false);
//            }
//            data.setCreateTime(new Date());
//
//            dataCopyMapper.insertSelective(data);
//
//        }
//        System.out.println(j);
//
//    }


//    @Test
//    public void update(){
//        List<Area> areaList = areaMapper.list();
//        List<DataCopy> dataCopies = dataCopyMapper.list();
////        System.out.println(areaList.size());
////        System.out.println(dataCopies.size());
//
//        dataCopies.forEach(i -> {
//            for (Area j : areaList) {
//                if (null != i.getCity()) {
//                    if (j.getName().contains(i.getCity())) {
//                        i.setCityNum(j.getCode());
//                        dataCopyMapper.updateByPrimaryKeySelective(i);
//                    }
//                }
//                continue;
//            }
//        });
//    }

    //    @Test
//    public static void main(String[] args) {
////    //冒泡排序算法
////    int[] numbers = new int[]{1, 5, 8, 2, 3, 9, 4};
////    int i, j;
////    for (i = 0; i < numbers.length; i++) {
////        for (j = 0; j < numbers.length - 1 - i; j++) {
////            if (numbers[j] < numbers[j + 1]) {
////                int temp = numbers[j];
////                numbers[j] = numbers[j + 1];
////                numbers[j + 1] = temp;
////            }
////        }
////    }
////    System.out.println("排序后的结果是:");
////    for(i=0;i<numbers.length;i++){
////        System.out.print(numbers[i]+" ");
//        List<Map<Object, String>> strList = new ArrayList<>();
//        HashMap map = new HashMap();
//        int a = 0;
//        map.put("a", a);
//
//
//        strList.add(map);
//
//        // 这行获取我们的stream流对象，我们的操作都是通过这个流对象进行操作的。
//        Stream<Map<Object, String>> stream = strList.stream();
//        // 去重后收集成一个集合
//
//        List<String> list = Arrays.asList("a", "b", "c");
//
//        list.stream().forEach((i) -> {
//            System.out.println(i);
//        });
//
//
//        Stream.of("a", "b", "c").forEach(System.out::println);
//
//
//    }
    @Test
    public static void main(String[] args) {
HashMap map = new HashMap();
map.put(null,1);
map.put(null,2);
        System.out.println(map);
        System.out.println(map.get(null));

        // System.out.println(today);
        List<Object> list = Stream.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
                .map(e -> new SimpleDateFormat("YYYY-").format(new Date()) +e)
                .collect(Collectors.toList());
        for (Object l:list) {
            System.out.println(l);
        }



    }


}

