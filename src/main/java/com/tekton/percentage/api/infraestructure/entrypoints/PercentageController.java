package com.tekton.percentage.api.infraestructure.entrypoints;

import com.tekton.percentage.api.domain.model.validation.UnknownFieldValidation;
import com.tekton.percentage.api.domain.usecase.HistoryUseCase;
import com.tekton.percentage.api.domain.usecase.PercentageUseCase;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.request.PercentageDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.GenericResponseDto;
import com.tekton.percentage.api.infraestructure.entrypoints.dto.response.HistoryResponseDto;
import com.tekton.percentage.api.infraestructure.entrypoints.mapper.IPercentageMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import static com.tekton.percentage.api.domain.model.util.ResponseCode.TP001;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "CartController", description = "Micro para las operaciones relacionadas a los porcentajes.")
public class PercentageController {

    private final PercentageUseCase percentageUseCase;
    private final HistoryUseCase historyUseCase;
    private final IPercentageMapper iPercentageMapper;

    @UnknownFieldValidation
    @PostMapping("/percentage")
    @Operation(summary = "Obtener numero",
            description = "Obtener un numero nuevo con el porcentaje aplicado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Numero creado",
                    content = @Content(schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "500",
                    description = "Error inesperado durante el proceso",
                    content = @Content)})
    public GenericResponseDto<Double> percentage(@Valid @RequestBody PercentageDto percentageDto){

        return new GenericResponseDto<>(TP001, TP001.getHtmlMessage(),
                percentageUseCase.execute(iPercentageMapper.toPercentageModel(percentageDto)));
    }

    @GetMapping("/history")
    @Operation(summary = "Obtener numero",
            description = "Obtener un numero nuevo con el porcentaje aplicado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Historial encontrado",
                    content = @Content(schema = @Schema(implementation = HistoryResponseDto.class))),
            @ApiResponse(responseCode = "500",
                    description = "Error inesperado durante el proceso",
                    content = @Content)})
    public GenericResponseDto<HistoryResponseDto> history(){

        return new GenericResponseDto<>(TP001, TP001.getHtmlMessage(),
                iPercentageMapper.toHistoryResponseDto(historyUseCase.execute()));
    }

}
