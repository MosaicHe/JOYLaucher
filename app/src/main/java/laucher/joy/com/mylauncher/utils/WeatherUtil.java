package laucher.joy.com.mylauncher.utils;

import android.content.Context;
import java.io.PrintStream;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherUtil
{
  public static final String CITY_CODE = "city_code";
  public static final String VALID_TIME = "validtime";
  public static final String WEATHER_INFO = "weatherinfo";
  
  public static String getTopLeftInfo(String paramString)
  {
    String[] arrayOfString = paramString.split("  ");
    StringBuilder localStringBuilder = new StringBuilder();
    if (arrayOfString.length > 0) {
      return DateUtil.getWeekDate(Calendar.getInstance()) + "  " + arrayOfString[1];
    }
    return null;
  }
  
  public static String getWeatherInfo(Context mContext, String cityCode)
  {
    String weatherUrlStr = " http://www.weather.com.cn/data/cityinfo/" + cityCode + ".html";
    String weatherInfoStr = validCity(mContext, cityCode);
    if (weatherInfoStr == null){
      String weatherContentStr = NetUtil.getContentString(weatherUrlStr);
      if (weatherContentStr != null) {
        try
        {
          JSONObject localJSONObject = new JSONObject(weatherContentStr).getJSONObject("weatherinfo");
          if (localJSONObject != null)
          {
              String weatherinfoStr = localJSONObject.getString("city") + "  " + localJSONObject.getString("weather") + "  " + localJSONObject.getString("temp1");
              System.out.println(weatherinfoStr);
              SPUtils.setString(mContext, "validtime", DateUtil.setValidTime());
              SPUtils.setString(mContext, "city_code", cityCode);
              SPUtils.setString(mContext, "weatherinfo", weatherinfoStr);
              System.out.println("@@@@@@@@@@@@@@@@@@@@" + weatherinfoStr);
              return weatherinfoStr;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return null;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    return null;
  }
  
  private static String validCity(Context mContext, String cityCode)
  {
    if ((cityCode.equals(SPUtils.getString(mContext, "city_code"))) && (Long.parseLong(SPUtils.getString(mContext, "validtime")) > System.currentTimeMillis())) {
      return SPUtils.getString(mContext, "weatherinfo");
    }
    return null;
  }
}
