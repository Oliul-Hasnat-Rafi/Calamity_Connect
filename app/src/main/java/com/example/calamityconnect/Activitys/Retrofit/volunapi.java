package com.example.calamityconnect.Activitys.Retrofit;

import com.example.calamityconnect.Activitys.model.model;
import com.example.calamityconnect.Activitys.model.volunteenmodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface volunapi {
    @GET("volunteerapi.php")
    Call<List<volunteenmodel>> getallvolunlist();
}
