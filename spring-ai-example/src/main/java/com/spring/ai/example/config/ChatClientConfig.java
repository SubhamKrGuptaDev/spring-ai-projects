package com.spring.ai.example.config;

import com.spring.ai.example.advisor.TokenUsageAuditOpenAiAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
//                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(List.of(new TokenUsageAuditOpenAiAdvisor()))
                .defaultToolNames("applyLeave", "getShiftDetails")
                .defaultSystem("""
                         You are an internal HR Assistant. Your role is to help
                         employee with questions related to HR policies, such as leave policy,
                         working policy, benefits, and code of conduct.
                         Your supporting languages are {English, Hindi, Bengali, Chinese}.
                         If a user asks for anything outside of those language, kindly inform them that you can only 
                         communicate with those languages only as for now. 
                         If a user asks for with anything outside of these topics,
                         kindly inform them that you can only assist with queries related to HR Policy
                        """)
                .defaultUser("How can you help me?")
                .build();
    }


}
