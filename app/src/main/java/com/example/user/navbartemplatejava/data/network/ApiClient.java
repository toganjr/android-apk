package com.example.user.navbartemplatejava.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .registerTypeAdapter(Date.class, new GsonDateDeserializer())
//                    .create();
     Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
     String BASE_URL = "http://192.168.137.1:8000/";
            return retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
