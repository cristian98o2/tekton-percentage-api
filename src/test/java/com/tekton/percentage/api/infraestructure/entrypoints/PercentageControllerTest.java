package com.tekton.percentage.api.infraestructure.entrypoints;

import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.domain.model.PercentageHistoryResponse;
import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.domain.usecase.HistoryUseCase;
import com.tekton.percentage.api.domain.usecase.PercentageUseCase;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.request.PercentageDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.GenericResponseDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.HistoryResponseDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.PercentageHistoryDto;
import com.tekton.percentage.api.infraestructure.entrypoints.mapper.IPercentageMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PercentageControllerTest {

    @Mock
    private PercentageUseCase percentageUseCase;
    @Mock
    private HistoryUseCase historyUseCase;
    @Mock
    private IPercentageMapper iPercentageMapper;
    @InjectMocks
    private PercentageController percentageController;

    @Test
    void shouldPostPercentage(){
        PercentageDto percentageDto = PercentageDto.builder()
                .firstNumber(50.0)
                .secondNumber(50.0)
                .build();
        PercentageModel percentageModel = PercentageModel.builder()
                .firstNumber(50.0)
                .secondNumber(50.0)
                .build();
        when(percentageUseCase.execute(percentageModel)).thenReturn(150.0);
        when(iPercentageMapper.toPercentageModel(percentageDto)).thenReturn(percentageModel);

        GenericResponseDto<Double> response = percentageController.percentage(percentageDto);

        verify(percentageUseCase, times(1)).execute(percentageModel);

    }

    @Test
    void shouldGetHistories(){
        PercentageHistoryDto percentageHistoryDto = PercentageHistoryDto.builder()
                .id(1L)
                .endpoint("/path")
                .response("success")
                .build();

        HistoryResponseDto historyResponseDto = HistoryResponseDto.builder()
                .histories(List.of(percentageHistoryDto))
                .build();

        PercentageHistoryModel percentageHistoryModel = PercentageHistoryModel.builder()
                .id(1L)
                .endpoint("/path")
                .response("success")
                .build();

        PercentageHistoryResponse percentageModel = PercentageHistoryResponse.builder()
                .percentageHistoryModels(List.of(percentageHistoryModel))
                .build();
        when(historyUseCase.execute()).thenReturn(percentageModel);
        when(iPercentageMapper.toHistoryResponseDto(percentageModel)).thenReturn(historyResponseDto);

        GenericResponseDto<HistoryResponseDto> response = percentageController.history();

        verify(historyUseCase, times(1)).execute();

    }

}
