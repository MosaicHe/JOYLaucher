package laucher.joy.com.mylauncher.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


import laucher.joy.com.mylauncher.R;
import laucher.joy.com.mylauncher.adpter.AllFragmentAdapter;
import laucher.joy.com.mylauncher.entity.AdBean;
import laucher.joy.com.mylauncher.utils.PackgeManagerInfo;
import laucher.joy.com.mylauncher.utils.ShadowCallBack;
import laucher.joy.com.mylauncher.utils.SharedPreferenceUtil;
import laucher.joy.com.mylauncher.utils.WeatherUtil;
import laucher.joy.com.mylauncher.widge.ApplicationFragment;
import laucher.joy.com.mylauncher.widge.CommonFragment;
import laucher.joy.com.mylauncher.widge.JoyFragment;
import laucher.joy.com.mylauncher.widge.MovieFragment;
import laucher.joy.com.mylauncher.widge.RecommendFragment;
import laucher.joy.com.mylauncher.widge.ServiceFragment;
import laucher.joy.com.mylauncher.widge.SettingFragment;

/**
 * Created by mosaic on 15-12-14.
 */
public class HomeActivity extends FragmentActivity {

    final String TAG = "HomeActivity";

    final static int UPDATE_WEATHER = 4;

    private RadioGroup titleGroup;
    private ViewPager centerPager;
    private ArrayList<AdBean> allAds = null;
    private int focus;

    private MainHandler handler;

    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    private ImageView netIcon;
    private TextView weatherInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new MovieFragment());
        fragmentList.add(new JoyFragment());
        fragmentList.add(new ServiceFragment());
        fragmentList.add(new ApplicationFragment());
        fragmentList.add(new SettingFragment());

        allAds = getIntent().getParcelableArrayListExtra("ads");
        if (allAds != null) {
            classifyAds();
        }
        handler = new MainHandler(this);
        weatherInfo = ((TextView)findViewById(R.id.weather_text));
        initView();
        initListener();
        centerPager.setCurrentItem(0);
        startAnimation(0);
        this.focus = 0;

    }

    private void initWeatherInfo(final String cityCode)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                String str = WeatherUtil.getWeatherInfo(HomeActivity.this, cityCode);
                if (str != null)
                {
                    Message message = new Message();
                    message.what = UPDATE_WEATHER;
                    message.obj = str;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        centerPager.setCurrentItem(focus);//设置当前显示标签页为第一页
        initWeatherInfo(SharedPreferenceUtil.getString("city_id", "101280601"));
        startAnimation(focus);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAnimation();
    }

    private void initView() {

        netIcon = ((ImageView)findViewById(R.id.net_icon));
        titleGroup = ((RadioGroup) findViewById(R.id.title_group));
        titleGroup.requestFocus();

        centerPager = (ViewPager) findViewById(R.id.main_layout_pager);
        centerPager.setAdapter(new AllFragmentAdapter(getSupportFragmentManager(), fragmentList));
    }


    private void classifyAds() {
        for (int i = 0; i < allAds.size(); i++) {
            switch (((AdBean) this.allAds.get(i)).getType()) {
                case 1:
                    ((CommonFragment) fragmentList.get(0)).setAd((AdBean) allAds.get(i));
                    break;

                case 2:
                    ((CommonFragment) fragmentList.get(1)).setAd((AdBean) allAds.get(i));
                    break;

                case 3:
                    ((CommonFragment) fragmentList.get(2)).setAd((AdBean) allAds.get(i));
                    break;

                case 4:
                    ((CommonFragment) fragmentList.get(3)).setAd((AdBean) allAds.get(i));
                    break;

                case 5:
                    ((CommonFragment) fragmentList.get(4)).setAd((AdBean) allAds.get(i));
                    break;

                default:
                    break;
            }
        }
    }


    public void startAnimation(int position) {

        if (position < fragmentList.size())
            ((ShadowCallBack) fragmentList.get(position)).startAnimation();
        return;
    }

    private void stopAnimation() {
        for (int i = 0; i < fragmentList.size(); i++) {
            Log.i(TAG, "position, stopAnimation()-->" + i);
            ((ShadowCallBack) fragmentList.get(i)).stopAnimation();
        }
        return;
    }

    public void initListener() {
        centerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position < titleGroup.getChildCount()) {
                    ((RadioButton) titleGroup.getChildAt(position)).setChecked(true);
                    stopAnimation();
                    startAnimation(position);
                }else {
                    return;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    int i = ((Integer) v.getTag()).intValue();
                    centerPager.setCurrentItem(i, true);
                    focus = i;
                }
            }
        };
        for (int i = 0; i < titleGroup.getChildCount(); i++) {
            View localView = titleGroup.getChildAt(i);
            localView.setTag(Integer.valueOf(i));
            localView.setOnFocusChangeListener(focusChangeListener);
            localView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = ((Integer) v.getTag()).intValue();
                    centerPager.setCurrentItem(i, true);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        java.lang.System.gc();
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }




    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        if(keyCode==KeyEvent.KEYCODE_HOME)
        {
            return true;
        }

    return super.onKeyDown(keyCode, event);
    }

    public static class  MainHandler extends Handler {

        private final WeakReference<HomeActivity> m_weakref;

        public MainHandler(HomeActivity mHomeActivity) {
            this.m_weakref = new WeakReference(mHomeActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeActivity localAct=(HomeActivity)this.m_weakref.get();
            switch (msg.what)
            {
                case 1 :
                    break;

                case HomeActivity.UPDATE_WEATHER:
                    localAct.weatherInfo.setText((String)msg.obj);
                    break;

                default:
                    break;

            }

            super.handleMessage(msg);
        }
    }
}
