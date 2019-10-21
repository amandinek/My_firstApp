package com.example.myfirstapp.network;



import com.example.myfirstapp.models.YelpEventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("businesses/search")
    Call<YelpEventResponse> getPublicEvents(
            @Query("location") String location,
            @Query("term") String term
    );
}
