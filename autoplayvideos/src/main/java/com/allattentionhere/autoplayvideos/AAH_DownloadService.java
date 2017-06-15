package com.allattentionhere.autoplayvideos;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by krupenghetiya on 03/05/17.
 */

public class AAH_DownloadService extends IntentService {

    public AAH_DownloadService() {
        super(AAH_DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra("url");
        String path = intent.getStringExtra("path");

        if (!TextUtils.isEmpty(url)) {
            downloadData(url,path);
        }
        this.stopSelf();
    }

    private boolean downloadData(String requestUrl,String path) {
        try {
            File rootDir = new File(path);
            if (!rootDir.exists()) {
                rootDir.mkdir();
            }

            File rootFile = new File(rootDir, new Date().getTime() + ".mp4");
            if (!rootFile.exists()) {
                rootFile.createNewFile();
            }

            URL url = new URL(requestUrl);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.connect();
            FileOutputStream f = new FileOutputStream(rootFile);
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
            AAH_Utils.saveString(getApplicationContext(), requestUrl, rootFile.getAbsolutePath());
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}