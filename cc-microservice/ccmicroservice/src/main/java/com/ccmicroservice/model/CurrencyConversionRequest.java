package com.ccmicroservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConversionRequest {

    private String from;
    private String to;
    private double amount;

    public CurrencyConversionRequest(@JsonProperty("from")    String from,
                                     @JsonProperty("to")      String to,
                                     @JsonProperty("amount")  double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public CurrencyConversionRequest() {}

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
