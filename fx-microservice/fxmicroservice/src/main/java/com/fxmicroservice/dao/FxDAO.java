package com.fxmicroservice.dao;

import com.fxmicroservice.model.FxRate;

import java.util.Optional;
import java.util.UUID;

public interface FxDAO {

    // Post Mapping Method
    int insertExchangeRate(FxRate fxRate);

    // Get Mapping Method
    Optional<FxRate> getFxRateByFromAndTo(String from, String to);

    // Put Mapping Method
    int updateFxRateByFromAndTo(String from, String to, double updatedRate);
}
