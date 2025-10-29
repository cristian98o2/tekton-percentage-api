package com.tekton.percentage.api.infraestructure.adapters.restconsumer.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    public OkHttpClient getHttpClient(OkHttpMetricsEventListener listener) {
        return new OkHttpClient.Builder()
                .eventListener(listener)
                .build();
    }

    @Bean
    public OkHttpMetricsEventListener okHttpMetricsListener(MeterRegistry registry) {
        return OkHttpMetricsEventListener.builder(registry, "http-outgoing")
                .uriMapper(req -> req.url().encodedPath()).build();
    }

}
