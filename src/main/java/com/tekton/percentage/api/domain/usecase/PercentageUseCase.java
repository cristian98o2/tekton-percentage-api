package com.tekton.percentage.api.domain.usecase;

import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.domain.model.gateways.IPercentageClientPort;
import com.tekton.percentage.api.domain.model.util.CustomException;
import com.tekton.percentage.api.infraestructure.helper.cachemanager.CacheManagerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RequiredArgsConstructor
@Service
public class PercentageUseCase {

    private final CacheManagerImpl cacheManager;
    private final AsyncHistoryUseCase asyncHistoryUseCase;
    private final IPercentageClientPort iPercentageClientPort;

    public Double execute(PercentageModel percentageModel) {
        Double percentage;
        try {
             percentage = iPercentageClientPort.getPercentage();
            if (percentage != null) {
                cacheManager.saveCache(percentage);
            } else {
                // if (percentage.doubleValue()*100 > 50){ for test get cache
                percentage = cacheManager.getCache();
            }
        }catch (CustomException e){
            asyncHistoryUseCase.saveHistoryAsync(percentageModel, e.getMessage());
            throw e;
        }

        Double computo = Double.sum(percentageModel.getFirstNumber(), percentageModel.getSecondNumber());
        BigDecimal response = BigDecimal.valueOf(Double.sum(computo, percentage * computo))
                .setScale(2, RoundingMode.HALF_UP);

        asyncHistoryUseCase.saveHistoryAsync(percentageModel, String.valueOf(response));

        log.info("The percentage was calculated successfully");

        return response.doubleValue();
    }

}
