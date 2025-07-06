package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.WorkRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkRecordDto {

    @NotNull(message = "Work date is required")
    private LocalDate workDate;

    @NotBlank(message = "Tasks description is required")
    @Size(min = 10, max = 2000, message = "Tasks description must be between 10 and 2000 characters")
    private String tasks;

    @NotNull(message = "Hours worked is required")
    @Min(value = 0, message = "Hours worked must be positive")
    @Max(value = 24, message = "Hours worked cannot exceed 24 hours")
    private Double hoursWorked;

    @NotNull(message = "Department is required")
    private WorkRecord.Department department;

    private String supervisor;

    @Size(max = 1000, message = "Achievements description cannot exceed 1000 characters")
    private String achievements;

    @Size(max = 1000, message = "Challenges description cannot exceed 1000 characters")
    private String challenges;

    @Size(max = 1000, message = "Learnings description cannot exceed 1000 characters")
    private String learnings;

    @NotNull(message = "Status is required")
    private WorkRecord.WorkStatus status;
}