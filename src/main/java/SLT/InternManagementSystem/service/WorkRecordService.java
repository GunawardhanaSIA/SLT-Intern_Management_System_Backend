package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.CreateWorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordStatsDto;

import java.time.LocalDate;
import java.util.List;

public interface WorkRecordService {

    WorkRecordDto createWorkRecord(CreateWorkRecordDto createDto, String userEmail);

    WorkRecordDto updateWorkRecord(Long workRecordId, CreateWorkRecordDto updateDto, String userEmail);

    void deleteWorkRecord(Long workRecordId, String userEmail);

    WorkRecordDto getWorkRecordById(Long workRecordId, String userEmail);

    WorkRecordDto getWorkRecordByDate(LocalDate workDate, String userEmail);

    List<WorkRecordDto> getAllWorkRecordsByIntern(String userEmail);

    List<WorkRecordDto> getWorkRecordsByDateRange(LocalDate startDate, LocalDate endDate, String userEmail);

    List<WorkRecordDto> getWorkRecordsByMonth(int year, int month, String userEmail);

    WorkRecordStatsDto getWorkRecordStats(String userEmail);

    WorkRecordStatsDto getWorkRecordStatsByMonth(int year, int month, String userEmail);

    boolean hasWorkRecordForDate(LocalDate workDate, String userEmail);
}