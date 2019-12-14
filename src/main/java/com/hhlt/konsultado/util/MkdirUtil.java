package com.hhlt.konsultado.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MkdirUtil {

    /** 服务器生成文件
     * @param localExportExcelPath
     * @param sheetName
     * @param workbook
     * @param type    比如： xlsx  docx
     * @throws IOException
     */
    public static void mkdir(String localExportExcelPath, String sheetName, HSSFWorkbook workbook, String type) throws IOException {
        FileOutputStream outputStream = null;
        try {
            File folder = new File(localExportExcelPath);  //文件目录
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String fileName = sheetName + "." + type;
            String savePath = folder + File.separator + fileName;
            outputStream = new FileOutputStream(savePath);
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
