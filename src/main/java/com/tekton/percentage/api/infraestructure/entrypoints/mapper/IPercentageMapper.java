package com.tekton.percentage.api.infraestructure.entrypoints.mapper;

import com.tekton.percentage.api.domain.model.PercentageHistoryResponse;
import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.request.PercentageDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.HistoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IPercentageMapper {

    PercentageModel toPercentageModel(PercentageDto percentageDto);
    @Mappings(value = {
            @Mapping(target = "histories", source = "percentageHistoryModels")
    })
    HistoryResponseDto toHistoryResponseDto(PercentageHistoryResponse percentageHistoryResponse);

}
