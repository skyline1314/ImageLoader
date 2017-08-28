package com.example.administrator.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.imageloader.sdk.ImageLoader;

public class ImageActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        ImageLoader.getInstance().with(img, "http://bpic.588ku.com/element_origin_min_pic/16/08/03/1757a1b6987152d.jpg", R.mipmap.ic_launcher);
    }
}
