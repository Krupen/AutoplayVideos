package com.allattentionhere.autoplayvideossample;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * Created by krupenghetiya on 08/10/17.
 */

public class MyApplication extends Application {
    private static HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy() {
        return proxy;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       proxy = new HttpProxyCacheServer(this);
    }
}
