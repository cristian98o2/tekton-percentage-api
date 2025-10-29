package com.tekton.percentage.api.domain.usecase;

import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.domain.model.PercentageHistoryResponse;
import com.tekton.percentage.api.domain.model.gateways.IHistoryJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HistoryUseCaseTest {

    @Mock
    private IHistoryJPARepository iHistoryJPARepository;
    @InjectMocks
    private HistoryUseCase historyUseCase;

    @Test
    void shouldGetHistory() {
        PercentageHistoryModel percentageHistoryModel = PercentageHistoryModel.builder()
                .id(1L)
                .endpoint("/path")
                .response("success")
                .build();

        when(iHistoryJPARepository.getPersentageHistory()).thenReturn(List.of(percentageHistoryModel));

        PercentageHistoryResponse response = historyUseCase.execute();

        assertEquals(percentageHistoryModel.getId(), response.getPercentageHistoryModels().get(0).getId());
    }

}
