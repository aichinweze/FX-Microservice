package com.ccmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyConversionResponse {

    private String from;
    private String to;
    private double startAmount;
    private double convertedAmount;

    public CurrencyConversionResponse(@JsonProperty("from") String from,
                                      @JsonProperty("to")   String to,
                                      @JsonProperty("startAmount")      double startAmount,
                                      @JsonProperty("convertedAmount")  double convertedAmount) {
        this.from = from;
        this.to = to;
        this.startAmount = startAmount;
        this.convertedAmount = convertedAmount;
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

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
