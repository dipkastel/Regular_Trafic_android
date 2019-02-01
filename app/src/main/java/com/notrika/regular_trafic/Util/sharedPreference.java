package com.notrika.regular_trafic.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class sharedPreference {

    String PREFS_NAME = "aaa";
    String TokenKey = "TokenKey";
    String scorekey = "scorekey";
    Context context;
    SharedPreferences sharedPreferences;
    Editor editor;

    public sharedPreference(Context context) {
        this.context=context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void setToken(String Token){
        editor.putString(TokenKey,Token);
        editor.commit();
    }
    public String getToken() {
        return sharedPreferences.getString(TokenKey, "");
    }

    public void setScore(Integer score){
        editor.putInt(scorekey,score);
        editor.commit();
    }
    public Integer getScore() {
        return sharedPreferences.getInt(scorekey, 0);
    }

}
