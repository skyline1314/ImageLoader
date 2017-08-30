package com.example.administrator.imageloader;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.example.administrator.imageloader.diskcache.cachemanager.DiskCacheManager;
import com.example.administrator.imageloader.sdk.ImageLoader;
import com.example.administrator.imageloader.sdk.ImageParms;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/8/28.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        LeakCanary.install(this);
        DiskCacheManager.getInstance().open(this);
        super.onCreate();
    }
}
