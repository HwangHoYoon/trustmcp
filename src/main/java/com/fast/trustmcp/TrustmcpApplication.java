package com.fast.trustmcp;

import com.fast.trustmcp.tools.ScanTools;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TrustmcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrustmcpApplication.class, args);
    }
    @Bean
    public List<ToolCallback> danTools(ScanTools scanTools) {
        return List.of(ToolCallbacks.from(scanTools));
    }
}
