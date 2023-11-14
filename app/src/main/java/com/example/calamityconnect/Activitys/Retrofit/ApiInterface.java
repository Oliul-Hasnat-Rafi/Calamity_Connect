package com.example.calamityconnect.Activitys.Retrofit;

import com.example.calamityconnect.Activitys.model.Donation;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("donationapi.php") // Replace "donations" with the actual endpoint in your API
    Call<List<Donation>> getDonations();
}

