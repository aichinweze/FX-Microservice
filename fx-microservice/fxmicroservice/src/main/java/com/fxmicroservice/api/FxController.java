package com.fxmicroservice.api;

import com.fxmicroservice.model.FxRate;
import com.fxmicroservice.service.FxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/fx")
@RestController
public class FxController {

    private final FxService fxService;

    @Autowired
    public FxController(FxService fxService) {
        this.fxService = fxService;
    }

    @PutMapping(path = "/id/{id}/from/{from}/to/{to}/rate/{rate}")
    public void addFxRate(@Valid @PathVariable("id")   int id,
                                 @PathVariable("from") String from,
                                 @PathVariable("to")   String to,
                                 @PathVariable("rate") double rate) {
        fxService.addFxRate(new FxRate(id, from, to, rate));
    }

    @GetMapping(path = "/from/{from}/to/{to}")
    public FxRate getFxRateByFromAndTo(@PathVariable("from") String from,
                                       @PathVariable("to") String to) {
        return fxService.getFxRateByFromAndTo(from, to)
                        .orElse(null);
    }
}
