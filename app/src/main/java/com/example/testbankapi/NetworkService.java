package com.example.testbankapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkService {
    private static NetworkService mNetworkService;

    private static final String BASE_URL = "https://api.monobank.ua";
    private Retrofit mRetrofit;

    public NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if(mNetworkService == null){
            mNetworkService = new NetworkService();
        }
        return mNetworkService;
    }

    public MonoBankApi getJSONApi(){
        return mRetrofit.create(MonoBankApi.class);
    }
}
