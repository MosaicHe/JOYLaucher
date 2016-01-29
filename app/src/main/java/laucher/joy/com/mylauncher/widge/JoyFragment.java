package laucher.joy.com.mylauncher.widge;

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
import laucher.joy.com.mylauncher.entity.AdBean;
import laucher.joy.com.mylauncher.utils.NetUtil;
import laucher.joy.com.mylauncher.utils.PackgeManagerInfo;
import laucher.joy.com.mylauncher.utils.ShadowCallBack;
import laucher.joy.com.mylauncher.utils.UIhelper;

/**
 * Created by mosaic on 15-12-17.
 */
public class JoyFragment extends CommonFragment
        implements View.OnClickListener, View.OnFocusChangeListener, ShadowCallBack {
    final String TAG = "JoyFragment";

    private View view;
    boolean bIsrun = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_joy, container, false);
        initView();
        mHandler = new FragmentHandler();
        return view;
    }

    public void initView() {
        fls[0] = (FrameLayout) (view.findViewById(R.id.joy_fl_1));
        fls[1] = (FrameLayout) (view.findViewById(R.id.joy_fl_0));
        fls[2] = (FrameLayout) (view.findViewById(R.id.joy_fl_2));
        fls[3] = (FrameLayout) (view.findViewById(R.id.joy_fl_3));
        fls[4] = (FrameLayout) (view.findViewById(R.id.joy_fl_4));
        fls[5] = (FrameLayout) (view.findViewById(R.id.joy_fl_5));
        posts[0] = ((ImageView) view.findViewById(R.id.joy_poster_1));
        posts[1] = ((ImageView) view.findViewById(R.id.joy_poster_0));
        posts[2] = ((ImageView) view.findViewById(R.id.joy_poster_2));
        posts[3] = ((ImageView) view.findViewById(R.id.joy_poster_3));
        posts[4] = ((ImageView) view.findViewById(R.id.joy_poster_4));
        posts[5] = ((ImageView) view.findViewById(R.id.joy_poster_5));
        backGrounds[0] = ((ImageView) view.findViewById(R.id.joy_bg_1));
        backGrounds[1] = ((ImageView) view.findViewById(R.id.joy_bg_0));
        backGrounds[2] = ((ImageView) view.findViewById(R.id.joy_bg_2));
        backGrounds[3] = ((ImageView) view.findViewById(R.id.joy_bg_3));
        backGrounds[4] = ((ImageView) view.findViewById(R.id.joy_bg_4));
        backGrounds[5] = ((ImageView) view.findViewById(R.id.joy_bg_5));
        for (int i = 0; i < 6; i++) {
            posts[i].setOnClickListener(this);
            posts[i].setOnFocusChangeListener(this);
            backGrounds[i].setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.joy_poster_1:
                if (PackgeManagerInfo.getPackageName("com.yusi.app.mv4tv", getActivity())) {
                    PackgeManagerInfo.getIntent("com.yusi.app.mv4tv", "com.yusi.app.mv4tv.activity.StartActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/gaoqingjingbaoMV.html");
                }

                break;
            case R.id.joy_poster_0:
                if (PackgeManagerInfo.getPackageName("st.com.xiami", getActivity())) {
                    PackgeManagerInfo.getIntent("st.com.xiami", "com.yunos.xiami.activity.MainActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/xiamiyinleTVban.html");
                }

                break;
            case R.id.joy_poster_2:
                if (PackgeManagerInfo.getPackageName("com.kgeking.client", getActivity())) {
                    PackgeManagerInfo.getIntent("com.kgeking.client", "com.kgeking.client.view.activity.EntryActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/Kgezhiwang.html");
                }

                break;

            case R.id.joy_poster_3:
                if (PackgeManagerInfo.getPackageName("com.lovesport.dama", getActivity())) {
                    PackgeManagerInfo.getIntent("com.lovesport.dama", "com.tvjianshen.tvfit.activity.ShowActivity", getActivity());
                } else {

                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/guangchangwudaquan.html");
                }


                break;

            case R.id.joy_poster_4:
                if (PackgeManagerInfo.getPackageName("com.putaolab.ptgame", getActivity())) {
                    PackgeManagerInfo.getIntent("com.putaolab.ptgame", "com.putaolab.ptgame.MainActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(), "http://app.shafa.com/apk/putaoyouxiting.html");
                }


                break;
            case R.id.joy_poster_5:
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
            case R.id.joy_poster_1:
                if (hasFocus) {
                    showOnFocusAnimation(0);
                    return;
                } else {
                    showLooseFocusAinimation(0);
                }
                break;

            case R.id.joy_poster_0:
                if (hasFocus) {
                    showOnFocusAnimation(1);
                    return;
                } else {
                    showLooseFocusAinimation(1);
                }
                break;

            case R.id.joy_poster_2:
                if (hasFocus) {
                    showOnFocusAnimation(2);
                    return;
                } else {
                    showLooseFocusAinimation(2);
                }
                break;

            case R.id.joy_poster_3:
                if (hasFocus) {
                    showOnFocusAnimation(3);
                    return;
                } else {
                    showLooseFocusAinimation(3);
                }
                break;

            case R.id.joy_poster_4:
                if (hasFocus) {
                    showOnFocusAnimation(4);
                    return;
                } else {
                    showLooseFocusAinimation(4);
                }
                break;

            case R.id.joy_poster_5:
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
}

