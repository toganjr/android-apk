package com.example.user.navbartemplatejava.data.network;

import com.example.user.navbartemplatejava.data.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthInterface {
    @FormUrlEncoded
    @POST("api/activity_login")
    Call<LoginResponse> signIn(@Field("nip") String nip, @Field("password") String password);
}
