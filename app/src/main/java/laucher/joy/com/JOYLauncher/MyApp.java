package laucher.joy.com.JOYLauncher;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.AsyncTask;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;



public class MyApp extends Application {
    public static String agent;
    public static String agentName;
    private static MyApp application;
    public static String city;
    public static Context context;
    public static boolean isAuthorized = false;
    public static int mAdTime = 5000;
    public static SharedPreferences prefs = null;
    public static String province;
    public static List<String> setTagList;
    public static List<String> tagList;

    public static Context getContext()
    {
        return context;
    }

    public static MyApp getInstance()
    {
        return application;
    }

    public static void initImageLoader(Context paramContext)
    {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inSampleSize = 2;
        DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).decodingOptions(localOptions).build();
        ImageLoaderConfiguration localImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(paramContext).defaultDisplayImageOptions(localDisplayImageOptions).memoryCacheExtraOptions(480, 800).taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR).taskExecutorForCachedImages(AsyncTask.THREAD_POOL_EXECUTOR).threadPoolSize(5).threadPriority(3).tasksProcessingOrder(QueueProcessingType.FIFO).discCacheFileNameGenerator(new Md5FileNameGenerator()).denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2097152)).memoryCacheSize(2097152).discCacheSize(52428800).discCacheFileCount(100).build();
        ImageLoader.getInstance().init(localImageLoaderConfiguration);
    }

    public static boolean isAudioNormal()
    {
        return ((AudioManager)context.getSystemService(AUDIO_SERVICE)).getStreamVolume(3) != 0;
    }

    public static boolean isAuthorized()
    {
        return isAuthorized;
    }

    public static void setAuthorized(boolean paramBoolean)
    {
        isAuthorized = paramBoolean;
    }

    public static void setContext(Context paramContext)
    {
        context = paramContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        application = this;
        prefs = getSharedPreferences("joylauncher", 0);
        initImageLoader(getApplicationContext());
        tagList = new ArrayList();
        setTagList = new ArrayList();
    }
}
