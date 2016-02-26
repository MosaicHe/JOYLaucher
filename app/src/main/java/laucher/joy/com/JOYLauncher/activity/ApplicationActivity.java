package laucher.joy.com.JOYLauncher.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.HashMap;

import laucher.joy.com.JOYLauncher.R;
import laucher.joy.com.JOYLauncher.adpter.ApplicationAdapter;
import laucher.joy.com.JOYLauncher.entity.AppBean;
import laucher.joy.com.JOYLauncher.utils.LancherBiz;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ApplicationActivity extends Activity {


    private ApplicationAdapter adapter;
    private ArrayList<AppBean> appBeans;
    // private AppDB appDb;
    private GridView appGrid;
    private Dialog contentDia;
    private Dialog positionDia;
    private AppReceiver receiver;
    private int selectionPosition = 0;
    private HashMap<Integer, AppBean> topApps;
    IntentFilter intentFilter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ApplicationActivity", "onCreate==============");
        setContentView(R.layout.app_list);

        this.receiver = new AppReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");

        intentFilter.addDataScheme("package");
        registerReceiver(this.receiver, intentFilter);
        initView();
        initListener();

        Log.i("ApplicationActivity", "onCreate======over========");
    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(this.receiver);
        super.onDestroy();
    }

    private void initView() {
        Log.i("ApplicationActivity", "initView========================");
        appGrid = ((GridView) findViewById(R.id.app_grid_new));
        appBeans = new LancherBiz(this).getLauncherApps();
//        appBeans = new ArrayList<AppBean>(6);
        adapter = new ApplicationAdapter(this, this.appBeans);
        appGrid.setAdapter(this.adapter);
    }

    private void showAppDetail(int paramInt) {
        Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", ((AppBean) this.appBeans.get(paramInt)).getPackageName(), null));
        startActivity(localIntent);
    }

    private void unInstallApp(int paramInt) {
        startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + ((AppBean) this.appBeans.get(paramInt)).getPackageName())));
    }

    private void refrushView() {
        this.appBeans = new LancherBiz(this).getLauncherApps();
        this.adapter.changeData(this.appBeans);
    }


    private void bootAnApp(int paramInt) {
        Intent localIntent = null;
        if (this.appBeans.get(paramInt) != null) {
            localIntent = getPackageManager().getLaunchIntentForPackage(((AppBean) this.appBeans.get(paramInt)).getPackageName());
        }
        try {
            startActivity(localIntent);
            return;
        } catch (Exception localException) {
            localException.printStackTrace();
            this.appBeans.remove(paramInt);
            this.adapter.changeData(this.appBeans);
        }
    }

    private void initListener() {
        Log.i("ApplicationActivity", "initListener========================");
        this.appGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                ApplicationActivity.this.selectionPosition = paramAnonymousInt;
                ApplicationActivity.this.bootAnApp(paramAnonymousInt);
            }
        });

        Log.i("ApplicationActivity", "initListener==========1111==============");
        this.appGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                ApplicationActivity.this.selectionPosition = paramAnonymousInt;
                ApplicationActivity.this.unInstallApp(paramAnonymousInt);
                return false;
            }
        });
    }

    class AppReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("ApplicationActivity", "AppReceiver=========");
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                refrushView();
            }

            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                refrushView();
            }
        }
    }
}
