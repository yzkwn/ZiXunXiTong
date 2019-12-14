package com.hhlt.konsultado.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;

public class MD5 {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 16进制字串
     */

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
//		String resultString = null;
//
//		try {
//			resultString = new String(origin);
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			resultString = byteArrayToHexString(md.digest(resultString
//					.getBytes("utf-8")));
//		} catch (Exception ex) {
//
//		}
//		return resultString;
        return encodeByName(origin, null);
    }
    public static String SHA1Encode(String origin) {
//		String resultString = null;
//
//		try {
//			resultString = new String(origin);
//			MessageDigest md = MessageDigest.getInstance("SHA-1");
//			resultString = byteArrayToHexString(md.digest(resultString
//					.getBytes("utf-8")));
//		} catch (Exception ex) {
//
//		}
//		return resultString;
        return encodeByName(origin, "SHA-1");
    }

    public static String sha256Hex(String signingKey, String stringToSign) {
        String resultString = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(signingKey.getBytes("utf-8"), "HmacSHA256"));
            resultString = new String(Hex.encodeHex(mac.doFinal(stringToSign.getBytes("utf-8"))));
        } catch (Exception e) {
        }
        return resultString;
    }
    public static String encodeByName(String origin,String encodeName){
        if(encodeName==null || encodeName.trim().isEmpty())encodeName="MD5";
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance(encodeName);
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
        } catch (Exception ex) {

        }
        return resultString;
    }
    /** 大文件加密  */
    public static String encodeBigFileByName(File file,String encodeName){
        try {
            FileInputStream input = new FileInputStream(file);
            return encodeBigFileByInput(input, encodeName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 数据流加密  */
    public static String encodeBigFileByInput(InputStream input,String encodeName){
        if(encodeName==null || encodeName.trim().isEmpty())encodeName="MD5";
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance(encodeName);
//			FileInputStream input = new FileInputStream(file);
            byte[] bs = new byte[1024*8];
            int i;
            while((i=input.read(bs))!=-1){
                md.update(bs, 0, i);
            }
            input.close();
            resultString = byteArrayToHexString(md.digest());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultString;
    }
    /** 字节加密  */
    public static String encodeBigFileByByte(byte[] bs,String encodeName){
        if(encodeName==null || encodeName.trim().isEmpty())encodeName="MD5";
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance(encodeName);
            resultString = byteArrayToHexString(md.digest(bs));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
    public static void main(String[] args) {
//		sha256Hex
//		(
        String signingKey ="我爱中华";
        String stringToSign ="爱我中华";
        String r = sha256Hex(signingKey, stringToSign);
        System.out.println(r);
    }
}