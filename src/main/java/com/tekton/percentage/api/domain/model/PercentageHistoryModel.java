package com.tekton.percentage.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@Data
public class PercentageHistoryModel {

    private Long id;
    private OffsetDateTime timestamp;
    private String endpoint;
    private String params;
    private String response;

}
