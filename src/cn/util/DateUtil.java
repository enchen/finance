package cn.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	/**
	 * 按格式将时间转换成字符
	 * @param format {yyyy-MM-dd,yyyyMMdd,yyyy-MM-dd hh:ss...}
	 * @param date
	 * @return
	 */
	public static String DateToStr(String format,Date date){
		String s="";
		try{
			s=new SimpleDateFormat(format).format(date);
		}catch(Exception e){
			s="";
		}
		return s;
	}
	//获取日期最小刻度
	public static Date DateMin(Date date)
	{
		return StringToDate("yyyy-MM-dd HH:mm:ss", DateToStr("yyyy-MM-dd", date)+" 00:00:00");
	}
	//获取日期最大刻度
	public static Date DateMax(Date date)
	{
		return StringToDate("yyyy-MM-dd HH:mm:ss", DateToStr("yyyy-MM-dd", date)+" 23:59:59");
	}
	
	/**
	 * 按格式将字符转换成时间
	 * @param format
	 * @param date
	 * @return
	 */
	public static Date StringToDate(String format,String date){
		Date d=null;
		try{
			d=new SimpleDateFormat(format).parse(date);
		}catch(Exception e){
			d=null;
		}
		return d;
	}
	
	/**
	 * 按格式将字符转换成时间
	 * @param format
	 * @param date
	 * @return
	 */
	public static int DateToSesson(Date date){
		int m=date.getMonth()+1;
		if(m==1||m==2||m==3)
		{
			return 0;
		}
		if(m==4||m==5||m==6)
		{
			return 1;
		}
		if(m==7||m==8||m==9)
		{
			return 2;
		}
		if(m==10||m==11||m==12)
		{
			return 3;
		}
		else
		{
			return -1;
		}
		
	}
	public static List<Date>DateOfWeek(String wid)
	{
		List<Date> list=new ArrayList<Date>();
		Date date=DateUtil.StringToDate("yyyy-mm-dd", wid.substring(0,4)+"-01-01");
		int days=Integer.parseInt(wid.substring(4))*7-DateUtil.DateToNoOfWeek(date)-6;
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, days);
		
		
		list.add(c.getTime());
		c.add(Calendar.DAY_OF_YEAR, 6);
		list.add(c.getTime());
		return list;
	}
	public static int DateToNoOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK)-1 ;
        if(w==0)
        {w=7;}
       return w;
	}
	public static String DateToWeekDay(Date date){
		
		 String[] weekDays = {"SUN","MON", "TUE", "WED", "THU", "FRI", "SAT"};
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);

         int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
         if (w < 0)
             w = 0;

        return weekDays[w];
	}
	
	public static String DateToWeekDayCN(Date date){
		
		 String[] weekDays = {"周日","周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

       return weekDays[w];
	}
	//获取当前时间周次
	public static String getWeekId()
	{
        Calendar calendar=Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar.getWeekYear()+""+calendar.get(Calendar.WEEK_OF_YEAR);
	}
}
