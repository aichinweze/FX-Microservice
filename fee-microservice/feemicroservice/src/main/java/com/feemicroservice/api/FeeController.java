package com.feemicroservice.api;

import com.feemicroservice.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/fee/")
@RestController
public class FeeController {

    private final FeeService feeService;

    // Anything you autowire has to have an @ annotation above its class declaration
    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @PutMapping(path = "{threshold}")
    public void updateFeeThreshold(@PathVariable("threshold") double threshold) {
        feeService.updateFeeThreshold(threshold);
    }

    @PutMapping(path = "{fixFee}")
    public void updateFixFee(@PathVariable("fixFee") double fixFee) {
        feeService.updateFixFee(fixFee);
    }

    @PutMapping(path = "{percentageFee}")
    public void updatePercentageFee(@PathVariable("percentageFee") double percentageFee) {
        feeService.updatePercentageFee(percentageFee);
    }

    // Test that it takes double types for input
    @RequestMapping(path = "calculate-fee-with/{amount}")
    public double applyServiceFee(@PathVariable("amount") double amount) {
        return feeService.applyFee(amount);
    }
}
