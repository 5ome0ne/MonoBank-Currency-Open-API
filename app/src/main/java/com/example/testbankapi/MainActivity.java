package com.example.testbankapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testbankapi.adapter.CurrencyAdapter;
import com.example.testbankapi.pojo.CurrencyInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MainActivity for representing data from API.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Tag for Log-s.
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private CurrencyAdapter currencyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    /**
     * Initialization RecyclerView.
     */
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewCurrency);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        currencyAdapter = new CurrencyAdapter();
        recyclerView.setAdapter(currencyAdapter);
    }

    /**
     * Handling the mouse click.
     */
    public void onClick(View view) {
        loadAPIData();
    }

    /**
     * GET data using Retrofit and load it to adapter
     */
    private void loadAPIData() {
        NetworkService.getInstance()
                .getJSONApi()
                .getCurrencyArray()
                .enqueue(new Callback<CurrencyInfo[]>() {
                    @Override
                    public void onResponse(@NonNull Call<CurrencyInfo[]> call, @NonNull Response<CurrencyInfo[]> response) {
                        CurrencyInfo[] post = response.body();
                        if (post != null) {
                            List<CurrencyInfo> list = new ArrayList<>(Arrays.asList(post));
                            currencyAdapter.setItems(list);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CurrencyInfo[]> call, @NonNull Throwable t) {
                        Log.w(TAG, "NetworkService.onFailure: " + "Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });
    }
}