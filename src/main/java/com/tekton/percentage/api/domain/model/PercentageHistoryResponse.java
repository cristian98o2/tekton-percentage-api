package com.tekton.percentage.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PercentageHistoryResponse {

    public List<PercentageHistoryModel> percentageHistoryModels;

}
