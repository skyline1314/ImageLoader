package com.example.administrator.imageloader.sdk;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.imageloader.diskcache.cachemanager.DiskCacheManager;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/28.
 */
public class ImageLoader {
    private static ImageLoader instance;
    private static HashMap<String, SoftReference<Bitmap>> imgCache = new HashMap<>();
    private String Tag = "ImageLoader";

    private static Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GlobalDefine.HANDLER_MSG_SUCCESS) {
                ImageParms params = (ImageParms) msg.obj;
                params.setBitmapToView();
            }
            super.handleMessage(msg);
        }
    };


    private ImageLoader() {

    }

    //singleton
    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    public void with(final View target, final String url) {
        with(target, url, 0);
    }

    //set bitmap to target view
    public void with(final View target, final String url, int defultImgId) {
        Bitmap bitmap = getBitmapFromCache(url);

        if (defultImgId != 0) {
            if (target instanceof ImageView) {
                ((ImageView) target).setImageResource(defultImgId);
            }
        }

        if (bitmap != null) {
            if (target instanceof ImageView) {
                ((ImageView) target).setImageBitmap(bitmap);
            }
        } else {
            //get image bitmap from network
            ThreadPoolManager.getInstance().addExecuteTask(new WorkRunable(target, url, mHander));
            Log.d(Tag, "get bitmap from network,url:" + url);
        }
    }

    //sync in other thread
    public void setBitmapCache(String url, Bitmap bitmap) {
        synchronized (imgCache) {
            imgCache.put(url, new SoftReference<Bitmap>(bitmap));
        }
    }

    // memory  disk
    public Bitmap getBitmapFromCache(String url) {
        SoftReference<Bitmap> reference = imgCache.get(url);
        if (reference != null && reference.get() != null) {
            Log.d(Tag, "get bitmap from memory cache,url:" + url);
            return reference.get();
        }
        return null;
    }

}
