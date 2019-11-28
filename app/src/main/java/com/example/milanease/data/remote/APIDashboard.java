package com.example.milanease.data.remote;

import com.example.milanease.data.model.DashboardModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIDashboard {

    @GET("/dashboard")
    Call<List<DashboardModel>> getDashboardJSON();


}
