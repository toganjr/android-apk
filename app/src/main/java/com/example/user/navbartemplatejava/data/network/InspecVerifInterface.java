package com.example.user.navbartemplatejava.data.network;

import com.example.user.navbartemplatejava.data.network.response.AddInspecFormResponse;
import com.example.user.navbartemplatejava.data.network.response.BrowseNcrResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InspecVerifInterface {
    @GET("api/inspector_verification")
    Call<BrowseNcrResponse> browseInspec(@Header("Authorization") String token);
    @FormUrlEncoded
    @POST("api/inspector_verification")
    Call<Void> addInspec(@Field("ncr_id") Integer id,
                         @Field("verification_description") String verificationDescription,
                         @Field("verification_result_id") Integer resultId,
                         @Header("Authorization") String token);

    @GET("api/inspector_verification/create")
    Call<AddInspecFormResponse> addInspecForm(@Header("Authorization") String token);
}
