package laucher.joy.com.JOYLauncher.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.AsyncHttpResponseHandler;

/*import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import laucher.joy.com.JOYLauncher.MyApp;
import laucher.joy.com.JOYLauncher.activity.IndexActivity;

public class NetUtil
{
  private static int CONN_TIMEOUT = 60000;
  private static int READ_TIMEOUT = 60000;
  
/*  public static String getContentString(String paramString)
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
  }*/

    public static String inputStream2String(InputStream   is)
            throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i=-1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        baos.close();
        return  baos.toString();
    }

    public static String getContentString(String urlStr){
      String result = null;
      URL url = null;
      try {
          url = new URL(urlStr);
          HttpURLConnection conn = (HttpURLConnection)url.openConnection();
          //conn.setConnectTimeout(10*1000);
          conn.setRequestMethod("GET");
          InputStream inStream = conn.getInputStream();
          //byte[] data = StreamTool.readInputStream(inStream);
          result = inputStream2String(inStream);
          inStream.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
      System.out.println("@@@@@@@@@@@@@@@@@@"+result);
      return result;
  }

  public static boolean isNetworkConnected()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager) MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
}
