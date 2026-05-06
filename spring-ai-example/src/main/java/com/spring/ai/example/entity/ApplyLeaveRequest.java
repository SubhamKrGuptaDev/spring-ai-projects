package com.spring.ai.example.entity;

public record ApplyLeaveRequest(Long accountNo,
                                String leaveTypeName,
                                String startDate,
                                String endDate) {
}
