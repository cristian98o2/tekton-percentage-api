package com.tekton.percentage.api.domain.model.util;

import lombok.Getter;

@Getter
public enum ResponseCode {
    TP001(200, "Operacion exitosa."),
    TP002(400, "Ocurrio un error al realizar la peticion HTTP."),
    TP003(500, "Ocurrio un error realizando operaciones de persistencia."),
    TP004(400, "No se cuenta porcentaje almacenado");

    private final int status;
    private final String htmlMessage;
    ResponseCode(int status, String htmlMessage) {
        this.status = status;
        this.htmlMessage = htmlMessage;
    }
}
