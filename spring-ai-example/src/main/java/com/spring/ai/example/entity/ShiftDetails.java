package com.spring.ai.example.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShiftDetails {

    private Integer id;
    private String shiftType;
    private String startTime;
    private String endTime;

}
