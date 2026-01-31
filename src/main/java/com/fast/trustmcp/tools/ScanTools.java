package com.fast.trustmcp.tools;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ScanTools {

    private WebClient webClient;

    @Value("${trust.api.base-url}")
    private String baseUrl;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Tool(name = "scan_url", description = "Scan a website URL for security vulnerabilities")
    public Mono<String> scanUrl(String url) {
        if (!url.startsWith("http")) {
            url = "https://" + url;
        }
        return webClient.get()
                .uri("/api/scan/streamAll?url={url}", url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
