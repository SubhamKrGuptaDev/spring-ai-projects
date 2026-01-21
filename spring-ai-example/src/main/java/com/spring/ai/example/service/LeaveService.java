package com.spring.ai.example.service;

import com.spring.ai.example.entity.LeaveRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveService {

    private List<LeaveRecord> leaveRecords = new ArrayList<>();
    private Long id=3L;
    private final LeaveTypeService leaveTypeService;

    public LeaveService(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
        leaveRecords.add(new LeaveRecord(1L,"2025-12-12","2025-12-14", leaveTypeService.getLeaveTypeById(1L)));
        leaveRecords.add(new LeaveRecord(1L,"2025-12-20","2025-12-22", leaveTypeService.getLeaveTypeById(2L)));
    }

    public List<LeaveRecord> getAll() {
        return this.leaveRecords;
    }

    public LeaveRecord getLeaveById(Long id) {
        return this.leaveRecords.stream().filter(leave -> leave.getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public LeaveRecord createLeave(LeaveRecord leaveRecord) {
        leaveRecord.setId(id++);
        this.leaveRecords.add(leaveRecord);
        return leaveRecord;
    }


}
