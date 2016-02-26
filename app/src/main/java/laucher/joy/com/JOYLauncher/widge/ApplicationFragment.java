package laucher.joy.com.JOYLauncher.widge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import laucher.joy.com.JOYLauncher.MyApp;
import laucher.joy.com.JOYLauncher.R;
import laucher.joy.com.JOYLauncher.activity.ApplicationActivity;
import laucher.joy.com.JOYLauncher.entity.AdBean;
import laucher.joy.com.JOYLauncher.utils.NetUtil;
import laucher.joy.com.JOYLauncher.utils.PackgeManagerInfo;
import laucher.joy.com.JOYLauncher.utils.ShadowCallBack;
import laucher.joy.com.JOYLauncher.utils.UIhelper;

/**
 * Created by mosaic on 15-12-17.
 */
public class ApplicationFragment extends CommonFragment
        implements View.OnClickListener, View.OnFocusChangeListener, ShadowCallBack {
    final String TAG = "RecommendFragment";
    private String pkg = "com.jixun.getappdemo";
    private View view;
    boolean bIsrun = false;
    private Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_application, container, false);
        initView();
        mHandler = new FragmentHandler();
        return view;
    }

    public void initView() {
        fls[0] = (FrameLayout) (view.findViewById(R.id.application_fl_1));
        fls[1] = (FrameLayout) (view.findViewById(R.id.application_fl_2));
        fls[2] = (FrameLayout) (view.findViewById(R.id.application_fl_3));
        fls[3] = (FrameLayout) (view.findViewById(R.id.application_fl_5));
        fls[4] = (FrameLayout) (view.findViewById(R.id.application_fl_4));
        fls[5] = (FrameLayout) (view.findViewById(R.id.application_fl_6));
        posts[0] = ((ImageView) view.findViewById(R.id.application_poster_1));
        posts[1] = ((ImageView) view.findViewById(R.id.application_poster_2));
        posts[2] = ((ImageView) view.findViewById(R.id.application_poster_3));
        posts[3] = ((ImageView) view.findViewById(R.id.application_poster_5));
        posts[4] = ((ImageView) view.findViewById(R.id.application_poster_4));
        posts[5] = ((ImageView) view.findViewById(R.id.application_poster_6));
        backGrounds[0] = ((ImageView) view.findViewById(R.id.application_bg_1));
        backGrounds[1] = ((ImageView) view.findViewById(R.id.application_bg_2));
        backGrounds[2] = ((ImageView) view.findViewById(R.id.application_bg_3));
        backGrounds[3] = ((ImageView) view.findViewById(R.id.application_bg_5));
        backGrounds[4] = ((ImageView) view.findViewById(R.id.application_bg_4));
        backGrounds[5] = ((ImageView) view.findViewById(R.id.application_bg_6));
        for (int i = 0; i < 6; i++) {
            posts[i].setOnClickListener(this);
            posts[i].setOnFocusChangeListener(this);
            backGrounds[i].setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.application_poster_1:
                if (PackgeManagerInfo.getPackageName("com.ucbrowser.tv", getActivity())) {
                    PackgeManagerInfo.getIntent("com.ucbrowser.tv", "com.ucweb.tv.activity.MainActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/UCliulanqiTVban.html");
                }

                break;
            case R.id.application_poster_2:
                if (PackgeManagerInfo.getPackageName("com.tv.ghost", getActivity())) {
                    PackgeManagerInfo.getIntent("com.tv.ghost", "com.tv.ghost.GhostAct", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/dianshibibei.html");
                }

                break;
            case R.id.application_poster_3:
                if (PackgeManagerInfo.getPackageName("com.jixun.getappdemo", getActivity())) {
                    PackgeManagerInfo.getIntent("com.jixun.getappdemo", "com.jixun.getappdemo.TestActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://www.remote3c.com/file/movies/wsApk/GetAppDemo.apk");
                }

                break;
            case R.id.application_poster_5:
                Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                break;

            case R.id.application_poster_4:

                if (PackgeManagerInfo.getPackageName("com.letv.dpkk", getActivity())) {
                    PackgeManagerInfo.getIntent("com.letv.dpkk", "com.letv.screenui.activity.KanKanActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/duopingkankan.html");
                }

                break;
            case R.id.application_poster_6:
                if (ads != null && ads.size() > 0) {
                    Log.v(TAG, "url=" + (ads.get(pageIndex)).getWebUrl());
                    UIhelper.jumpToWeb(getActivity(), ((AdBean) ads.get(pageIndex)).getWebUrl());
                } else {
                    UIhelper.jumpToWeb(getActivity(), "http://app.shafa.com/apk/dianshimaoshipin.html");
                }
                break;

            default:
                return;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.application_poster_1:
                if (hasFocus) {
                    showOnFocusAnimation(0);
                    return;
                } else {
                    showLooseFocusAinimation(0);
                }
                break;

            case R.id.application_poster_2:
                if (hasFocus) {
                    showOnFocusAnimation(1);
                    return;
                } else {
                    showLooseFocusAinimation(1);
                }
                break;

            case R.id.application_poster_3:
                if (hasFocus) {
                    showOnFocusAnimation(2);
                    return;
                } else {
                    showLooseFocusAinimation(2);
                }
                break;

            case R.id.application_poster_5:
                if (hasFocus) {
                    showOnFocusAnimation(3);
                    return;
                } else {
                    showLooseFocusAinimation(3);
                }
                break;

            case R.id.application_poster_4:
                if (hasFocus) {
                    showOnFocusAnimation(4);
                    return;
                } else {
                    showLooseFocusAinimation(4);
                }
                break;

            case R.id.application_poster_6:
                if (hasFocus) {
                    showOnFocusAnimation(5);
                    return;
                } else {
                    showLooseFocusAinimation(5);
                }
                break;

            default:
                break;
        }
    }

    public void startAnimation() {
        if ((NetUtil.isNetworkConnected()) && (ads != null)) {
            bIsrun = true;
            if (mHandler != null)
                mHandler.sendEmptyMessage(0);
        }
    }

    public void stopAnimation() {
        bIsrun = false;
    }

    class FragmentHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.i(TAG, "handler is run1111");
                    if (ads != null && ads.size() > 0) {
                        Log.i(TAG, "handler is run222");
                        if (bIsrun) {
                            Log.i(TAG, "handler is run333");
                            ImageLoader.getInstance().displayImage(((AdBean) ads.get(pageIndex)).getPicUrl(), posts[5]);
                            pageIndex = (1 + pageIndex);
                            if (pageIndex == ads.size()) {
                                pageIndex = 0;
                            }
                            mHandler.sendEmptyMessageDelayed(0, MyApp.mAdTime);
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    ;

    private boolean getPackageName(String packageName) {
        final PackageManager packageManager = getActivity().getPackageManager();
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

}

