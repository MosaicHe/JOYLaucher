package laucher.joy.com.JOYLauncher.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtils
{
  public static final String PREFERENCE_NAME = "jcvod";
  
  public static void addLong(Context paramContext, String paramString, long paramLong)
  {
    setLong(paramContext, paramString, paramLong + getLong(paramContext, paramString));
  }
  
  public static boolean getBoolean(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    boolean bool = false;
    if (localSharedPreferences != null) {
      bool = localSharedPreferences.getBoolean(paramString, false);
    }
    return bool;
  }
  
  public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null) {
      return localSharedPreferences.getBoolean(paramString, paramBoolean);
    }
    return false;
  }
  
  public static int getInt(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    int i = 0;
    if (localSharedPreferences != null) {
      i = localSharedPreferences.getInt(paramString, 0);
    }
    return i;
  }
  
  public static long getLong(Context paramContext, String paramString)
  {
    long l = 0L;
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null) {
      l = localSharedPreferences.getLong(paramString, l);
    }
    return l;
  }
  
  public static SharedPreferences getPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences("jcvod", 0);
  }
  
  public static String getString(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null) {
      return localSharedPreferences.getString(paramString, "");
    }
    return "";
  }
  
  public static void setBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null)
    {
      Editor localEditor = localSharedPreferences.edit();
      localEditor.remove(paramString);
      localEditor.putBoolean(paramString, paramBoolean);
      localEditor.commit();
    }
  }
  
  public static void setInt(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null)
    {
      Editor localEditor = localSharedPreferences.edit();
      localEditor.remove(paramString);
      localEditor.putInt(paramString, paramInt);
      localEditor.commit();
    }
  }
  
  public static void setLong(Context paramContext, String paramString, long paramLong)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null)
    {
      Editor localEditor = localSharedPreferences.edit();
      localEditor.remove(paramString);
      localEditor.putLong(paramString, paramLong);
      localEditor.commit();
    }
  }
  
  public static void setString(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences localSharedPreferences = getPreferences(paramContext);
    if (localSharedPreferences != null) {
      localSharedPreferences.edit().remove(paramString1).putString(paramString1, paramString2).commit();
    }
  }
}
