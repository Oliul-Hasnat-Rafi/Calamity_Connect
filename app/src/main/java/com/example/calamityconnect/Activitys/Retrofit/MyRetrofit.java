package com.example.calamityconnect.Activitys.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class MyRetrofit {
//    static Retrofit retrofit = null;
//
//    public static Retrofit getRetrofit(){
//
//        if (retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl("https://zirwabd.000webhostapp.com/image/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return retrofit;
//    }
//}

public class MyRetrofit {
    private static final String BASE_URL = "https://zirwabd.000webhostapp.com/image/"; // Replace with your API base URL

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

