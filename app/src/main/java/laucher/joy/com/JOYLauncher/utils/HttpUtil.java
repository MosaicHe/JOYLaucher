package laucher.joy.com.JOYLauncher.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public final class HttpUtil
{
  private static AsyncHttpClient client = new AsyncHttpClient();
  
  static
  {
    client.setTimeout(8000);
  }
  
  public static void get(String paramString, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    client.get(paramString, paramAsyncHttpResponseHandler);
  }
  
  public static void get(String paramString, BinaryHttpResponseHandler paramBinaryHttpResponseHandler)
  {
    client.get(paramString, paramBinaryHttpResponseHandler);
  }
  
  public static void get(String paramString, JsonHttpResponseHandler paramJsonHttpResponseHandler)
  {
    client.get(paramString, paramJsonHttpResponseHandler);
  }
  
  public static void get(String paramString, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    client.get(paramString, paramRequestParams, paramAsyncHttpResponseHandler);
  }
  
  public static AsyncHttpClient getClient()
  {
    return client;
  }
  
  public static void post(String paramString, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    client.post(paramString, paramRequestParams, paramAsyncHttpResponseHandler);
  }
}
