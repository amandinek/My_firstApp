package com.example.myfirstapp.network;



import com.example.myfirstapp.models.GitSearchAppResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/events/#list-public-events")
    Call<GitSearchAppResponse> getPublicEvents(
            @Query("events") String events,
            @Query("list-public-events") String eventsList
    );
}
