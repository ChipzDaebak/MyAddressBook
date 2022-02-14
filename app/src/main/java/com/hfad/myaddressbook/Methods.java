package com.hfad.myaddressbook;

import com.hfad.myaddressbook.model.BaseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET("?nim=2301878624&nama=Vincent%20Ciptadi")
    Call<BaseModel> getAllData();
}
