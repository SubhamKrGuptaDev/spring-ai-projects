package com.spring.ai.example.service;

import com.spring.ai.example.entity.HRIntent;
import org.springframework.stereotype.Component;

@Component
public class HRIntentDetector {

    public HRIntent detect(String message) {
        String msg = message.toLowerCase();

        if (msg.contains("leave") || msg.contains("holiday") || msg.contains("sick")) {
            return HRIntent.LEAVE;
        }
        if (msg.contains("shift") || msg.contains("working hours")) {
            return HRIntent.SHIFT;
        }
        if (msg.contains("policy") || msg.contains("notice period")) {
            return HRIntent.POLICY;
        }
        return HRIntent.OTHER;
    }

}
