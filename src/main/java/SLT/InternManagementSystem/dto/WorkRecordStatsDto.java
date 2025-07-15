package SLT.InternManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkRecordStatsDto {

    private Double totalHours;
    private Double monthlyHours;
    private Integer totalWorkDays;
    private Integer monthlyWorkDays;
    private Integer currentYear;
    private Integer currentMonth;
}