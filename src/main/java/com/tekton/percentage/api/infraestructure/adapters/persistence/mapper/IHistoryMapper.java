package com.tekton.percentage.api.infraestructure.adapters.persistence.mapper;

import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.infraestructure.adapters.persistence.entity.PercentageHistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IHistoryMapper {

    PercentageHistoryModel toPercentageHistoryModel(PercentageHistoryEntity percentageHistoryEntity);

}
