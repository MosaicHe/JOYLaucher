package laucher.joy.com.mylauncher.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import laucher.joy.com.mylauncher.entity.AppBean;

/**
 * Created by Administrator on 2015/12/23.
 */
public class LancherBiz {
    private Context mContext;

    public LancherBiz(Context context) {
        this.mContext = context;
    }

    private boolean isSystem(String pkge) {
        if (pkge.contains("com.android.")) {
            return true;
        }
        if (pkge.contains("com.google.")) {
            return true;
        }
        return false;
    }

    public ArrayList<AppBean> getLauncherApps() {
        PackageManager pm = mContext.getPackageManager();
        ArrayList<AppBean> localArrayList = null;
        List<ResolveInfo> list;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        list = pm.queryIntentActivities(intent, 32);

        if (list != null) {
            localArrayList = new ArrayList<AppBean>();

            for (ResolveInfo localResolveInfo : list) {
                if (!isSystem(localResolveInfo.activityInfo.packageName)) {
                    AppBean localAppBean = new AppBean();
                    localAppBean.setIcon(localResolveInfo.activityInfo.loadIcon(pm));
                    localAppBean.setName(localResolveInfo.activityInfo.loadLabel(pm).toString());
                    localAppBean.setPackageName(localResolveInfo.activityInfo.packageName);
                    localAppBean.setDataDir(localResolveInfo.activityInfo.applicationInfo.publicSourceDir);
                    localArrayList.add(localAppBean);
                }
            }
        }

        return localArrayList;
    }


    public ArrayList<AppBean> getUserApps() {
        PackageManager pm = mContext.getPackageManager();
        ArrayList<AppBean> localArrayList = null;
        List<ResolveInfo> list;
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        list = pm.queryIntentActivities(intent, 32);

        if (list != null) {
            localArrayList =  new ArrayList<AppBean>();

            for (ResolveInfo localResolveInfo : list) {
                if (!isSystem(localResolveInfo.activityInfo.packageName)) {
                    AppBean localAppBean = new AppBean();
                    localAppBean.setIcon(localResolveInfo.activityInfo.loadIcon(pm));
                    localAppBean.setName(localResolveInfo.activityInfo.loadLabel(pm).toString());
                    localAppBean.setPackageName(localResolveInfo.activityInfo.packageName);
                    localAppBean.setDataDir(localResolveInfo.activityInfo.applicationInfo.publicSourceDir);
                    localArrayList.add(localAppBean);
                }
            }
        }

        return localArrayList;
    }
}

