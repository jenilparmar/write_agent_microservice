package com.alwriter.ContentGeneratorAgent.controller;

import com.alwriter.ContentGeneratorAgent.controller.WriteRequest;
import com.alwriter.ContentGeneratorAgent.controller.WriteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class WriteAgentEndpoint {

    private final WebClient webClient;

    public WriteAgentEndpoint(
            WebClient.Builder webClientBuilder,
            @Value("${external.write.service.url}") String serverUrl
    ) {
        this.webClient = webClientBuilder
                .baseUrl(serverUrl)
                .build();
    }

    @PostMapping("/write-analyze")
    public Mono<WriteResponse> writeAnalyze(@RequestBody WriteRequest body) {

        return webClient.post()
                .uri("/api/write-analyze")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(WriteResponse.class);
    }
}
