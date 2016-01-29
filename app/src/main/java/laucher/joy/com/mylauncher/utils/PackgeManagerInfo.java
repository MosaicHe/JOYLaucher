package laucher.joy.com.mylauncher.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.WindowManager;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/26.
 */
public class PackgeManagerInfo {


    private static Context mContext;

    public PackgeManagerInfo(Context mContext) {
        this.mContext = mContext;
    }

    public static boolean getPackageName(String packageName, Context context) {
        mContext = context;
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }

        return pName.contains(packageName);
    }

    public static void getIntent(String pkg, String pkga, Context context) {
        try {
            mContext = context;
            Intent i = new Intent();
            ComponentName componetName = new ComponentName(pkg, pkga);
            i.setComponent(componetName);
            if (i != null) {
                context.startActivity(i);
            } else {
                return;
            }
        } catch (Exception ex) {
                Log.i("ex",ex.toString());
        }

    }

    public static void startIntent(Context context, String strUrlPrefix) {
        mContext = context;
        Intent intent = null;
        try {
            intent = Intent.parseUri(strUrlPrefix, Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            intent.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.i("contentID", strUrlPrefix);

        } catch (URISyntaxException e) {

        }


    }

}
