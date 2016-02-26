package laucher.joy.com.JOYLauncher.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import laucher.joy.com.JOYLauncher.MyApp;

public class SharedPreferenceUtil
{
  public static boolean getBoolean(String paramString, boolean paramBoolean)
  {
    if (MyApp.prefs != null) {
      return MyApp.prefs.getBoolean(paramString, paramBoolean);
    }
    return false;
  }
  
  public static int getInt(String paramString, int paramInt)
  {
    if (MyApp.prefs != null) {
      paramInt = MyApp.prefs.getInt(paramString, paramInt);
    }
    return paramInt;
  }
  
  public static String getString(String paramString)
  {
    return MyApp.prefs.getString(paramString, null);
  }
  
  public static String getString(String paramString1, String paramString2)
  {
    return MyApp.prefs.getString(paramString1, paramString2);
  }
  
  public static String[] getStringArray(String paramString)
  {
    String str = MyApp.prefs.getString(paramString, null);
    boolean bool = "".equals(str);
    String[] arrayOfString = null;
    if (!bool)
    {
      arrayOfString = null;
      if (str != null) {
        arrayOfString = str.split(",");
      }
    }
    return arrayOfString;
  }
  
  public static void putBoolean(String paramString, boolean paramBoolean)
  {
    Editor localEditor = MyApp.prefs.edit();
    localEditor.remove(paramString);
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.apply();
  }
  
  public static void putInt(String paramString, int paramInt)
  {
    Editor localEditor = MyApp.prefs.edit();
    localEditor.remove(paramString);
    localEditor.putInt(paramString, paramInt);
    localEditor.apply();
  }
  
  public static void putString(String paramString1, String paramString2)
  {
    Editor localEditor = MyApp.prefs.edit();
    localEditor.remove(paramString1);
    localEditor.putString(paramString1, paramString2);
    localEditor.apply();
  }
}
