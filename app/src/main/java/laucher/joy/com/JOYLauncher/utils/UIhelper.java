package laucher.joy.com.JOYLauncher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import laucher.joy.com.JOYLauncher.MyApp;
import laucher.joy.com.JOYLauncher.R;
import laucher.joy.com.JOYLauncher.activity.WebActivity;

public class UIhelper
{
  private static final String TAG = "UIhelper";
  
  public static void bootActivity(Context paramContext, Class paramClass, int paramInt)
  {
    Intent localIntent = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putInt("num", paramInt);
    localIntent.putExtras(localBundle);
    localIntent.setClass(paramContext, paramClass);
    paramContext.startActivity(localIntent);
  }
  
  public static void bootAnApp(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClassName(paramString1, paramString2);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      Toast.makeText(paramContext, paramContext.getResources().getString(R.string.software_not_installed), Toast.LENGTH_LONG).show();
    }
  }
  
  public static boolean isPackageExist(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return false;
    }
    try
    {
      MyApp.getInstance().getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {
        return false;
    }
  }
  
  public static void jumpToWeb(Context paramContext, String url)
  {
    if(url==null || url.equals("")) {
        Toast.makeText(paramContext, "url=null", Toast.LENGTH_SHORT);
        return;
    }
    Intent localIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    if (isPackageExist("com.android.browser")) {
      localIntent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
    }
    //localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    paramContext.startActivity(localIntent);
  }
  
  public static void jumpToWeb2(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, WebActivity.class);
    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    localIntent.putExtra("web_url", paramString);
    paramContext.startActivity(localIntent);
  }
  
  public static void openFile(Context paramContext, String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("cur_path", "/storage/emulated/0" + paramString);
    Intent localIntent = new Intent();
    localIntent.putExtras(localBundle);
    if (isPackageExist("com.hisilicon.android.mediacenter"))
    {
      localIntent.setClassName("com.hisilicon.android.mediacenter", "com.hisilicon.android.mediacenter.activity.TabBarExample");
      localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      MyApp.context.startActivity(localIntent);
      return;
    }
    Toast.makeText(MyApp.context, "文件浏览器没装", Toast.LENGTH_LONG).show();
  }
  
  public static void startApp(Context paramContext, String paramString)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (localIntent != null)
    {
      localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      paramContext.startActivity(localIntent);
      return;
    }
    Toast.makeText(paramContext, paramContext.getResources().getString(R.string.software_not_installed), Toast.LENGTH_SHORT).show();
  }
  
  public static void startApp(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString1, paramString2);
    localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    paramContext.startActivity(localIntent);
  }
}
