package com.example.testbankapi.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyInfo {

    @SerializedName("currencyCodeA")
    @Expose
    private int     currencyCodeA;
    @SerializedName("currencyCodeB")
    @Expose
    private int     currencyCodeB;
    @SerializedName("date")
    @Expose
    private long    date;
    @SerializedName("rateBuy")
    @Expose
    private float   rateBuy;
    @SerializedName("rateSell")
    @Expose
    private float   rateSell;
    @SerializedName("rateCross")
    @Expose
    private float   rateCross;

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(int currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(float rateBuy) {
        this.rateBuy = rateBuy;
    }

    public float getRateSell() {
        return rateSell;
    }

    public void setRateSell(float rateSell) {
        this.rateSell = rateSell;
    }

    public float getRateCross() {
        return rateCross;
    }

    public void setRateCross(float rateCross) {
        this.rateCross = rateCross;
    }
}
