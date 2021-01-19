package com.example.testbankapi;

import com.example.testbankapi.pojo.CurrencyInfo;

import retrofit2.Call;
import retrofit2.http.GET;

interface MonoBankApi {
    @GET("bank/currency")
    Call<CurrencyInfo[]> getCurrencyArray();
}
