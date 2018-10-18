package com.example.user.navbartemplatejava.data.network;

import com.example.user.navbartemplatejava.data.network.response.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AuthInterface {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> signIn(@Field("nip") String nip, @Field("password") String password);

    @Multipart
    @PUT("api/profile/{id}")
    Call<LoginResponse> editProfile(
                                    @Field("new_password") String newpassword,
                                    @Field("new_password_confirmation") String newpasswordconfirmation,
                                    @Part MultipartBody.Part image,
                                    @Path("id") Integer id,
                                    @Header("Authorization") String token
                                    );

}
