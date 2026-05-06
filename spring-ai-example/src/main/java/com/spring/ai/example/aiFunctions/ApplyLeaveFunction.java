package com.spring.ai.example.aiFunctions;

import com.spring.ai.example.entity.ApplyLeaveRequest;
import com.spring.ai.example.service.LeaveService;
import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("applyLeave")
@Slf4j
@Description("""
Apply leave for an employee based on the ApplyLeaveRequest details provided.
ApplyLeaveRequest contains values Long accountNo,String leaveTypeName,String startDate,String endDate
for leave.
""")
public class ApplyLeaveFunction implements Function<ApplyLeaveRequest, String> {

    private final LeaveService leaveService;

    public ApplyLeaveFunction(LeaveService leaveService) {
        this.leaveService = leaveService;
    }


    @Override
    public String apply(ApplyLeaveRequest applyLeaveRequest) {
        log.info("🔥 FUNCTION CALLED: {}", applyLeaveRequest);
        leaveService.applyLeave(applyLeaveRequest);
        log.info("Leave applied: {}", applyLeaveRequest);

        return "Leave applied successfully from "
                + applyLeaveRequest.startDate() + " to " + applyLeaveRequest.endDate();
    }
}
