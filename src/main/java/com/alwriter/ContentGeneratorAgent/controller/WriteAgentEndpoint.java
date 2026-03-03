package com.alwriter.ContentGeneratorAgent.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class WriteAgentEndpoint {

    private final WebClient webClient;

    public WriteAgentEndpoint(WebClient.Builder webClientBuilder,
                              @Value("${external.write.service.url}") String serverUrl) {
        System.out.println("SERVER URL =------------------> " + serverUrl);
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30_000)
                .responseTimeout(Duration.ofMinutes(15))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(900, TimeUnit.SECONDS)));

        this.webClient = webClientBuilder
                .baseUrl(serverUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(32 * 1024 * 1024))
                .build();
    }

    @GetMapping
    public String hello() {
        return "Hi";
    }

    @PostMapping("/write-analyze")
    public Mono<WriteResponse> writeAnalyze(@RequestBody WriteRequest body) {
        return webClient.post()
                .uri("/api/write-analyze")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(WriteResponse.class);
    }

    /**
     * Proxy SSE streaming endpoint.
     * Receives JSON `CombinedRequest` from frontend and forwards the SSE stream
     * coming from the FastAPI service back to the client as SSE events.
     */
    @PostMapping(value = "/combined-think-write/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> stream(@RequestBody CombinedRequest request) {
        System.out.println("🚀 SSE Stream started for: " + request.getTopic());

        ParameterizedTypeReference<ServerSentEvent<String>> typeRef =
                new ParameterizedTypeReference<ServerSentEvent<String>>() {
                };

        Flux<ServerSentEvent<String>> upstream = webClient.post()
                .uri("/api/combined-think-write/stream")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(typeRef)
                .doOnSubscribe(s -> System.out.println("✅ Subscribed to FastAPI SSE"))
                .doOnNext(evt -> {
                    String snippet = evt != null && evt.data() != null ? evt.data().substring(0, Math.min(120, evt.data().length())) : "<empty>";
                    System.out.println("📥 Upstream event: " + snippet);
                })
                .doOnError(err -> {
                    System.err.println("❌ Upstream SSE error: " + err.getMessage());
                })
                .doOnComplete(() -> System.out.println("🏁 Upstream SSE complete"));

        // Forward events transparently to client (preserve id/event/comment if present)
        return upstream.map(evt -> ServerSentEvent.<String>builder()
                        .id(evt.id())
                        .event(evt.event())
                        .data(evt.data())
                        .comment(evt.comment())
                        .retry(evt.retry())
                        .build())
                .doOnNext(e -> System.out.println("✉️ Forwarded event to client"));
    }

    @GetMapping("/health")
    public Mono<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Spring Boot Middleware");
        return Mono.just(response);
    }


}
