package com.example.administrator.imageloader.sdk.diskcache.cachemanager;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by sujizhong on 16/5/16.
 */
public abstract class BaseCacheManager {

    public final void IOclose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
