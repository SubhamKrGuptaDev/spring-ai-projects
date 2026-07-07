package com.spring.ai.example.aiFunctions;


import com.spring.ai.example.entity.LeavePolicy;
import com.spring.ai.example.service.LeavePolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("getShiftDetails")
@Slf4j
@Description("""
        Get Shift Details for current Employee
        ShiftDetails Object contains shift related all the information 
        shiftType means shift name, 
        startTime when shift will be start, 
        endTime when shift will be complete
        response based on this 3 values don't need inform about ID  
        """)
public class GetShiftDetailsFunction implements Function<Void, String> {

    private final LeavePolicyService leavePolicyService;

    public GetShiftDetailsFunction(LeavePolicyService leavePolicyService) {
        this.leavePolicyService = leavePolicyService;
    }

    @Override
    public String apply(Void unused) {
        LeavePolicy leavePolicy = leavePolicyService.getLeavePolicy();
        return leavePolicy.getShiftDetails().toString();
    }
}
