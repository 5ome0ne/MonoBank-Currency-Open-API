package com.example.testbankapi;

import retrofit2.Call;
import retrofit2.http.GET;

interface MonoBankApi {
    @GET("bank/currency")
    public Call<CurrencyMonoBank[]> getCurrencyArray();
}
