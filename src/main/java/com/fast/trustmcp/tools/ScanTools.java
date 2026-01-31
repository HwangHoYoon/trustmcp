package com.fast.trustmcp.tools;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @Tool(name = "scan_url", description = "Scan a website URL and return final results as a list")
    public Mono<List> scanUrl(String url) {
        if (!url.startsWith("http")) {
            url = "https://" + url;
        }

        return webClient.get()
                .uri("/api/scan/mcpAll?url={url}", url)
                .retrieve()
                .bodyToMono(List.class) // JSON 리스트 그대로 받음
                .onErrorResume(e -> {
                    // 오류 발생 시 빈 리스트 반환
                    e.printStackTrace();
                    return Mono.just(List.of());
                });
    }
}
