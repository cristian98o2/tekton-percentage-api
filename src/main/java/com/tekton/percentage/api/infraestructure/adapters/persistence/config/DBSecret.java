package com.tekton.percentage.api.infraestructure.adapters.persistence.config;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DBSecret {
    private final String url;
    private final String username;
    private final String password;
}
