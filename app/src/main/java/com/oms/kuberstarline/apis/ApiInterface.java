package com.oms.kuberstarline.apis;

import com.oms.kuberstarline.models.HomeModel;
import com.oms.kuberstarline.models.LoginModel;
import com.oms.kuberstarline.models.LoginRequestModel;
import com.oms.kuberstarline.models.ProfileModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(BaseUrls.login)
    Call<LoginModel> login(@Body LoginRequestModel request);

    @POST(BaseUrls.register)
    Call<LoginModel> register(@Body LoginRequestModel request);

    @POST(BaseUrls.dashboard)
    Call<HomeModel> dashboard(@Body LoginRequestModel request);

    @POST(BaseUrls.getProfile)
    Call<ProfileModel> getProfile(@Body LoginRequestModel request);


}
