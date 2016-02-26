package laucher.joy.com.JOYLauncher.widge;

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

import laucher.joy.com.JOYLauncher.MyApp;
import laucher.joy.com.JOYLauncher.R;
import laucher.joy.com.JOYLauncher.entity.AdBean;
import laucher.joy.com.JOYLauncher.utils.NetUtil;
import laucher.joy.com.JOYLauncher.utils.PackgeManagerInfo;
import laucher.joy.com.JOYLauncher.utils.ShadowCallBack;
import laucher.joy.com.JOYLauncher.utils.UIhelper;

/**
 * Created by mosaic on 15-12-17.
 */
public class MovieFragment extends CommonFragment
        implements View.OnClickListener, View.OnFocusChangeListener,ShadowCallBack
{
    final String TAG = "MovieFragment";

    private View view;
    boolean bIsrun = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_movie, container, false);
        initView();
        mHandler =  new FragmentHandler();
        return view;
    }

    @Override
    public void initView() {
        fls[0] = (FrameLayout)(view.findViewById(R.id.movie_fl_0));
        fls[1] = (FrameLayout)(view.findViewById(R.id.movie_fl_1));
        fls[2] = (FrameLayout)(view.findViewById(R.id.movie_fl_2));
        fls[3] = (FrameLayout)(view.findViewById(R.id.movie_fl_3));
        fls[4] = (FrameLayout)(view.findViewById(R.id.movie_fl_4));
        fls[5] = (FrameLayout)(view.findViewById(R.id.movie_fl_6));
        posts[0] = ((ImageView)view.findViewById(R.id.movie_poster_0));
        posts[1] = ((ImageView)view.findViewById(R.id.movie_poster_1));
        posts[2] = ((ImageView)view.findViewById(R.id.movie_poster_2));
        posts[3] = ((ImageView)view.findViewById(R.id.movie_poster_3));
        posts[4] = ((ImageView)view.findViewById(R.id.movie_poster_4));
        posts[5] = ((ImageView)view.findViewById(R.id.movie_poster_6));
        backGrounds[0] = ((ImageView)view.findViewById(R.id.movie_bg_0));
        backGrounds[1] = ((ImageView)view.findViewById(R.id.movie_bg_1));
        backGrounds[2] = ((ImageView)view.findViewById(R.id.movie_bg_2));
        backGrounds[3] = ((ImageView)view.findViewById(R.id.movie_bg_3));
        backGrounds[4] = ((ImageView)view.findViewById(R.id.movie_bg_4));
        backGrounds[5] = ((ImageView)view.findViewById(R.id.movie_bg_6));
        for (int i = 0;i < 6; i++)
        {
            posts[i].setOnClickListener(this);
            posts[i].setOnFocusChangeListener(this);
            backGrounds[i].setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.movie_poster_0:

                if (PackgeManagerInfo.getPackageName("com.aa.aa.aa", getActivity())) {
                    PackgeManagerInfo.getIntent("com.meson.videoplayer", "com.meson.videoplayer.FileList", getActivity());
                } else {
                    Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.movie_poster_1:
                if (PackgeManagerInfo.getPackageName("net.myvst.v2", getActivity())) {
                    PackgeManagerInfo.getIntent("net.myvst.v2", "com.vst.itv52.v1.LancherActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(),"http://app.shafa.com/apk/VSTquanjuhe20.html");
                }

                break;
            case R.id.movie_poster_2:
                //Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                if (PackgeManagerInfo.getPackageName("com.js.litchi", getActivity())) {
                    PackgeManagerInfo.getIntent("com.js.litchi", "com.qiyi.video.ui.home.HomeActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(),"http://app.shafa.com/apk/3Dbobo.html");
                }
                break;
            case R.id.movie_poster_3:
                //Toast.makeText(getActivity(), "建设中", Toast.LENGTH_SHORT).show();
                if (PackgeManagerInfo.getPackageName("com.bobo.splayer", getActivity())) {
                    PackgeManagerInfo.getIntent("com.bobo.splayer", "com.bobo.splayer.modules.mainpage.userInterface.BootActivity", getActivity());
                } else {
                    PackgeManagerInfo.startIntent(getActivity(),"http://app.shafa.com/apk/lizhiTV.html");
                }
                break;
            case R.id.movie_poster_4:
               if (PackgeManagerInfo.getPackageName("com.lekan.tv.kids.activity", getActivity())) {
                    PackgeManagerInfo.getIntent("com.lekan.tv.kids.activity", "com.lekan.tv.kids.activity.SplashActivity", getActivity());
                } else {
                   PackgeManagerInfo.startIntent(getActivity(),"http://app.shafa.com/apk/shiwangeweishenme.html");
                }

                break;
            case R.id.movie_poster_6:
                if ( ads!=null && ads.size() > 0)
                {
                    Log.v(TAG, "url="+(ads.get(pageIndex)).getWebUrl());
                    UIhelper.jumpToWeb(getActivity(), ((AdBean)ads.get(pageIndex)).getWebUrl());
                }
                else
                {
                    UIhelper.jumpToWeb(getActivity(), "http://app.shafa.com/apk/dianshimaoshipin.html");
                }
                break;
            default:
                return;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId())
        {
            case R.id.movie_poster_0:
                if (hasFocus)
                {
                    showOnFocusAnimation(0);
                    return;
                }else {
                    showLooseFocusAinimation(0);
                }
                break;

            case R.id.movie_poster_1:
                if (hasFocus)
                {
                    showOnFocusAnimation(1);
                    return;
                }else {
                    showLooseFocusAinimation(1);
                }
                break;

            case R.id.movie_poster_2:
                if (hasFocus)
                {
                    showOnFocusAnimation(2);
                    return;
                }else {
                    showLooseFocusAinimation(2);
                }
                break;

            case R.id.movie_poster_3:
                if (hasFocus)
                {
                    showOnFocusAnimation(3);
                    return;
                }else {
                    showLooseFocusAinimation(3);
                }
                break;

            case R.id.movie_poster_4:
                if (hasFocus)
                {
                    showOnFocusAnimation(4);
                    return;
                }else {
                    showLooseFocusAinimation(4);
                }
                break;

            case R.id.movie_poster_6:
                if (hasFocus)
                {
                    showOnFocusAnimation(5);
                    return;
                }else {
                    showLooseFocusAinimation(5);
                }
                break;

            default:
                break;
        }
    }

    public void startAnimation()
    {
        if ((NetUtil.isNetworkConnected()) && ( ads != null))
        {
            bIsrun = true;
            if(mHandler!=null)
                mHandler.sendEmptyMessage(0);
        }
    }

    public void stopAnimation()
    {
        bIsrun=false;
    }
    class FragmentHandler extends Handler
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 0:
                    Log.i(TAG, "handler is run1111");
                    if( ads!=null && ads.size() > 0) {
                        Log.i(TAG, "handler is run222");
                        if(bIsrun){
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
    };
}

