package com.example.myfirstapp;

import com.example.myfirstapp.models.CheckList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrelloApi {

    @GET("businesses/search")
    Call<CheckList> getChecklists(
            @Query("task") String task,
            @Query("term") String term
    );
}
