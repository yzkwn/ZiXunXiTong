package com.hhlt.konsultado.util;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownUtil {

    /**
     * filename 下载到客户端后的文件名称
     * filepath 文件的路径 绝对路径和相对路径都可以
     * 下载 文件
     */
    public static void downLoad(String filename, String filepath, HttpServletResponse response, String type) {
        FileInputStream inStream = null;
        try {
                File zip = new File(filepath);// 上传文件路径
                //检查文件是否存在
                if (!zip.exists()) {
                    //如果不存在创建文件夹
                    zip.getParentFile().mkdirs();
                    //创建文件
                    zip.createNewFile();
                }
            inStream = new FileInputStream(zip);
            byte[] buf = new byte[4096];
            int readLength;
            setResponseHeader(response, filename, type);
            while (((readLength = inStream.read(buf)) != -1)) {
                response.getOutputStream().write(buf, 0, readLength);
            }
        } catch (Exception e) {
            try {
                OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
                response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示
                byte[] dataByteArr = "下载失败".getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
                outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
                return;
            } catch (Exception ex) {

            }
        } finally {
            try {
                inStream.close();
            } catch (Exception e) {

            }
        }

    }

    /**
     * 设置响应头  文件类型为zip的   可以修改对应的后缀
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName, String type) {
        try {
            response.reset();// 清空输出流
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("GB2312"), "8859_1")
                    + "." + type);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
