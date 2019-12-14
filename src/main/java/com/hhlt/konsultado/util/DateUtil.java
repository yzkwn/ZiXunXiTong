package com.hhlt.konsultado.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期格式化字符串
 * @author YZK
 * @since 2018-04-05 21:28:20
 *
 */
public  class DateUtil {
	private static  SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");
	private static Date getCurrentDate(){
		return new Date();
	}
	private static Date getCurrentDate(double minutes){
		return new Date(System.currentTimeMillis()-(long)(minutes*60000));
	}
	/**
	 * 得到当前日期时间
	 * @return String 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getCurrentDate());
	}
	/**
	 * 得到当前minutes分钟之前的日期时间
	 * @param double minutes
	 * @return String 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getBeforeDateTime(double minutes){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getCurrentDate(minutes));
	}
	/**
	 * 得到当前minutes分钟之后的日期时间
	 * @param double minutes
	 * @return String 格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getAfterDateTime(double minutes){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getCurrentDate(-minutes));
	}
	/**
	 * 得到当前日期
	 * @return String 格式 yyyy-MM-dd
	 */
	public static String getDate(){
		return new SimpleDateFormat("yyyy-MM-dd").format(getCurrentDate());
	}
	/**
	 * 得到当前时间
	 * @return String 格式 HH:mm:ss
	 */
	public static String getTime(){
		return new SimpleDateFormat("HH:mm:ss").format(getCurrentDate());
	}
	/**
	 * 得到当前minutes分钟之前的时间
	 * @param double minutes
	 * @return String 格式 HH:mm:ss
	 */
	public static String getBeforeTime(double minutes){
		return new SimpleDateFormat("HH:mm:ss").format(getCurrentDate(minutes));
	}
	/**
	 * 得到当前minutes分钟之后的时间
	 * @param double minutes
	 * @return String 格式 HH:mm:ss
	 */
	public static String getAfterTime(double minutes){
		return new SimpleDateFormat("HH:mm:ss").format(getCurrentDate(-minutes));
	}
	/**
	 * 得到当前日期之前时间戳
	 * @return String 格式 yyyyMMddHHmmss
	 */
	public static String getDateTimeStamp(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(getCurrentDate());
	}
	/**
	 * 得到当前minutes分钟之前日期时间戳
	 * @return String 格式 yyyyMMddHHmmss
	 */
	public static String getBeforeDateTimeStamp(double minutes){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(getCurrentDate(minutes));
	}
	/**
	 * 得到当前minutes分钟之后日期时间戳
	 * @return String 格式 yyyyMMddHHmmss
	 */
	public static String getAfterDateTimeStamp(double minutes){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(getCurrentDate(-minutes));
	}
	/**
	 * 得到当前时间戳
	 * @return String 格式 HHmmss
	 */
	public static String getTimeStamp(){
		return new SimpleDateFormat("HHmmss").format(getCurrentDate());
	}

	public static String formatDateToTimeStamp(String oldString){return oldString.trim().replace("-","").replace(":","").replace(" ","");}

	/**
	 * 计算两个日期相差几天
	 *
	 * @param startTime ： 开始时间  最新的时间在前
	 * @param endTime   ： 结束时间
	 * @return
	 */
	public static int caculateTotalTime(String startTime, String endTime) {
		Date date1 = null;
		Date date = null;
		Long l = 0L;
		try {
			date = dayFmt.parse(startTime);
			long ts = date.getTime();
			date1 = dayFmt.parse(endTime);
			long ts1 = date1.getTime();

			l = (ts1 - ts) / (1000 * 60 * 60 * 24);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return l.intValue();
	}

	//日期往后加几天
	public static  String addDate(String now, int day) {
		Calendar fromCal = Calendar.getInstance();
		try {
			Date date = dayFmt.parse(now);
			fromCal.setTime(date);
			fromCal.add(Calendar.DATE, day);
			return dayFmt.format(fromCal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
