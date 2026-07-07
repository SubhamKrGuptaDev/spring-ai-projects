package com.github.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class CopilotChatConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder()
                .requestInterceptor(copilotHeaderInterceptor());
    }

    private ClientHttpRequestInterceptor copilotHeaderInterceptor() {
        return (request, body, execution) -> {
            // These headers are mandatory to prevent GitHub from throwing a 400 Bad Request
            request.getHeaders().add("Copilot-Integration-Id", "copilot-developer-cli");
            request.getHeaders().add("Editor-Version", "vscode/1.90.0");
            request.getHeaders().add("User-Agent", "GitHubCopilotChat/1.90.0");
            return execution.execute(request, body);
        };
    }

}
