package com.tekton.percentage.api.infraestructure.entrypoints.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Schema(description = "Lista de historiales.")
public class HistoryResponseDto {

    public List<PercentageHistoryDto> histories;

}
