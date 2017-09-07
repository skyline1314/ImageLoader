package com.example.administrator.imageloader.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public RequstConnection(ImageParms parms) {
        this.parms = parms;
    }

    public Bitmap request() {
        try {
            URL url = new URL(parms.getUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            parms.setBitmap(bitmap);
            inputStream.close();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
