package com.allattentionhere.autoplayvideos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

public class AAH_Utils {

    private static SharedPreferences sharedPrefs;

    protected static void initialize(Context context) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    protected static String getString(Context context, String key) {
        if (sharedPrefs == null) {
            initialize(context);
        }
        return sharedPrefs.getString(key, null);
    }

    protected static void saveString(Context context, String key, String value) {
        if (sharedPrefs == null) {
            initialize(context);
        }
        sharedPrefs.edit().putString(key, value).apply();
    }

    protected static void remove(Context context, String key) {
        if (sharedPrefs == null) {
            initialize(context);
        }
        sharedPrefs.edit().remove(key).apply();
    }

    public static boolean isVideoDownloaded(Context c, String url) {
        return getString(c, url) != null;

    }

}
