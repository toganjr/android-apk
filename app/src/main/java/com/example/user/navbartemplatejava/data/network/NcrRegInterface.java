package com.example.user.navbartemplatejava.data.network;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface NcrRegInterface {
    @GET("api/ncr_reg")
    Call<BrowseNcrResponse> browseNcr(@Header("Authorization") String token);

    @Multipart
    @POST("api/ncr_reg")
    Call<AddNcrResponse> addNcr(@Part("process_name") String process_name,
                                @Part("description_incompatibility") String description_incompatibility,
                                @Part("division_id") Integer division_id,
                                @Part("master_project_id") Integer master_project_id,
                                @Part("master_product_id") Integer master_product_id,
                                @Part("disposition_inspector_id") Integer disposition_inspector_id,
                                @Part("incompatibility_category_id") Integer incompatibility_category_id,
                                @Part("lat") Double latitude,
                                @Part("long") Double longitude,
                                @Part("acuan_id") Integer acuan_id,
                                @Part("acuan_po") String acuan_po,
                                @Path("file_bukti") List<MultipartBody.Part> images,
                                @Header("Authorization") String token);

    @GET("api/ncr_reg/create")
    Call<AddFormResponse> addNcrForm(@Header("Authorization") String token);
}
