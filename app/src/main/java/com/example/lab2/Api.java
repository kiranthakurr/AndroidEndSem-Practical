package com.example.lab2;

import com.example.lab2.ModelResponse.DataModel;
import com.example.lab2.ModelResponse.DataModel2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("Signup2")
    Call<DataModel> register(
            @Body DataModel dataModel);

    @POST("login2")
    Call<DataModel>login2(
            @Body DataModel dataModel);


    @POST("Signup2")
    Call<DataModel2>Signup2(
            @Body DataModel2 dataModel);
}
