package com.spring.ai.example.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;

@Slf4j
public class TokenUsageAuditOpenAiAdvisor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        ChatResponse chatResponse = chatClientResponse.chatResponse();
        ChatResponseMetadata metadata = chatResponse.getMetadata();

        if(metadata != null &&
                metadata.getUsage() != null) {
            log.info("Usage Details : {}", metadata.getUsage());
            Usage usage = metadata.getUsage();
            log.info("Native Usage : {}", usage.getNativeUsage());
            log.info("Total Token : {}", usage.getTotalTokens());
        }

        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "TokenUsageAuditOpenAiAdvisor";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
