package com.example.user.navbartemplatejava.data.network;

import com.example.user.navbartemplatejava.data.network.response.AddFormResponse;
import com.example.user.navbartemplatejava.data.network.response.AddNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.BrowseNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.EditFormResponse;
import com.example.user.navbartemplatejava.data.network.response.UpdateNcrResponse;
import com.example.user.navbartemplatejava.data.network.response.UploadFile;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NcrRegInterface {
    @GET("api/ncr_reg")
    Call<BrowseNcrResponse> browseNcr(@Header("Authorization") String token);

    @GET("api/ncr_reg/create")
    Call<AddFormResponse> addNcrForm(@Header("Authorization") String token);

    @GET("api/ncr_reg/{id}/edit")
    Call<EditFormResponse> editNcrForm(@Path("id") Integer id,
                                       @Header("Authorization") String token);
    @Multipart
    @POST("api/ncr_reg")
    Call<AddNcrResponse> addNcr(@Part("process_name") String process_name,
                                @Part("master_project_id") Integer master_project_id,
                                @Part("vendor_name") String vendorName,
                                @Part("acuan_id") Integer acuan_id,
                                @Part("acuan_po") String acuan_po,
                                @Part("description_incompatibility") String description_incompatibility,
                                @Part("incompatibility_category_id") Integer incompatibility_category_id,
                                @Part("person_in_charge") String person_in_charge,
                                @Part MultipartBody.Part image,
                                @Part("disposition_inspector_id") Integer disposition_inspector_id,
                                @Part("completion_target") String completion_target,
                                @Part("lat") Double latitude,
                                @Part("long") Double longitude,
                                @Header("Authorization") String token);


    @Multipart
    @PUT("api/ncr_reg/{id}")
    Call<UpdateNcrResponse> updateNcr(@Part("process_name") String process_name,
                                      @Part("master_project_id") Integer master_project_id,
                                      @Part("vendor_name") String vendorName,
                                      @Part("acuan_id") Integer acuan_id,
                                      @Part("acuan_po") String acuan_po,
                                      @Part("description_incompatibility") String description_incompatibility,
                                      @Part("incompatibility_category_id") Integer incompatibility_category_id,
                                      @Part("person_in_charge") String person_in_charge,
                                      @Part("disposition_inspector_id") Integer disposition_inspector_id,
                                      @Part("completion_target") String completion_target,
                                      @Part("lat") Double latitude,
                                      @Part("long") Double longitude,
                                      @Path("id") Integer id,
                                      @Header("Authorization") String token);

    @Multipart
    @POST
            ("api/ncr_reg/{id}/store_img")
    Call<UploadFile> uploadFile(@Part MultipartBody.Part images,
                                @Path("id") Integer id,
                                @Header("Authorization") String token);

}
