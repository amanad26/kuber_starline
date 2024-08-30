package com.oms.kuberstarline.models;

public class LoginRequestModel {

    String app_key;
    String env_type;
    String mobile;
    String password;
    String device_id;
    String name;
    String email;
    String unique_token;
    String player_id;

    public LoginRequestModel(String app_key, String env_type, String unique_token, String player_id) {
        this.app_key = app_key;
        this.env_type = env_type;
        this.unique_token = unique_token;
        this.player_id = player_id;
    }

    public LoginRequestModel(String app_key, String env_type, String mobile, String password, String name, String email) {
        this.app_key = app_key;
        this.env_type = env_type;
        this.mobile = mobile;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public LoginRequestModel(String app_key, String env_type, String mobile, String password, String device_id) {
        this.app_key = app_key;
        this.env_type = env_type;
        this.mobile = mobile;
        this.password = password;
        this.device_id = device_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnique_token() {
        return unique_token;
    }

    public void setUnique_token(String unique_token) {
        this.unique_token = unique_token;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getEnv_type() {
        return env_type;
    }

    public void setEnv_type(String env_type) {
        this.env_type = env_type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
