package com.spring.ai.example.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeavePolicy {

    private Integer id;
    private Integer totalLeaves;
    private ShiftDetails shiftDetails;
    private Integer noticePeriodInDays;

}
