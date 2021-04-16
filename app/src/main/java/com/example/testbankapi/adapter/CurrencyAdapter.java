package com.example.testbankapi.adapter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbankapi.R;
import com.example.testbankapi.pojo.CurrencyInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Adapter for Currency of MonoBank API.
 */
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    /**
     * Tag for Log-s.
     */
    private static final String TAG = CurrencyAdapter.class.getSimpleName();

    /**
     * List of received exchange rates.
     */
    private List<CurrencyInfo> currencyInfoList = new ArrayList<>();

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_row_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyAdapter.CurrencyViewHolder holder, int position) {
        holder.bind(currencyInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyInfoList.size();
    }

    public void setItems(Collection<CurrencyInfo> items) {
        currencyInfoList.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems() {
        currencyInfoList.clear();
        notifyDataSetChanged();
    }

    /**
     * Holder for representing exchange rates of current currency.
     */
    protected static class CurrencyViewHolder extends RecyclerView.ViewHolder {

        private static final String CURRENT_TIME_ZONE = "Europe/Kiev";
        private final TextView textViewCurrencyCodeA;
        private final TextView textViewCurrencyCodeB;
        private final TextView textViewDate;
        private final TextView textViewRateSell;
        private final TextView textViewRateBuy;
        private final TextView textViewRateCross;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCurrencyCodeA = itemView.findViewById(R.id.textViewCurrencyCodeA);
            textViewCurrencyCodeB = itemView.findViewById(R.id.textViewCurrencyCodeB);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewRateSell = itemView.findViewById(R.id.textViewRateSell);
            textViewRateBuy = itemView.findViewById(R.id.textViewRateBuy);
            textViewRateCross = itemView.findViewById(R.id.textViewRateCross);
        }

        public void bind(CurrencyInfo currencyInfo) {
            String currency;
            int currencyCode;

            currencyCode = currencyInfo.getCurrencyCodeA();
            currency = currencyCodeToName(currencyCode);
            textViewCurrencyCodeA.setText(currency);

            currencyCode = currencyInfo.getCurrencyCodeB();
            currency = currencyCodeToName(currencyCode);
            textViewCurrencyCodeB.setText(currency);

            Date date = new Date(TimeUnit.SECONDS.toMillis(currencyInfo.getDate()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.ROOT);
            String gmtTime = dateFormat.format(date);
            textViewDate.setText(gmtTime);

            textViewRateSell.setText(String.valueOf(currencyInfo.getRateSell()));
            textViewRateBuy.setText(String.valueOf(currencyInfo.getRateBuy()));

            float rateCross = currencyInfo.getRateCross();
            if (rateCross == 0 && currencyInfo.getRateSell() != 0 && currencyInfo.getRateBuy() != 0) {
                rateCross = (currencyInfo.getRateSell() + currencyInfo.getRateBuy()) / 2;
            }
            textViewRateCross.setText(String.valueOf(rateCross));
        }

        /**
         * Trying to get currency name by its code
         *
         * @return currency name if SDK> = N otherwise its code
         */
        public String currencyCodeToName(int currencyCode) {
            String currencyName;
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                currencyName = String.valueOf(currencyCode);
                return currencyName;
            }
            currencyName = getCurrencyName(currencyCode);
            return currencyName;
        }

        /**
         * Get currency name by its code
         *
         * @return currency name.
         */
        @RequiresApi(api = Build.VERSION_CODES.N)
        public String getCurrencyName(int currencyCode) {
            Set<Currency> currencies = Currency.getAvailableCurrencies();
            for (Currency currency : currencies) {
                if (currency.getNumericCode() == currencyCode) {
                    return currency.getDisplayName();
                }
            }
            Log.w(TAG, "getCurrencyDisplayName: " + "Currency with numeric code " + currencyCode + " not found");

            if (currencyCode == 933) {
                return "Belarussian Ruble";
            }

            return String.valueOf(currencyCode);
        }
    }
}