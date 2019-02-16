package com.example.irfannawawi.kamus5milyar.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.irfannawawi.kamus5milyar.R;

/**
 * Created by Irfan Nawawi on 26/01/2019.
 */

public class AppPreference {

    SharedPreferences sharedPreferences;
    Context context;

    public AppPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public Boolean getFirstRun() {
        String key = context.getResources().getString(R.string.app_first_run);
        return sharedPreferences.getBoolean(key, true);
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = context.getResources().getString(R.string.app_first_run);
        editor.putBoolean(key, input);
        editor.commit();
    }
}
