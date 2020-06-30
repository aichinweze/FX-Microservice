package com.ccmicroservice.service;

import com.ccmicroservice.model.CurrencyConversionRequest;
import com.ccmicroservice.model.CurrencyConversionResponse;
import com.ccmicroservice.model.FxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//TODO: Check you have ordered public and private methods well
@Service
public class CurrencyConversionService {

    //TODO: Making these objects from new rather than keeping??
    private final CurrencyConversionRequest ccRequest;

    private final String feeBaseUrl = "http://localhost:8081/api/v1/fee/";
    private final String fxBaseUrl  = "http://localhost:8080/api/v1/fx/";

    private double exchangeRate;

    private Double productOf(double number1, double number2) {
        return number1 * number2;
    }

    private Double applyServiceFee(double amount) {
        String calculateFeeUrl = feeBaseUrl + "calculate-fee-with/" + amount;
        RestTemplate restTemplate = new RestTemplate();

        // System.out.println("calcUrl: " + calculateFeeUrl);
        double amountWithFee = restTemplate.getForObject(calculateFeeUrl, Double.class);
        // System.out.println("Amount with Fee: " + amountWithFee);
        return amountWithFee;
    }

    @Autowired
    public CurrencyConversionService(CurrencyConversionRequest ccRequest) {
        this.ccRequest = ccRequest;
    }

    public String getFrom() {
        return ccRequest.getFrom();
    }

    public String getTo() {
        return ccRequest.getTo();
    }

    public Double getStartAmount() {
        return ccRequest.getAmount();
    }

    public double getExchangeRate() {
        setExchangeRate();
        return exchangeRate;
    }

    public void setExchangeRate() {
        String fxUrl = fxBaseUrl + "from/" + getFrom() + "/to/" + getTo();
        RestTemplate restTemplate = new RestTemplate();

        FxRate fxRate = restTemplate.getForObject(fxUrl, FxRate.class);
        exchangeRate = fxRate.getExchangeRate();
    }

    // Null pointer exception?
    public Double getConvertedAmount() {
        double amountWithFee = applyServiceFee(ccRequest.getAmount());
        setExchangeRate();
        return productOf(exchangeRate, amountWithFee);
    }

    public CurrencyConversionResponse getCurrencyConversionResponse() {
        return new CurrencyConversionResponse(ccRequest.getFrom(),
                                              ccRequest.getTo(),
                                              ccRequest.getAmount(),
                                              getConvertedAmount());
    }
}
