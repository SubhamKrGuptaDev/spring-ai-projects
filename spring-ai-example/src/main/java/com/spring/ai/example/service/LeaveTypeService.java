package com.spring.ai.example.service;

import com.spring.ai.example.entity.LeaveType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveTypeService {

    private static List<LeaveType> leaveTypes = new ArrayList<>();
    private static Long id = 3L;

    static {
        leaveTypes.add(new LeaveType(1L, "Sick Leave"));
        leaveTypes.add(new LeaveType(2L, "Casual Leave"));
    }

    public List<LeaveType> getAllLeaveType() {
        return leaveTypes;
    }

    public LeaveType getLeaveTypeById(Long id) {
        return leaveTypes.stream()
                .filter(leaveType -> leaveType.getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public LeaveType createLeaveType(LeaveType leaveType) {
        leaveType.setId(id++);
        this.leaveTypes.add(leaveType);
        return leaveType;
    }



}
