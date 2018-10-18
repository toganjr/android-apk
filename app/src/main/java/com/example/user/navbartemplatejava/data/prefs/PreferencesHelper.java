package com.example.user.navbartemplatejava.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.user.navbartemplatejava.data.User;
import com.example.user.navbartemplatejava.data.UserInspectorCode;
import com.google.gson.Gson;

public class PreferencesHelper {
    private SharedPreferences mPrefs;

    private static final String PREFERENCES_NAME = "PREFERENCES_INKA";
    private static final String PREFERENCES_KEY_USER_SIGN_IN = "PREFERENCES_KEY_USER_SIGN_IN";
    private static final String PREFERENCES_KEY_USER_IS_SIGN_IN = "PREFERENCES_KEY_USER_IS_SIGN_IN";
    private static final String PREFERENCES_KEY_USER_SIGN_IN_TOKEN = "PREFERENCES_KEY_USER_SIGN_IN_TOKEN";
    private static final String PREFERENCES_KEY_USER_ID = "PREFERENCES_KEY_USER_ID";

    public PreferencesHelper(Context context) {
        this.mPrefs = context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setUserSignInToken(String token){
        mPrefs.edit().putString(PREFERENCES_KEY_USER_SIGN_IN_TOKEN, "Bearer " + token).apply();
    }

    public String getUserSignInToken(){
        return mPrefs.getString(PREFERENCES_KEY_USER_SIGN_IN_TOKEN, "");
    }

    public void setUserSignIn(User user){
        mPrefs.edit().putString(PREFERENCES_KEY_USER_SIGN_IN, new Gson().toJson(user)).apply();
    }

    public User getUserSignIn(){
        String user = mPrefs.getString(PREFERENCES_KEY_USER_SIGN_IN, "");
        return new Gson().fromJson(user, User.class);
    }

    public void setUserID(Integer userID){
        mPrefs.edit().putInt(PREFERENCES_KEY_USER_ID,  userID).apply();
    }

    public Integer getUserID(){
        return mPrefs.getInt(PREFERENCES_KEY_USER_ID, 0);
    }

    public boolean isSignIn(){
        return mPrefs.getBoolean(PREFERENCES_KEY_USER_IS_SIGN_IN, false);
    }

    public void setUserIsSignIn(boolean signIn){
        mPrefs.edit().putBoolean(PREFERENCES_KEY_USER_IS_SIGN_IN, signIn).apply();
    }
}
