package com.fast.trustmcp.tools;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class ScanTools {

    private WebClient webClient;

    @Value("${trust.api.base-url}")
    private String baseUrl;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .build();
    }

    @Tool(name = "scan_url")
    public Mono<String> scanUrl(String url) {

        if (!url.startsWith("http")) {
            url = "https://" + url;
        }

        return webClient.get()
                .uri("http://localhost:8080/api/scan/mcpAll?url={url}", url)
                .retrieve()
                .bodyToMono(String.class); // 🔥 가장 중요
    }
}
