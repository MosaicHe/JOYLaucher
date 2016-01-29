package laucher.joy.com.mylauncher.widge;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import laucher.joy.com.mylauncher.MyApp;
import laucher.joy.com.mylauncher.R;
import laucher.joy.com.mylauncher.entity.AdBean;
import laucher.joy.com.mylauncher.utils.NetUtil;
import laucher.joy.com.mylauncher.utils.ScaleAnimEffect;

/**
 * Created by mosaic on 15-12-17.
 */
public class CommonFragment extends Fragment
{
    private static final String TAG = "CommnFragment";

    protected ImageView[] backGrounds = new ImageView[6];
    protected FrameLayout[] fls = new FrameLayout[6];
    protected ImageView[] posts = new ImageView[6];

    protected ArrayList<AdBean> ads =  new ArrayList<>();
    private ScaleAnimEffect animEffect = new ScaleAnimEffect();

    protected int pageIndex=0;



    Handler mHandler;


    protected void showOnFocusAnimation(final int num)
    {
        this.fls[num].bringToFront();
        animEffect.setAttributs(1.0F, 1.0366F, 1.0F, 1.0366F, 100L);
        Animation mAnimation = this.animEffect.createAnimation();
        this.posts[num].startAnimation(mAnimation);
        mAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation paramAnonymousAnimation)
            {
                backGrounds[num].startAnimation(animEffect.alphaAnimation(0.0F, 1.0F, 100L, 0L));
                backGrounds[num].setVisibility(View.VISIBLE);
            }

            public void onAnimationRepeat(Animation paramAnonymousAnimation) {}

            public void onAnimationStart(Animation paramAnonymousAnimation) {}
        });
    }

    protected void showLooseFocusAinimation(int num)
    {
        animEffect.setAttributs(1.1F, 1.0F, 1.1F, 1.0F, 100L);
        posts[num].startAnimation(animEffect.createAnimation());
        backGrounds[num].setVisibility(View.GONE);
    }

    public void setAds(ArrayList<AdBean> ads) {
        this.ads = ads;
    }

    public void setAd(AdBean ad) {
        this.ads.add(ad);
    }



}
