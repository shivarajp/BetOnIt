package com.shivaraj.bet.data.source.remote;


import com.shivaraj.bet.data.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubApi {

    @GET("betonit")
    Call<ResponseModel> getData();

}
