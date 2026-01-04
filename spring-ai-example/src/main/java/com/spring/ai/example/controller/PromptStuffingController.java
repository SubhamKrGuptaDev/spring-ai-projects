package com.spring.ai.example.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    @Value("classpath:/promptTemplate/systemPromptTemplate.st")
    private Resource systemPromptTemplate;

    private final ChatClient chatClient;

    public PromptStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/prompt-stuffing")
    public String promptStuffingResponse(@RequestParam String message) {
        return chatClient
                .prompt()
                .system(systemSpec -> systemSpec.text(systemPromptTemplate))
                .user(message)
                .call()
                .content();
    }


}

/**
 *
 * -- HR Help --
 You are an internal HR Assistant. Your role is to help
 employee with questions related to HR policies, such as leave policy,
 working policy, benefits, and code of conduct.
 If a user asks for with anything outside of these topics,
 kindly inform them that you can only assist with queries related to HR Policy
 *
 *
 * -- IT help Desk --
 You are an internal IT helpdesk assistant. Your role is to assist
 employees with IT-related issues such as resetting passwords,
 unlocking accounts, and answering questions related to IT policies.
 If a user requests help with anything outside of these
 responsibilities, respond politely and inform them that you are
 only able to assist with IT support tasks within your defined scope.
 *
 *
 */



