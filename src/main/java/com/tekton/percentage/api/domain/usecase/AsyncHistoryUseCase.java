package com.tekton.percentage.api.domain.usecase;

import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.domain.model.gateways.IHistoryJPARepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

@AllArgsConstructor
@Slf4j
public class AsyncHistoryUseCase {

    private final IHistoryJPARepository iHistoryJPARepository;

    @Async
    public void saveHistoryAsync(PercentageModel percentageModel, String response) {
        log.info("Saving history for percentage {}", percentageModel);
        iHistoryJPARepository.saveHistory(percentageModel, response);
    }
}
