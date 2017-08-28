package com.example.administrator.imageloader.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/28.
 */
public class RequstConnection {

    private ImageParms parms;
    private Handler mHandler;

    public RequstConnection(ImageParms parms, Handler mHandler) {
        this.parms = parms;
        this.mHandler = mHandler;
    }

    public void request() {
        try {
            URL url = new URL(parms.getUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            parms.setBitmap(bitmap);
            ImageLoader.getInstance().setBitmapCache(parms.getUrl(), bitmap);
            Message message = mHandler.obtainMessage();
            message.what = GlobalDefine.HANDLER_MSG_SUCCESS;
            message.obj = parms;
            mHandler.sendMessage(message);
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(GlobalDefine.HANDLER_MSG_FAIL);
        } catch (IOException e) {
            mHandler.sendEmptyMessage(GlobalDefine.HANDLER_MSG_FAIL);
            e.printStackTrace();
        }
    }

}
