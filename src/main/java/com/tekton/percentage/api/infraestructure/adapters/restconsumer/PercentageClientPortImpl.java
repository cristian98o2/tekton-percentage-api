package com.tekton.percentage.api.infraestructure.adapters.restconsumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekton.percentage.api.domain.model.gateways.IPercentageClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class PercentageClientPortImpl implements IPercentageClientPort {

    private final OkHttpClient client;

    /**
     * @return double
     */
    @Override
    public Double getPercentage() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/percentage")
                .get()
                .build();
        try(Response response = client.newCall(request).execute()){
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.isSuccessful()) {
                assert response.body() != null;
                JsonNode jsonNode = objectMapper.readTree(response.body().string());
                JsonNode dataNode = jsonNode.get("data");
                return dataNode.get("percentage").asDouble();
            } else {
                return Double.valueOf(generatePercentage());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            //Codigo real de no llegar a necesitar un mock
            //throw new CustomException(ResponseCode.TP002, ResponseCode.TP002.getHtmlMessage());
            return generatePercentage();
        }
    }

    private double generatePercentage() {
        Random random = new Random();
        double percentage = random.nextDouble();
        log.info("Generating percentage {}", percentage*100);
        return percentage;
    }

}
