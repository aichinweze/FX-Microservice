package com.ccmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class FxRate {

    // FX Rate ID number
    private int id;

    private String from;
    private String to;

    private double exchangeRate;

    public FxRate(@JsonProperty("id") int id,
                  @JsonProperty("from") String from,
                  @JsonProperty("to") String to,
                  @JsonProperty("exchangeRate") double exchangeRate) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.exchangeRate = exchangeRate;
    }

    /*public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
