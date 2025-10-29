package com.tekton.percentage.api.domain.usecase;

import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.domain.model.gateways.IHistoryJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AsyncHistoryUseCaseTest {

    @Mock
    private IHistoryJPARepository historyRepository;

    @InjectMocks
    private AsyncHistoryUseCase asyncHistoryUseCase;

    @Test
    void shouldSaveHistory() throws Exception {

        PercentageModel model = new PercentageModel();
        String response = "OK";

        doNothing().when(historyRepository).saveHistory(model, response);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                asyncHistoryUseCase.saveHistoryAsync(model, response)
        );
        future.get();

        verify(historyRepository, times(1)).saveHistory(model, response);
    }

}
