package com.example.administrator.imageloader.sdk;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/8/28.
 */
public class ImageParms {
    private View target;
    private String url;
    private Bitmap bitmap;


    public ImageParms(View target, String url) {
        this.target = target;
        this.url = url;
    }

    public View getTarget() {
        return target;
    }

    public void setTarget(View target) {
        this.target = target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBitmapToView() {
        if (target instanceof ImageView && bitmap != null) {
            ((ImageView) target).setImageBitmap(bitmap);
        }
    }
}
