package com.tekton.percentage.api.infraestructure.entrypoints.dto.response;

import com.tekton.percentage.api.domain.model.util.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Generic response object
 *
 * @param <T>
 */
@Schema(description = "Modelo de salida generica")
@NoArgsConstructor
@Getter
@ToString
public class GenericResponseDto<T> {
    @Schema(description = "Codigo interno de la respuesta")
    private String responseCode;
    @Schema(description = "Codigo http de la respuesta")
    private int status;
    @Schema(description = "Mensaje adicional de la respuesta")
    private String responseMessage;
    @Schema(description = "Contenido de la respuesta")
    private T data;

    public GenericResponseDto(ResponseCode responseCode, String responseMessage, T data) {
        this.status = responseCode.getStatus();
        this.responseCode = responseCode.toString();
        this.responseMessage = responseMessage;
        this.data = data;
    }
}