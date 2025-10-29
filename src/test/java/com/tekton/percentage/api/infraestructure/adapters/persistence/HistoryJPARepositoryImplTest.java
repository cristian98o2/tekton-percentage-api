package com.tekton.percentage.api.infraestructure.adapters.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.infraestructure.adapters.persistence.entity.PercentageHistoryEntity;
import com.tekton.percentage.api.infraestructure.adapters.persistence.mapper.IHistoryMapper;
import com.tekton.percentage.api.infraestructure.adapters.persistence.repository.PercentageHistoryJPARepository;
import com.tekton.percentage.api.infraestructure.adapters.persistence.repository.impl.HistoryJPARepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryJPARepositoryImplTest {

    @Mock
    private PercentageHistoryJPARepository percentageHistoryJPARepository;
    @Mock
    private IHistoryMapper iHistoryMapper;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private HistoryJPARepositoryImpl historyJPARepository;

    @Test
    void shouldSaveHistory() {
        PercentageModel percentageModel = PercentageModel.builder()
                .firstNumber(50.0)
                .secondNumber(50.0)
                .build();

        historyJPARepository.saveHistory(percentageModel, "110");

        verify(percentageHistoryJPARepository, times(1)).save(any());
    }

    @Test
    void shouldGetPercentageHistory() {
        PercentageHistoryModel percentageHistoryModel = PercentageHistoryModel.builder()
                .id(1L)
                .endpoint("/path")
                .response("success")
                .build();
        PercentageHistoryEntity percentageHistoryEntity = PercentageHistoryEntity.builder()
                .id(1L)
                .endpoint("/path")
                .response("success")
                .build();

        when(percentageHistoryJPARepository.findAll()).thenReturn(List.of(percentageHistoryEntity));
        when(iHistoryMapper.toPercentageHistoryModel(percentageHistoryEntity)).thenReturn(percentageHistoryModel);

        List<PercentageHistoryModel> response = historyJPARepository.getPersentageHistory();

        assertEquals(percentageHistoryModel.getId(), response.get(0).getId());
    }

}
