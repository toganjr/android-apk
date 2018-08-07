package com.example.user.navbartemplatejava.data.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface AuthInterface {
    @POST("api/login")
    Call<LoginResponse> signIn(@Field("nip") String nip, @Field("password") String password);


}
