package com.feemicroservice.model;

import org.springframework.stereotype.Component;

@Component
public class FeeStructure {

    private double feeThreshold;
    private double fixFee;
    private double percentageFee;

    public FeeStructure(double feeThreshold, double fixFee, double percentageFee) {
        this.feeThreshold = feeThreshold;
        this.fixFee = fixFee;
        this.percentageFee = (1 - (percentageFee/100));
    }

    public FeeStructure() {
        this.feeThreshold = 1000;
        this.fixFee = 25;
        this.percentageFee = 0.99;
    }

    public double getFeeThreshold() {
        return feeThreshold;
    }

    public void setFeeThreshold(double feeThreshold) {
        this.feeThreshold = feeThreshold;
    }

    public double getFixFee() {
        return fixFee;
    }

    public void setFixFee(double fixFee) {
        this.fixFee = fixFee;
    }

    public double getPercentageFee() {
        return percentageFee;
    }

    public void setPercentageFee(double percentageFee) {
        this.percentageFee = percentageFee;
    }
}
