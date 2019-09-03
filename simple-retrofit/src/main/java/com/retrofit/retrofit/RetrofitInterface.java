package com.retrofit.retrofit;

import com.retrofit.domain.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("/remote/test/api")
    Call<User> getRemoteTestApi();
}
