package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.CreateWorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordDto;
import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.WorkRecord;
import org.springframework.stereotype.Component;

@Component
public class WorkRecordMapper {

    public WorkRecordDto toDto(WorkRecord workRecord) {
        if (workRecord == null) {
            return null;
        }

        return WorkRecordDto.builder()
                .workRecordId(workRecord.getWorkRecordId())
                .internId(workRecord.getIntern().getInternId())
                .workDate(workRecord.getWorkDate())
                .tasks(workRecord.getTasks())
                .hoursWorked(workRecord.getHoursWorked())
                .department(workRecord.getDepartment())
                .supervisor(workRecord.getSupervisor())
                .achievements(workRecord.getAchievements())
                .challenges(workRecord.getChallenges())
                .learnings(workRecord.getLearnings())
                .status(workRecord.getStatus())
                .approvalStatus(workRecord.getApprovalStatus())
                .createdAt(workRecord.getCreatedAt())
                .updatedAt(workRecord.getUpdatedAt())
                .build();
    }

    public WorkRecord toEntity(CreateWorkRecordDto createDto, Intern intern) {
        if (createDto == null) {
            return null;
        }

        return WorkRecord.builder()
                .intern(intern)
                .workDate(createDto.getWorkDate())
                .tasks(createDto.getTasks())
                .hoursWorked(createDto.getHoursWorked())
                .department(createDto.getDepartment())
                .supervisor(createDto.getSupervisor())
                .achievements(createDto.getAchievements())
                .challenges(createDto.getChallenges())
                .learnings(createDto.getLearnings())
                .status(createDto.getStatus())
                .approvalStatus(WorkRecord.ApprovalStatus.PENDING)
                .build();
    }

    public void updateEntity(WorkRecord workRecord, CreateWorkRecordDto updateDto) {
        if (workRecord == null || updateDto == null) {
            return;
        }

        workRecord.setWorkDate(updateDto.getWorkDate());
        workRecord.setTasks(updateDto.getTasks());
        workRecord.setHoursWorked(updateDto.getHoursWorked());
        workRecord.setDepartment(updateDto.getDepartment());
        workRecord.setSupervisor(updateDto.getSupervisor());
        workRecord.setAchievements(updateDto.getAchievements());
        workRecord.setChallenges(updateDto.getChallenges());
        workRecord.setLearnings(updateDto.getLearnings());
        workRecord.setStatus(updateDto.getStatus());
    }
}