package laucher.joy.com.mylauncher.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import laucher.joy.com.mylauncher.MyApp;

public class NetUtil
{
  private static int CONN_TIMEOUT = 60000;
  private static int READ_TIMEOUT = 60000;
  
  public static String getContentString(String paramString)
  {
    String content = null;
    HttpGet localHttpGet = new HttpGet(paramString);
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 3000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 5000);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
    try
    {
      HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        content = EntityUtils.toString(localHttpResponse.getEntity());
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    finally
    {
      localDefaultHttpClient.getConnectionManager().shutdown();
      return content;
    }
  }
  
  public static InputStream getInputStream(String paramString)
    throws MalformedURLException, IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
    localHttpURLConnection.setDoInput(true);
    localHttpURLConnection.setConnectTimeout(CONN_TIMEOUT);
    localHttpURLConnection.setReadTimeout(READ_TIMEOUT);
    localHttpURLConnection.connect();
    return localHttpURLConnection.getInputStream();
  }
  
  public static boolean isNetworkConnected()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager) MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
}
