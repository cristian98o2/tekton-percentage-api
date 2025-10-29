package com.tekton.percentage.api.domain.usecase;

import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.domain.model.gateways.IPercentageClientPort;
import com.tekton.percentage.api.domain.model.util.CustomException;
import com.tekton.percentage.api.domain.model.util.ResponseCode;
import com.tekton.percentage.api.infraestructure.helper.cachemanager.CacheManagerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PercentageUseCaseTest {

    @Mock
    private CacheManagerImpl cacheManager;
    @Mock
    private AsyncHistoryUseCase asyncHistoryUseCase;
    @Mock
    private IPercentageClientPort iPercentageClientPort;
    @InjectMocks
    private PercentageUseCase percentageUseCase;

    @Test
    void shouldComputePercentage() {
        PercentageModel percentageModel = PercentageModel.builder()
                .firstNumber(50.0)
                .secondNumber(50.0)
                .build();
        when(iPercentageClientPort.getPercentage()).thenReturn(0.10);

        Double response = percentageUseCase.execute(percentageModel);

        assertEquals(110.0, response);
    }

    @Test
    void shouldComputePercentageGettingCache() {
        PercentageModel percentageModel = PercentageModel.builder()
                .firstNumber(50.0)
                .secondNumber(50.0)
                .build();
        when(iPercentageClientPort.getPercentage()).thenReturn(null);
        when(cacheManager.getCache()).thenReturn(0.10);

        Double response = percentageUseCase.execute(percentageModel);

        assertEquals(110.0, response);
    }

    @Test
    void shouldComputePercentageCustomException() {
        PercentageModel percentageModel = PercentageModel.builder()
                .firstNumber(50.0)
                .secondNumber(50.0)
                .build();
        when(iPercentageClientPort.getPercentage()).thenReturn(null);
        when(cacheManager.getCache()).thenThrow(new CustomException(ResponseCode.TP004, ResponseCode.TP004.getHtmlMessage()));

        CustomException response = assertThrows(CustomException.class, () -> percentageUseCase.execute(percentageModel));

        assertEquals(ResponseCode.TP004.getHtmlMessage(), response.getMessage());
    }

}
