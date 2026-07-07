package com.github.example.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
//                .defaultSystem("""
//                        You are an internal Product Assistant. Your role is to help
//                        user to find products, such as product details, product price, product availability, and product reviews.
//                        If a user asks for with anything outside of these topics,
//                        kindly inform them that you can only assist with queries related to Product
//                       """)
//                .defaultUser("How can you help me?")
                .build();
    }

}
