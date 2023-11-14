package com.example.calamityconnect.Activitys.Retrofit;

import com.example.calamityconnect.Activitys.model.model;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface postapi {
    @GET("json_user_fetch.php")
    Call<List<model>> getallpost();

    @GET("json_user_fetch_slider.php")
    Call<List<model>> getSliderData();
}


