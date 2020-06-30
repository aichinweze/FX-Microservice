package com.feemicroservice.service;

import com.feemicroservice.model.FeeStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeService {

    private FeeStructure feeStructure;

    private double lowerFeeFunction(double amount) {
        return amount - feeStructure.getFixFee();
    }

    private double percentageFeeFunction(double amount) {
        return amount * getPercentageFee();
    }

    @Autowired
    public FeeService(FeeStructure feeStructure) {
        this.feeStructure = feeStructure;
    }

    public double getFeeThreshold() {
        return feeStructure.getFeeThreshold();
    }

    public double getFixFee() {
        return feeStructure.getFixFee();
    }

    public double getPercentageFee() {
        return feeStructure.getPercentageFee();
    }

    // Test: If original amount less than fee threshold
    public double applyFee(double amount) {
        if (amount < feeStructure.getFeeThreshold()) {
            return lowerFeeFunction(amount);
        }
        return percentageFeeFunction(amount);
    }

    // TODO: Make update uniform with set method in CC-Service
    public int updateFeeThreshold(double feeThreshold) {
        feeStructure.setFeeThreshold(feeThreshold);
        return 1;
    }

    public int updateFixFee(double fixFee) {
        feeStructure.setFixFee(fixFee);
        return 1;
    }

    public int updatePercentageFee(double percentageFee) {
        feeStructure.setPercentageFee(percentageFee);
        return 1;
    }
}
