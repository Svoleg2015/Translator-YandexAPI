package com.example.translatoryandexapi;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/api/v1.5/tr.json/translate")
    Call<MyResponse> getText(@Query("key")String key,
                             @Query("text")String text,
                             @Query("lang") String lang,
                             @Query("format") String format
                             );

}
