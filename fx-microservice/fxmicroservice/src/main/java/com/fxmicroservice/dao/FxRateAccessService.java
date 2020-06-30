package com.fxmicroservice.dao;

import com.fxmicroservice.model.FxRate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fxDao")
public class FxRateAccessService implements FxDAO {

    private static List<FxRate> DB = new ArrayList<>();

    static {
        // Hard-coded GBP-USD Exchange rate into service
        FxRate givenRateGBP_USD = new FxRate(101, "GBP","USD",1.29);
        DB.add(givenRateGBP_USD);
    }

    @Override
    public int insertExchangeRate(FxRate fxRate) {
        DB.add(fxRate);
        return 1;
    }

    @Override
    public Optional<FxRate> getFxRateByFromAndTo(String from, String to) {
        return DB.stream()
                .filter(rate -> rate.getFrom().equals(from) && rate.getTo().equals(to))
                .findFirst();
    }

    @Override
    public int updateFxRateByFromAndTo(String from, String to, double updatedRate) {
        return getFxRateByFromAndTo(from, to).map(r -> {
                int indexOfFxRateToUpdate = DB.indexOf(r);
                if (indexOfFxRateToUpdate >= 0) {
                    DB.get(indexOfFxRateToUpdate).setExchangeRate(updatedRate);
                    return 1;
                }
                return 0;
                }).orElse(0);
    }
}
