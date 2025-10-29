package com.tekton.percentage.api.infraestructure.entrypoints.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@Data
@Schema(description = "Modelo de respuesta del historial de porcentajes.")
public class PercentageHistoryDto {

    private Long id;
    private OffsetDateTime timestamp;
    private String endpoint;
    private String params;
    private String response;

}
