package com.example.milanease.data.remote;

import com.example.milanease.data.model.DashboardModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static final NetworkManager ourInstance = new NetworkManager();

    public static NetworkManager getInstance() {
        return ourInstance;
    }

    private static final String ROOT_URL = "http://54.93.33.83:8080";
    private Retrofit retrofit;
    private APIDashboard apiDashboard;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiDashboard = retrofit.create(APIDashboard.class);
    }

    public Call<List<DashboardModel>> getDashboardModel() {
        return apiDashboard.getDashboardJSON();
    }
}
