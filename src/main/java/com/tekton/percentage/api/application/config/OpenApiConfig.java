package com.tekton.percentage.api.application.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${info.app.version}")
    private String infoAppVersion;

    @Value("${info.app.name}")
    private String infoAppName;

    @Value("${info.app.description}")
    private String infoAppDescription;

    @Bean
    public GroupedOpenApi userApi() {
        final String[] packagesToScan = {"com.tekton.percentage.api"};
        return GroupedOpenApi
                .builder()
                .group("Tekton Percentage")
                .packagesToScan(packagesToScan)
                .pathsToMatch("/**")
                .addOpenApiCustomizer(statusApiCostumizer())
                .build();
    }

    private OpenApiCustomizer statusApiCostumizer() {
        return openApi -> openApi
                .info(new Info()
                    .title(infoAppName)
                    .description(infoAppDescription)
                    .version(infoAppVersion));
    }

}
