package com.allattentionhere.autoplayvideos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AAH_SharedPrefsUtil {

    private static SharedPreferences sharedPrefs;

    public static void initialize(Context context) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getString(Context context, String key) {
        if (sharedPrefs == null) {
            initialize(context);
        }
        return sharedPrefs.getString(key, null);
    }

    public static void saveString(Context context, String key, String value) {
        if (sharedPrefs == null) {
            initialize(context);
        }
        sharedPrefs.edit().putString(key, value).apply();
    }

    public static void remove(Context context, String key) {
        if (sharedPrefs == null) {
            initialize(context);
        }
        sharedPrefs.edit().remove(key).apply();
    }


}
