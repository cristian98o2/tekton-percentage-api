package com.tekton.percentage.api.domain.usecase;

import com.tekton.percentage.api.domain.model.PercentageHistoryResponse;
import com.tekton.percentage.api.domain.model.gateways.IHistoryJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class HistoryUseCase {

    private final IHistoryJPARepository iHistoryJPARepository;

    public PercentageHistoryResponse execute() {
        return PercentageHistoryResponse.builder()
                .percentageHistoryModels(iHistoryJPARepository.getPersentageHistory())
                .build();
    }

}
