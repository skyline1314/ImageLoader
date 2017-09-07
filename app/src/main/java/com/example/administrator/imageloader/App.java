package com.example.administrator.imageloader;

import android.app.Application;

import com.example.administrator.imageloader.sdk.diskcache.cachemanager.DiskCacheManager;
import com.example.administrator.imageloader.sdk.ImageLoader;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/8/28.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        LeakCanary.install(this);
        DiskCacheManager.getInstance().open(this);
        ImageLoader.getInstance().init(this);
        super.onCreate();
    }
}
