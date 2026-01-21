package com.spring.ai.example.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeaveRecord {

    private Long id;
    private String startDate;
    private String endDate;
    private LeaveType leaveType;

}
