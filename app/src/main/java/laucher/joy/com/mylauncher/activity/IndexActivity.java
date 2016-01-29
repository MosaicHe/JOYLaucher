package laucher.joy.com.mylauncher.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

import laucher.joy.com.mylauncher.MyApp;
import laucher.joy.com.mylauncher.R;
import laucher.joy.com.mylauncher.entity.AdBean;
import laucher.joy.com.mylauncher.utils.HttpUtil;
import laucher.joy.com.mylauncher.utils.JsonUtils;
import laucher.joy.com.mylauncher.utils.NetUtil;

/**
 * Created by mosaic on 15-12-14.
 */
public class IndexActivity extends Activity {

    final String TAG = "IndexActivity";

    private NetworkReceiver nR = null;

    public static final int MSG_TO_MAIN = 16;
    public static final int MSG_TIMEOUT = 20;
    public static final int MSG_NO_AGENT = 23;
    public static final int MSG_GET_COUNTRY = 24;

    private boolean is_timeout = false;
    private ArrayList<AdBean> allAds = new ArrayList();

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TO_MAIN:
                    redirectTo();
                    break;
                case MSG_TIMEOUT:
                    redirectTo();
                    break;
                case MSG_GET_COUNTRY:
                    Log.e(TAG, "下载全国广告" + "http://120.24.227.182:8080/WeiShi/getskin.action?area=全国");
                    HttpUtil.get("http://120.24.227.182:8080/WeiShi/getskin.action?area=全国", new AsyncHttpResponseHandler() {
                        @Override
                        public void onFailure(int var1, Header[] var2, byte[] var3, Throwable var4) {
                            is_timeout = false;
                            Toast.makeText(IndexActivity.this, "获取不到服务器上的资源", Toast.LENGTH_SHORT).show();
                            mHandler.sendEmptyMessageDelayed(MSG_TO_MAIN, 2000);
                        }
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            try
                            {
                                is_timeout = true;
                                System.out.println(new String(bytes));
                                JsonUtils.parseAdJson(new String(bytes), allAds);
                                Iterator localIterator = allAds.iterator();
                                while(localIterator.hasNext())
                                {
                                    AdBean localAdBean = (AdBean) localIterator.next();
                                    Log.v(TAG, "全国广告的url%%%%%%%%%%%%%:" + localAdBean.getPicUrl());
                                }
                                mHandler.sendEmptyMessage(MSG_TO_MAIN);
                            }
                            catch (Exception localException)
                            {
                                is_timeout = false;
                                Toast.makeText(IndexActivity.this, "网络不太稳定", Toast.LENGTH_SHORT).show();
                                mHandler.sendEmptyMessageDelayed(MSG_TO_MAIN, 2000);
                            }
                            return;
                        }
                    });
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    };

    private void redirectTo() {
        Intent localIntent = new Intent(this, HomeActivity.class);
        if (allAds != null) {
            localIntent.putParcelableArrayListExtra("ads", allAds);
        }
        startActivity(localIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        nR = new NetworkReceiver(this);
        nR.registerReceiver();
        if(!NetUtil.isNetworkConnected())
            mHandler.sendEmptyMessageDelayed(MSG_TIMEOUT, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nR.unRegisterReceiver();
        nR = null;
        mHandler = null;
        System.gc();
    }

    final class NetworkReceiver extends BroadcastReceiver {
        private Context mContext;
        public NetworkReceiver(Context paramContext) {
            mContext = paramContext;
        }

        public void onReceive(Context paramContext, Intent paramIntent) {
            System.out.println("IndexActivity onReceive");
            if (NetUtil.isNetworkConnected()) {
                mHandler.sendEmptyMessage(MSG_GET_COUNTRY);
            }
            /*
            else
            {
                mHandler.sendEmptyMessageDelayed(MSG_TO_MAIN, 2000);
            }
            */
        }

        public void registerReceiver() {
            IntentFilter localIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            mContext.registerReceiver(this, localIntentFilter);
        }

        public void unRegisterReceiver() {
            mContext.unregisterReceiver(this);
        }
    }
}
