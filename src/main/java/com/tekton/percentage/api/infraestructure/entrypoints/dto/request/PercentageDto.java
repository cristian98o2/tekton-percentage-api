package com.tekton.percentage.api.infraestructure.entrypoints.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Modelo de entrada de datos para aplicarle el porcentaje.")
public class PercentageDto {

    @NotNull
    @Schema(description = "Primer numero.")
    public Double firstNumber;
    @NotNull
    @Schema(description = "segundo numero.")
    public Double secondNumber;
}
