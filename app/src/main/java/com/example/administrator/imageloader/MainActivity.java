package com.example.administrator.imageloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.imageloader.diskcache.cachemanager.DiskCacheManager;
import com.example.administrator.imageloader.sdk.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        ImageLoader.getInstance().with(img, "http://bpic.588ku.com/back_pic/03/70/72/5257b6c12d89875.jpg!ww800");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
    }
}
