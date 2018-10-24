package com.wardrobe.common.util;

import com.wardrobe.common.constant.IPlatformConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static final String YYYYMMDD_S = "yyyyMMdd";
	public static final String YYYYMMDD_HMS = "yyyyMMddHHmmss";
	public static final String HHMM = "HH:mm";
    public static final String YYYY = "yyyy";
    public static final String YYYYMM = "yyyy-MM";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD = "yyyy/MM/dd";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";
    public static final String YYYYMMDDHHMMSS_ZH = "yyyy年MM月dd日HH:mm:ss";
	public static final String HHMMSS = "HH:mm:ss";
    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;// 中国周一是一周的第一天
    public static final String[] DAYS = {"一号","二号","三号","四号","五号","六号","七号","八号","九号","十号","十一号","十二号","十三号","十四号","十五号","十六号","十七号","十八号","十九号","二十号","二十一号","二十二号","二十三号","二十四号","二十五号","二十六号","二十七号","二十八号","二十九号","三十号","三十一号"};

	// 获取本周的七天
	public static List<String> getWeekDate(String dateStr) {
        List<String> dateList = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 今天是一周中的第几天
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK );

		if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		// 计算一周开始的日期
		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

		for (int i=1;i<=7;i++) {
			c.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(sdf.format(c.getTime()));
		}

		return dateList;
	}

    // 获取每月的日期
    public static List<String> getMonthDate(String dateStr) {
        List<String> dateList = new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        cal.setTime(cal.getTime());
        cal.set(Calendar.DATE, 1);

        int month = cal.get(Calendar.MONTH);
        while(cal.get(Calendar.MONTH) == month){
            dateList.add(sdf.format(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }

        return dateList;
    }

	/**
		 * @param time
		 * @return
		 * @Description: 时间格式转换
		 * @author wangbing
		 *@Since:2015年9月6日
		 */
	public static String formatStringTime(String time){
		long msgCreateTime = Long.parseLong(time) * 1000L;  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return format.format(new Date(msgCreateTime));
	}
	
	/**
		 * @param time
		 * @return
		 * @Description: 时间格式转换
		 * @author wangbing
		 *@Since:2015年9月6日
		 */
	public static Date formatDataTime(String time){
		long msgCreateTime = Long.parseLong(time) * 1000L;  
		return new Date(msgCreateTime);
	}
	
	/**
	 * 将date类型的日期转换为指定格式
	 */
	public static String dateToString(Date date, String format) {
		if(StringUtils.isBlank(format)) format = YYYYMMDD;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	/**
	 * 降string类型的日期转换为Date
	 */
	public static Date stringToDate(String dateStr, String format) throws ParseException {
		if(StringUtils.isBlank(format)) format = YYYYMMDD;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(dateStr);
	}
	
	/**
	 * 当前时间增加几个月，返回字符串年-月-日
	 */
	public static String getAddMonth(String month) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, com.wardrobe.common.util.StrUtil.objToInt(month));
		return dateToString(c.getTime(), YYYYMMDD);
	}
	
	public static String getAddDay(String dateStr, int addDay) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(stringToDate(dateStr, YYYYMMDD));
		c.add(Calendar.DATE, addDay);
		return dateToString(c.getTime(), YYYYMMDD);
	}
	
	public static Date addHHMMTime(Date time, int field, int amount) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(field, amount);
		return c.getTime();
	}
	
	public static Date getHHMM(String time) throws ParseException{
		DateFormat format = new SimpleDateFormat(HHMM);
		return format.parse(time);
	}

	public static Date getHHMMSS(String time) throws ParseException{
		DateFormat format = new SimpleDateFormat(HHMMSS);
		return format.parse(time);
	}

	public static Date getHHMMSS(Timestamp time) throws ParseException{
		DateFormat format = new SimpleDateFormat(HHMMSS);
		return format.parse(dateToString(new Date(time.getTime()), HHMMSS));
	}

	public static String getHHMMSS(Date time) throws ParseException{
		DateFormat format = new SimpleDateFormat(HHMMSS);
		return format.format(time);
	}
	
	//获取两个时间之间小时数
	public static int getTimeHourNums(String startTimeStr, String endTimeStr) throws ParseException{
		Date startDate = stringToDate(startTimeStr, HHMM);
		Date endDate = stringToDate(endTimeStr, HHMM);
		return (int) ((endDate.getTime() - startDate.getTime())/DateUtils.MILLIS_PER_HOUR);
	}
	
	public static int getWeek(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		if(week == 1) return 7; //周日
		return week-1; //其他星期-1
	}
	
	public static Date dateToDate(Date date) throws ParseException{
		DateFormat format = new SimpleDateFormat(YYYYMMDD);
		return format.parse(format.format(date));
	}

	public static Date addDate(Date date, int addDay){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, addDay);
		return cal.getTime();
	}

	public static Date addDateByType(Date date, int field, int amount){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, amount);
		return c.getTime();
	}
	
	/** 
     * 获取当月的 天数 
     * */  
    public static int getCurrentMonthDay() {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }
    
    // 获得本周一0点时间  Date
    public static Date getTimesWeekmorning(Date date) {
		if(date == null) date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Calendar cal = sundaySubOneDay(c);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return cal.getTime();  
    }
    
    // 获得本周一0点时间  Str
    public static String getTimesWeekmorningStr(){
     	return dateToString(getTimesWeekmorning(null), YYYYMMDDHHMMSS);
	}

	public static String getTimesWeeknightStr(){
		return dateToString(getTimesWeeknight(null), YYYYMMDD) + IPlatformConstant.time24;
	}
  
    // 获得本周日24点时间  Str
    public static Date getTimesWeeknight(Date date) {
		if(date == null) date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
        Calendar cal = sundaySubOneDay(c);
        cal.setTime(getTimesWeekmorning(date));
        cal.add(Calendar.DAY_OF_WEEK, 7);
		cal.add(Calendar.DATE, -1);
        return cal.getTime();
	}
    
    public static Calendar sundaySubOneDay(Calendar cal){
    	if(cal.get(Calendar.DAY_OF_WEEK) == 1){
    		cal.add(Calendar.DATE, -1);
    	}
    	return cal;
    }
    
    // 获得本月第一天0点时间  
    public static String getTimesMonthmorning() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return dateToString(cal.getTime(), YYYYMMDDHHMMSS);
    }  
  
    // 获得本月最后一天24点时间  
    public static String getTimesMonthnight() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));  
        cal.set(Calendar.HOUR_OF_DAY, 24);  
        return dateToString(cal.getTime(), YYYYMMDDHHMMSS); 
    }
    
    public static String getCurrentYearEndTime() {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(new Date());  
        cal.add(Calendar.YEAR, 0);  
        return dateToString(cal.getTime(), YYYY); 
    }  
  
    public static String getLastYearStartTime() {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(new Date());  
        cal.add(Calendar.YEAR, 1);  
        return dateToString(cal.getTime(), YYYY);
    }


	public static List<String> getWeekTimes(Date date) throws Exception{
		List<String> weekDays = new ArrayList<>();

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(getTimesWeekmorning(date));
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(getTimesWeeknight(date));
		while (dateToDate(startCal.getTime()).before(dateToDate(endCal.getTime()))) {
			weekDays.add(dateToString(startCal.getTime(), YYYYMMDD));
			startCal.add(Calendar.DATE, 1);
		}
		weekDays.add(dateToString(endCal.getTime(), YYYYMMDD));
		return weekDays;
	}

	public static String getAddMonth(int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, month);
		return dateToString(c.getTime(), YYYYMM);
	}

	public static String getAddYear(int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, year);
		return dateToString(c.getTime(), YYYY);
	}

	public static int getYearDay(String year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, com.wardrobe.common.util.StrUtil.objToInt(year));
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	public static int getMonthDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	public static Timestamp getNowDate(){
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 获得当天零时零分零秒
	 * @return
	 */
	public static Date initDateByDay(){
		return initDateByDay(new Date());
	}

	/**
	 * 获得某天零时零分零秒
	 * @return
	 */
	public static Date initDateByDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/*
	 * 注意事项：
		Calendar 的 month 从 0 开始，也就是全年 12 个月由 0 ~ 11 进行表示。
		而 Calendar.DAY_OF_WEEK 定义和值如下：（需要判断周日，然后其他减1）
		Calendar.SUNDAY = 1  //周日
		Calendar.MONDAY = 2
		Calendar.TUESDAY = 3
		Calendar.WEDNESDAY = 4
		Calendar.THURSDAY = 5
		Calendar.FRIDAY = 6
		Calendar.SATURDAY = 7 //周六
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(DateUtil.getHHMM("00:00:"));
		System.out.println(DateUtil.getHHMM("23:59"));
		System.out.println(DateUtil.getHHMM("24:00"));

		/*Date date = addDate(new Date(), 2);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		System.out.println( calendar.getActualMaximum(Calendar.DATE));*/

		/*System.out.println(getWeekTimes(addDate(new Date(), -1)));
		System.out.println(dateToString(addDate(new Date(), -1), YYYYMMDD));
		System.out.println(getAddMonth(-1));
		System.out.println(getAddMonth(0));
		System.out.println(getAddYear(-1));
		System.out.println(dateToString(getTimesWeeknight(addDate(new Date(), -1)), null));*/

		/*System.out.println(DateUtil.getTimesWeekmorningStr());
	    System.out.println(DateUtil.getTimesWeeknightStr());*/
		
		/*Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
		*/
		/*long s = System.currentTimeMillis();
		for(int i=0;i<100000;i++){
			System.out.println(i);
		}
		System.out.println((System.currentTimeMillis()-s)/1000.0);*/
		/*String time = "09:00";
		String end = "12:00";
		DateFormat format = new SimpleDateFormat("HH:mm"); 
		Date endTime = format.parse(end);
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(time));
		while(c.getTime().getTime() < endTime.getTime()){
			c.add(Calendar.HOUR_OF_DAY, 1);
			System.out.println(c.getTime());
		}*/
		/*String str="20110214";
        String str1="20110225";
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        try {
            start.setTime(format.parse(str));
            end.setTime(format.parse(str1));
            end.add(Calendar.DAY_OF_MONTH,1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(start.before(end))
        {
            System.out.println(format.format(start.getTime()));
            start.add(Calendar.DAY_OF_MONTH,1);
            
            System.out.println("---");
        }*/
		/*Date startDate = stringToDate("08:00", HHMM);
		Date endDate = stringToDate("12:00", HHMM);
		int a = (int) ((endDate.getTime() - startDate.getTime())/DateUtils.MILLIS_PER_HOUR);
		System.out.println(a);*/
	}

}
