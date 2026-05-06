package com.spring.ai.example.controller;

import com.spring.ai.example.entity.LeaveRecord;
import com.spring.ai.example.service.LeaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }


    @GetMapping
    public List<LeaveRecord> getAll() {
        return leaveService.getAll();
    }

}
