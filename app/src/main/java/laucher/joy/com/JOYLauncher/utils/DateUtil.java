package laucher.joy.com.JOYLauncher.utils;

import java.util.Calendar;

public class DateUtil
{
  public static String getDateWeek(Calendar mCalendar)
  {
    return getWeekOfDate() + "   " + mCalendar.get(Calendar.YEAR) + "-" + pad(1 + mCalendar.get(Calendar.MONTH)) + "-" + mCalendar.get(Calendar.DATE);
  }
  
  public static String getTime(Calendar paramCalendar)
  {
    return pad(paramCalendar.get(Calendar.HOUR_OF_DAY)) + ":" + pad(paramCalendar.get(Calendar.MINUTE));
  }
  
  public static String getWeekDate(Calendar paramCalendar)
  {
    return paramCalendar.get(Calendar.YEAR) + "-" + pad(1 + paramCalendar.get(Calendar.MONTH)) + "-" + paramCalendar.get(Calendar.DAY_OF_MONTH) + "  " + getWeekOfDate();
  }
  
  public static String getWeekOfDate()
  {
    return new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }[(-1 + Calendar.getInstance().get(Calendar.DAY_OF_WEEK))];
  }
  
  private static String pad(int paramInt)
  {
    if (paramInt >= 10) {
      return String.valueOf(paramInt);
    }
    return "0" + String.valueOf(paramInt);
  }
  
  public static String setValidTime()
  {
    return String.valueOf(18000000L + System.currentTimeMillis());
  }
}
