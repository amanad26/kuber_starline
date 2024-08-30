package com.oms.kuberstarline.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.oms.kuberstarline.activities.LoginActivity;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.ProfileModel;

public class Session {


    private static final String PREF_NAME = "Rapidine_pref2";

    private final String warehouse = "warehouse";
    private final String profile = "profile";
    private Context _context;
    private SharedPreferences Rapidine_pref;
    private SharedPreferences.Editor editor;

    private final String isLogin = "isLogin ";


    public Session(Context context) {
        this._context = context;
        Rapidine_pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = Rapidine_pref.edit();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return Rapidine_pref.getBoolean(isLogin, false);
    }


    public void saveUserModel(LoginModel user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(warehouse, userJson);
        editor.apply();
    }

    public void saveProfile(ProfileModel.Profile user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(profile, userJson);
        editor.apply();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(isLogin, isLoggedIn);
        editor.commit();
    }

    public LoginModel getUserModel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString(warehouse, null);
        return gson.fromJson(userJson, LoginModel.class);
    }

    public ProfileModel.Profile getProfile(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString(profile, null);
        return gson.fromJson(userJson, ProfileModel.Profile.class);
    }

    public void logout() {
        editor.clear();
        editor.apply();
        Intent showLogin = new Intent(_context, LoginActivity.class);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        showLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(showLogin);
    }

}
