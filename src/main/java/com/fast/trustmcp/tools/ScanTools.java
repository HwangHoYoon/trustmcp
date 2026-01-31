package com.fast.trustmcp.tools;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ScanTools {

    private final WebClient webClient;

    public ScanTools(
            @Value("${trust.api.base-url}") String baseUrl
    ) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @McpTool(
            name = "scan_url",
            description = "Scan a website URL for security vulnerabilities"
    )
    public Mono<String> scanUrl(String url) {

        if (!url.startsWith("http")) {
            url = "https://" + url;
        }
        return webClient.get()
                .uri("/api/scan/streamAll?url={url}", url)
                .retrieve()
                .bodyToMono(String.class);
    }

    record ScanRequest(String target_url) {}
}
