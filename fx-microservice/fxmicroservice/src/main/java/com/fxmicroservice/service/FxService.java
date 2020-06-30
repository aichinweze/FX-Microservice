package com.fxmicroservice.service;

import com.fxmicroservice.dao.FxDAO;
import com.fxmicroservice.model.FxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FxService {

    private final FxDAO fxDAO;

    @Autowired
    public FxService(@Qualifier("fxDao") FxDAO fxDAO) {
        this.fxDAO = fxDAO;
    }

    public int addFxRate(FxRate fxRate) {
        return fxDAO.insertExchangeRate(fxRate);
    }

    public Optional<FxRate> getFxRateByFromAndTo(String from, String to) {
        return fxDAO.getFxRateByFromAndTo(from, to);
    }

    public int updateFxRateByFromAndTo(String from, String to, double updatedRate) {
        return fxDAO.updateFxRateByFromAndTo(from, to, updatedRate);
    }
}
