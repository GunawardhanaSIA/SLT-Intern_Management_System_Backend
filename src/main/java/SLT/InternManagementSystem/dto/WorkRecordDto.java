package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.WorkRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkRecordDto {

    private Long workRecordId;
    private Integer internId;
    private LocalDate workDate;
    private String tasks;
    private Double hoursWorked;
    private WorkRecord.Department department;
    private String supervisor;
    private String achievements;
    private String challenges;
    private String learnings;
    private WorkRecord.WorkStatus status;
    private WorkRecord.ApprovalStatus approvalStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}