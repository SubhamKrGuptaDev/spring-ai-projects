package com.spring.ai.example.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveRecord {

    private Long id;
    private Long accountNo;
    private String startDate;
    private String endDate;
    private LeaveType leaveType;

    public LeaveRecord(Long accountNo, String startDate, String endDate, LeaveType leaveType) {
        this.accountNo = accountNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
    }

}
