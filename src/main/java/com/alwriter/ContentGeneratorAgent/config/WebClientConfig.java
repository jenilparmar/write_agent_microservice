package com.alwriter.ContentGeneratorAgent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements WebFluxConfigurer {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://cnvrt-lab-danish-work.vercel.app",
                        "http://localhost:3000",
                        "http://localhost:5173",
                        "http://localhost:8081",
                        "https://hoppscotch.io"
                )
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        // Disable buffering for SSE
        configurer.defaultCodecs().enableLoggingRequestDetails(true);
        configurer.defaultCodecs().maxInMemorySize(32 * 1024 * 1024);
    }
}
