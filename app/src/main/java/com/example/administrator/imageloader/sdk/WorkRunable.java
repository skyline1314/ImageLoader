package com.example.administrator.imageloader.sdk;

import android.os.Handler;
import android.view.View;

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
        RequstConnection connection = new RequstConnection(new ImageParms(target, url), mHandler);
        connection.request();
    }
}
