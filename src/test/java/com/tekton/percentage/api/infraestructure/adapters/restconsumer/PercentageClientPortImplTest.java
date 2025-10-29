package com.tekton.percentage.api.infraestructure.adapters.restconsumer;

import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PercentageClientPortImplTest {

    @Mock
    private OkHttpClient client;
    @InjectMocks
    private PercentageClientPortImpl percentageClient;

    @Test
    void getPercentage() throws IOException {
        ResponseBody responseBody = ResponseBody.create("{\"data\": {\n \"percentage\": 10.0 \n}}",
                MediaType.parse("application/json"));

        Response responseMock = new Response.Builder()
                .request(new Request.Builder().url("https://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build();

        Call callMock = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(callMock);
        when(callMock.execute()).thenReturn(responseMock);

        Double response = percentageClient.getPercentage();

        assertEquals(10.0, response);
    }

    @Test
    void getPercentageIsNotSuccessful() throws IOException {
        ResponseBody responseBody = ResponseBody.create(
                "",  // cuerpo vacío
                MediaType.parse("application/json")
        );

        Response responseMock = new Response.Builder()
                .request(new Request.Builder().url("https://example.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(400)
                .message("Bad Request")
                .body(responseBody)
                .build();

        Call callMock = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(callMock);
        when(callMock.execute()).thenReturn(responseMock);

        Double response = percentageClient.getPercentage();

        assertNotNull(response);
    }

    @Test
    void getPercentageException() throws IOException {


        Call callMock = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(callMock);
        when(callMock.execute()).thenThrow(new IOException());

        Double response = percentageClient.getPercentage();

        assertNotNull(response);

        //CustomException response = assertThrows(CustomException.class, () -> percentageClient.getPercentage());
        //assertEquals(ResponseCode.TP002.getHtmlMessage(), response.getMessage());
    }

}
