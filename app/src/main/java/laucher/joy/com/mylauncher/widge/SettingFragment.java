package laucher.joy.com.mylauncher.widge;

import android.content.ComponentName;
import android.content.Intent;
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

import laucher.joy.com.mylauncher.MyApp;
import laucher.joy.com.mylauncher.R;
import laucher.joy.com.mylauncher.activity.HomeActivity;
import laucher.joy.com.mylauncher.activity.WeatherSettingActivity;
import laucher.joy.com.mylauncher.entity.AdBean;
import laucher.joy.com.mylauncher.utils.NetUtil;
import laucher.joy.com.mylauncher.utils.PackgeManagerInfo;
import laucher.joy.com.mylauncher.utils.ShadowCallBack;
import laucher.joy.com.mylauncher.utils.UIhelper;

/**
 * Created by mosaic on 15-12-17.
 */
public class SettingFragment extends CommonFragment
        implements View.OnClickListener, View.OnFocusChangeListener, ShadowCallBack {
    final String TAG = "RecommendFragment";

    private View view;
    boolean bIsrun = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_setting, container, false);
        initView();
        mHandler = new FragmentHandler();
        return view;
    }

    public void initView() {
        fls[0] = (FrameLayout) (view.findViewById(R.id.set_fl_1));
        fls[1] = (FrameLayout) (view.findViewById(R.id.set_fl_2));
        fls[2] = (FrameLayout) (view.findViewById(R.id.set_fl_3));
        fls[3] = (FrameLayout) (view.findViewById(R.id.set_fl_4));
        fls[4] = (FrameLayout) (view.findViewById(R.id.set_fl_5));
        posts[0] = ((ImageView) view.findViewById(R.id.setting_sys));
        posts[1] = ((ImageView) view.findViewById(R.id.weather_settings));
        posts[2] = ((ImageView) view.findViewById(R.id.about_us));
        posts[3] = ((ImageView) view.findViewById(R.id.clean_key));
        posts[4] = ((ImageView) view.findViewById(R.id.vip_center));
        backGrounds[0] = ((ImageView) view.findViewById(R.id.set_bg_1));
        backGrounds[1] = ((ImageView) view.findViewById(R.id.set_bg_2));
        backGrounds[2] = ((ImageView) view.findViewById(R.id.set_bg_3));
        backGrounds[3] = ((ImageView) view.findViewById(R.id.set_bg_4));
        backGrounds[4] = ((ImageView) view.findViewById(R.id.set_bg_5));
        for (int i = 0; i < 5; i++) {
            posts[i].setOnClickListener(this);
            posts[i].setOnFocusChangeListener(this);
            backGrounds[i].setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_sys:

                if (PackgeManagerInfo.getPackageName("com.mbx.settingsmbox", getActivity())) {
                    Intent i = new Intent();
                    PackgeManagerInfo.getIntent("com.mbx.settingsmbox", "com.mbx.settingsmbox.SettingsMboxActivity", getActivity());

                } else {
                    Toast.makeText(getActivity(), "请提供apk", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.weather_settings:
               /* if (PackgeManagerInfo.getPackageName("com.hitutu.weathertv", getActivity())) {
                    PackgeManagerInfo.getIntent("com.hitutu.weathertv", "com.hitutu.weathertv.MainActivity", getActivity());
                } else {

                   // PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/mojitianqi.html");http://www.hitutu.com/weather.html
                    PackgeManagerInfo.startIntent(getActivity(), "http://www.hitutu.com/weather.html");
                }*/
                Intent localIntent = new Intent(getContext(), WeatherSettingActivity.class);
                startActivity(localIntent);

                break;
            case R.id.about_us:
                Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clean_key:

                if (PackgeManagerInfo.getPackageName("com.sparrowtools.videoacceleration", getActivity())) {
                    Intent i = new Intent();
                    PackgeManagerInfo.getIntent("com.sparrowtools.videoacceleration", "com.sparrowtools.videoacceleration.MainActivity", getActivity());

                } else {
                    Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.vip_center:
                Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                break;
            default:
                return;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        ;
        switch (v.getId()) {

            case R.id.setting_sys:
                if (hasFocus) {
                    showOnFocusAnimation(0);
                    return;
                } else {
                    showLooseFocusAinimation(0);
                }
                break;
            case R.id.weather_settings:
                if (hasFocus) {
                    showOnFocusAnimation(1);
                    return;
                } else {
                    showLooseFocusAinimation(1);
                }
                break;
            case R.id.about_us:
                if (hasFocus) {
                    showOnFocusAnimation(2);
                    return;
                } else {
                    showLooseFocusAinimation(2);
                }
                break;
            case R.id.clean_key:
                if (hasFocus) {
                    showOnFocusAnimation(3);
                    return;
                } else {
                    showLooseFocusAinimation(3);
                }
                break;
            case R.id.vip_center:
                if (hasFocus) {
                    showOnFocusAnimation(4);
                    return;
                } else {
                    showLooseFocusAinimation(4);
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
                            ImageLoader.getInstance().displayImage(((AdBean) ads.get(pageIndex)).getPicUrl(), posts[4]);
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
}

