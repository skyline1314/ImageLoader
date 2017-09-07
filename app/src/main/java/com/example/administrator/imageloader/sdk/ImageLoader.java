package com.example.administrator.imageloader.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.imageloader.sdk.diskcache.cachemanager.DiskCacheManager;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/28.
 */
public class ImageLoader {
    private static ImageLoader instance;
    private HashMap<String, SoftReference<Bitmap>> imgCache = new HashMap<>();
    private String Tag = "ImageLoader";
    private Context context;
    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GlobalDefine.HANDLER_MSG_SUCCESS) {
                ImageParms params = (ImageParms) msg.obj;
                params.setBitmapToView();
                cacheMemoryBitmap(params);
                cacheDiskBitmap(params);
            }
            super.handleMessage(msg);
        }
    };


    private ImageLoader() {

    }

    public void init(Context context) {
        this.context = context;
    }


    //singleton
    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    //set bitmap to view by local resource
    public void with(final View target, int resId) {
        Bitmap bitmap = getBitmapFromCache(resId + "");
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            ImageParms parms = new ImageParms(target, resId + "", bitmap);
            cacheMemoryBitmap(parms);
        }

        if (target instanceof ImageView) {
            ((ImageView) target).setImageBitmap(bitmap);
        }
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


    private void cacheMemoryBitmap(ImageParms parms) {
        //set bitmap to memory
        setBitmapCache(parms.getUrl(), parms.getBitmap());
    }

    private void cacheDiskBitmap(ImageParms parms) {
        //set bitmap to disk
        DiskCacheManager.getInstance().put(parms.getUrl(), parms.getBitmap());
    }
}
