package com.example.testbankapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void onClick(View view) {


        NetworkService.getInstance()
                .getJSONApi()
                .getCurrencyArray()
                .enqueue(new Callback<CurrencyMonoBank[]>() {
                    @Override
                    public void onResponse(@NonNull Call<CurrencyMonoBank[]> call, @NonNull Response<CurrencyMonoBank[]> response) {
                        CurrencyMonoBank[] post = response.body();

                        if (post != null) {
                            for (CurrencyMonoBank currencyMonoBank : post) {
                                textView.append("------------------------------------------\n");
                                textView.append(currencyMonoBank.getCurrencyCodeA() + "\n");
                                textView.append(currencyMonoBank.getCurrencyCodeB() + "\n");

                                Date date = new Date(currencyMonoBank.getDate() * 1000);

                                DateFormat dateFormat = DateFormat.getTimeInstance();
                                dateFormat.setTimeZone(TimeZone.getTimeZone("gmt+2"));
                                String gmtTime = dateFormat.format(date);


                                textView.append(gmtTime.toString() + "\n");

                                textView.append(currencyMonoBank.getRateBuy() + "\n");
                                textView.append(currencyMonoBank.getRateCross() + "\n");
                                textView.append(currencyMonoBank.getRateSell() + "\n");
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CurrencyMonoBank[]> call, @NonNull Throwable t) {
                        textView.append("Error occurred while getting request!");
                        t.printStackTrace();
                    }

                });
    }
}