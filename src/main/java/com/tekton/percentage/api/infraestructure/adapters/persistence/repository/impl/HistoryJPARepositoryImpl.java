package com.tekton.percentage.api.infraestructure.adapters.persistence.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekton.percentage.api.domain.model.PercentageHistoryModel;
import com.tekton.percentage.api.domain.model.PercentageModel;
import com.tekton.percentage.api.domain.model.gateways.IHistoryJPARepository;
import com.tekton.percentage.api.domain.model.util.CustomException;
import com.tekton.percentage.api.domain.model.util.ResponseCode;
import com.tekton.percentage.api.infraestructure.adapters.persistence.entity.PercentageHistoryEntity;
import com.tekton.percentage.api.infraestructure.adapters.persistence.mapper.IHistoryMapper;
import com.tekton.percentage.api.infraestructure.adapters.persistence.repository.PercentageHistoryJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class HistoryJPARepositoryImpl implements IHistoryJPARepository {

    private final PercentageHistoryJPARepository percentageHistoryJPARepository;
    private final IHistoryMapper iHistoryMapper;
    private final ObjectMapper objectMapper;

    /**
     * @param percentageModel
     * @param response
     */
    @Override
    public void saveHistory(PercentageModel percentageModel, String response) {
        PercentageHistoryEntity percentageHistoryEntity;
        try {
            percentageHistoryEntity = PercentageHistoryEntity.builder()
                    .timestamp(OffsetDateTime.now())
                    .endpoint("/api/v1/percentage")
                    .params(objectMapper.writeValueAsString(percentageModel))
                    .response(response)
                    .build();
        } catch (JsonProcessingException e) {
            throw new CustomException(ResponseCode.TP003, ResponseCode.TP003.getHtmlMessage());
        }
        percentageHistoryJPARepository.save(percentageHistoryEntity);
    }

    /**
     * @return List<PercentageHistoryModel>
     */
    @Override
    public List<PercentageHistoryModel> getPersentageHistory() {
        return percentageHistoryJPARepository.findAll().stream().map(iHistoryMapper::toPercentageHistoryModel).toList();
    }

}
