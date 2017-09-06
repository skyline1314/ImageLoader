package com.example.administrator.imageloader.sdk;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.administrator.imageloader.diskcache.cachemanager.DiskCacheManager;

/**
 * Created by Administrator on 2017/8/28.
 */
public class WorkRunable implements Runnable {

    private View target;
    private String url;
    private Handler mHandler;

    public WorkRunable(final View target, final String url, Handler mHandler) {
        this.target = target;
        this.url = url;
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        ImageParms parms = new ImageParms(target, url);
        //get bitmap from disk cache
        Bitmap bitmap = DiskCacheManager.getInstance().getAsBitmap(url);
        if (bitmap != null) {
            Message message = mHandler.obtainMessage();
            message.what = GlobalDefine.HANDLER_MSG_SUCCESS;
            message.obj = parms;
            mHandler.sendMessage(message);
            Log.d("ImageLoader","get bitmap from disk cache");
        } else {
            RequstConnection connection = new RequstConnection(parms, mHandler);
            connection.request();
        }
    }
}
