package com.spring.ai.example.controller;


import com.spring.ai.example.advisor.TokenUsageAuditOpenAiAdvisor;
import com.spring.ai.example.entity.HRIntent;
import com.spring.ai.example.entity.LeavePolicy;
import com.spring.ai.example.entity.LeaveRecord;
import com.spring.ai.example.service.HRIntentDetector;
import com.spring.ai.example.service.LeavePolicyService;
import com.spring.ai.example.service.LeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PromptStuffingController {

    @Value("classpath:/promptTemplate/systemPromptTemplate.st")
    private Resource systemPromptTemplate;
    @Value("classpath:/promptTemplate/LeaveAgentPromptTemplate.st")
    private Resource leaveAgentPromptTemplate;

    private final ChatClient chatClient;
    private final HRIntentDetector intentDetector;
    private final LeaveService leaveService;
    private final LeavePolicyService leavePolicyService;


    public PromptStuffingController(ChatClient chatClient, HRIntentDetector intentDetector, LeaveService leaveService, LeavePolicyService leavePolicyService) {
        this.chatClient = chatClient;
        this.intentDetector = intentDetector;
        this.leaveService = leaveService;
        this.leavePolicyService = leavePolicyService;
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

    @GetMapping("/prompt-stuffing-leave-agent")
    public String LeavePromptStuffingResponse(@RequestParam String message) {

        HRIntent intent = intentDetector.detect(message);

        String enrichedContext = "";
        LeavePolicy policy = leavePolicyService.getLeavePolicy();
        List<LeaveRecord> totalLeaves = leaveService.getAll();
        if (intent == HRIntent.LEAVE) {
            log.info("Detected LEAVE intent, fetching leave policy and records for context enrichment.");
            log.info("Fetched Leave Policy: {}", policy);
            enrichedContext = """
                Employee Leave Policy:
                Total Leaves: %d
                Total Leave Used: %d
                Notice Period: %d days 
                Shift Type: %s
                Shift Start Time: %s 
                Shift End Time: %s \s
                \s""".formatted(
                    policy.getTotalLeaves(),
                    totalLeaves.size(),
                    policy.getNoticePeriodInDays(),
                    policy.getShiftDetails().getShiftType(),
                    policy.getShiftDetails().getStartTime(),
                    policy.getShiftDetails().getEndTime()
            );
        } else {
            enrichedContext = """
                    Notice Period: %d days
                    Shift Type: %s
                    Shift Start Time: %s
                    Shift End Time: %s \s
                   \s""".formatted(
                    policy.getNoticePeriodInDays(),
                    policy.getShiftDetails().getShiftType(),
                    policy.getShiftDetails().getStartTime(),
                    policy.getShiftDetails().getEndTime()
            );
        }

        return chatClient.prompt()
                .advisors(new TokenUsageAuditOpenAiAdvisor())
                .system(systemSpec -> systemSpec.text(leaveAgentPromptTemplate))
                .user(enrichedContext + "\nUser Question: " + message)
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



