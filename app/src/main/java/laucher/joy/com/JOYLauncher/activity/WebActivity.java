package laucher.joy.com.JOYLauncher.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import laucher.joy.com.JOYLauncher.R;


public class WebActivity
  extends Activity
{
  private WebView mywebView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.web);
    String str = getIntent().getStringExtra("web_url");
    this.mywebView = ((WebView)findViewById(R.id.myWebView));
    this.mywebView.getSettings().setJavaScriptEnabled(true);
    if (str != null) {
      this.mywebView.loadUrl(str);
    }
  }
}
