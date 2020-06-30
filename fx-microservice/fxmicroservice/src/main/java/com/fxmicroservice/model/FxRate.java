package com.fxmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class FxRate {

    private int id;
    private String from;
    private String to;

    @Min(value = 0L)
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object == null || getClass() != object.getClass()) {
            return false;
        }

        FxRate fxRateToCompare = (FxRate) object;
        return id == fxRateToCompare.getId()            &&
               from.equals(fxRateToCompare.getFrom())   &&
               to.equals(fxRateToCompare.getTo())       &&
               exchangeRate == fxRateToCompare.getExchangeRate();
    }
}
