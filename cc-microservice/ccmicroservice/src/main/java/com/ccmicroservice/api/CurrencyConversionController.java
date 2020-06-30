package com.ccmicroservice.api;

import com.ccmicroservice.model.CurrencyConversionRequest;
import com.ccmicroservice.model.CurrencyConversionResponse;
import com.ccmicroservice.service.CurrencyConversionService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/cc-service/")
@RestController
public class CurrencyConversionController {

    private CurrencyConversionService ccService;

    public CurrencyConversionController() {}

    @GetMapping(path = "/from/{from}/to/{to}/amount/{amount}")
    public CurrencyConversionResponse getConversionResponse(@PathVariable("from")     String from,
                                                            @PathVariable("to")       String to,
                                                            @PathVariable("amount")   double amount) {
        CurrencyConversionRequest ccRequest = new CurrencyConversionRequest(from, to, amount);
        ccService = new CurrencyConversionService(ccRequest);
        return ccService.getCurrencyConversionResponse();
    }
}
