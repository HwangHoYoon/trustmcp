package com.fast.trustmcp;

import com.fast.trustmcp.tools.FileSystemTools;
import com.fast.trustmcp.tools.ScanTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrustmcpApplication {

    @Autowired
    private FileSystemTools fileSystemTools;

    @Autowired
    private ScanTools scanTools;

    public static void main(String[] args) {
        SpringApplication.run(TrustmcpApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider getToolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(
                        fileSystemTools, scanTools)
                .build();
    }
}
