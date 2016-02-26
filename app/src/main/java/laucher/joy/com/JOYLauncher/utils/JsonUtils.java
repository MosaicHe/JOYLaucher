package laucher.joy.com.JOYLauncher.utils;

import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import laucher.joy.com.JOYLauncher.entity.AdBean;

public final class JsonUtils
{
  private static final String TAG = "JsonUtils";
  
  public static String InputStreamToString(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    int len=0;
    try
    {
      len = paramInputStream.read(arrayOfByte, 0, 1024);
      if(len!=-1)
      {
          localByteArrayOutputStream.write(arrayOfByte, 0, len);
          return new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public static List<String> getTag(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    JSONArray localJSONArray;
    JSONObject localJSONObject;
    {
      try
      {
        Log.e(TAG, paramString);
        localJSONArray = new JSONArray(paramString);
        for(int i=0; i<localJSONArray.length(); i++)
        {
         localJSONObject = localJSONArray.getJSONObject(i);
         localArrayList.add(localJSONObject.getString("retailer"));
         localArrayList.add(localJSONObject.getString("retailerName"));
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
    }
    return localArrayList;
  }
  
  private static InputStream openAssetsFile(Context paramContext, String paramString)
  {
    try
    {
      InputStream localInputStream = paramContext.getAssets().open(paramString);
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }
  
  public static void parseAdJson(String paramString, ArrayList<AdBean> paramArrayList)
  {
    JSONArray localJSONArray;
    JSONObject localJSONObject=null;
    try
    {
      localJSONArray = new JSONArray(paramString);
      for(int i=0; i<localJSONArray.length(); i++)
      {
        localJSONObject = localJSONArray.getJSONObject(i);

      if(localJSONObject!=null)
        paramArrayList.add(new AdBean(localJSONObject.getInt("id"),
                localJSONObject.getString("picurl"),
                localJSONObject.getString("weburl"),
                localJSONObject.getInt("type")));
      }
    }
    catch (Exception localException)
    {
      Log.e(TAG, paramString);
      localException.printStackTrace();
    }

  }
  
  public static void parseLocalAdJson(Context paramContext, ArrayList<AdBean> paramArrayList)
  {
    parseAdJson(InputStreamToString(openAssetsFile(paramContext, "ad.json")), paramArrayList);
  }
}
