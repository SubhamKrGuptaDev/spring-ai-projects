package com.spring.ai.example.service;

import com.spring.ai.example.entity.LeavePolicy;
import com.spring.ai.example.entity.ShiftDetails;
import org.springframework.stereotype.Service;

@Service
public class LeavePolicyService {

    public LeavePolicy getLeavePolicy() {
        ShiftDetails shift = new ShiftDetails(
                1, "HARD-CORE-Coder", "08:00", "19:30"
        );

        return new LeavePolicy(
                1,
                40,
                shift,
                70
        );
    }

}
