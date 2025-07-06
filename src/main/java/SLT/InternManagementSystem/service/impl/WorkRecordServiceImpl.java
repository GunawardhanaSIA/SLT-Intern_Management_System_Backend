package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.CreateWorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordStatsDto;
import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.entity.WorkRecord;
import SLT.InternManagementSystem.exception.ResourceNotFoundException;
import SLT.InternManagementSystem.exception.UnauthorizedException;
import SLT.InternManagementSystem.mapper.WorkRecordMapper;
import SLT.InternManagementSystem.repository.InternRepository;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.repository.WorkRecordRepository;
import SLT.InternManagementSystem.service.WorkRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkRecordServiceImpl implements WorkRecordService {

    private final WorkRecordRepository workRecordRepository;
    private final InternRepository internRepository;
    private final UserRepository userRepository;
    private final WorkRecordMapper workRecordMapper;

    @Override
    @Transactional
    public WorkRecordDto createWorkRecord(CreateWorkRecordDto createDto, String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);

        // Check if work record already exists for this date
        if (workRecordRepository.existsByInternAndWorkDate(intern, createDto.getWorkDate())) {
            throw new IllegalStateException("Work record already exists for date: " + createDto.getWorkDate());
        }

        WorkRecord workRecord = workRecordMapper.toEntity(createDto, intern);
        WorkRecord savedWorkRecord = workRecordRepository.save(workRecord);

        return workRecordMapper.toDto(savedWorkRecord);
    }

    @Override
    @Transactional
    public WorkRecordDto updateWorkRecord(Long workRecordId, CreateWorkRecordDto updateDto, String userEmail) {
        WorkRecord workRecord = getWorkRecordByIdAndValidateOwnership(workRecordId, userEmail);

        // Check if another work record exists for the new date (if date is being
        // changed)
        if (!workRecord.getWorkDate().equals(updateDto.getWorkDate())) {
            Intern intern = getInternByUserEmail(userEmail);
            if (workRecordRepository.existsByInternAndWorkDate(intern, updateDto.getWorkDate())) {
                throw new IllegalStateException("Work record already exists for date: " + updateDto.getWorkDate());
            }
        }

        workRecordMapper.updateEntity(workRecord, updateDto);
        WorkRecord updatedWorkRecord = workRecordRepository.save(workRecord);

        return workRecordMapper.toDto(updatedWorkRecord);
    }

    @Override
    @Transactional
    public void deleteWorkRecord(Long workRecordId, String userEmail) {
        WorkRecord workRecord = getWorkRecordByIdAndValidateOwnership(workRecordId, userEmail);
        workRecordRepository.delete(workRecord);
    }

    @Override
    public WorkRecordDto getWorkRecordById(Long workRecordId, String userEmail) {
        WorkRecord workRecord = getWorkRecordByIdAndValidateOwnership(workRecordId, userEmail);
        return workRecordMapper.toDto(workRecord);
    }

    @Override
    public WorkRecordDto getWorkRecordByDate(LocalDate workDate, String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);
        Optional<WorkRecord> workRecord = workRecordRepository.findByInternAndWorkDate(intern, workDate);

        return workRecord.map(workRecordMapper::toDto).orElse(null);
    }

    @Override
    public List<WorkRecordDto> getAllWorkRecordsByIntern(String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);
        List<WorkRecord> workRecords = workRecordRepository.findByInternOrderByWorkDateDesc(intern);

        return workRecords.stream()
                .map(workRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkRecordDto> getWorkRecordsByDateRange(LocalDate startDate, LocalDate endDate, String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);
        List<WorkRecord> workRecords = workRecordRepository.findByInternAndWorkDateBetweenOrderByWorkDateDesc(
                intern, startDate, endDate);

        return workRecords.stream()
                .map(workRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkRecordDto> getWorkRecordsByMonth(int year, int month, String userEmail) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        return getWorkRecordsByDateRange(startDate, endDate, userEmail);
    }

    @Override
    public WorkRecordStatsDto getWorkRecordStats(String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);

        Double totalHours = workRecordRepository.getTotalHoursByIntern(intern);
        Long totalWorkDays = workRecordRepository.getTotalWorkDaysByIntern(intern);

        LocalDate now = LocalDate.now();
        Double monthlyHours = workRecordRepository.getMonthlyHoursByIntern(intern, now.getYear(), now.getMonthValue());
        Long monthlyWorkDays = workRecordRepository.getMonthlyWorkDaysByIntern(intern, now.getYear(),
                now.getMonthValue());

        return WorkRecordStatsDto.builder()
                .totalHours(totalHours != null ? totalHours : 0.0)
                .monthlyHours(monthlyHours != null ? monthlyHours : 0.0)
                .totalWorkDays(totalWorkDays != null ? totalWorkDays.intValue() : 0)
                .monthlyWorkDays(monthlyWorkDays != null ? monthlyWorkDays.intValue() : 0)
                .currentYear(now.getYear())
                .currentMonth(now.getMonthValue())
                .build();
    }

    @Override
    public WorkRecordStatsDto getWorkRecordStatsByMonth(int year, int month, String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);

        Double totalHours = workRecordRepository.getTotalHoursByIntern(intern);
        Long totalWorkDays = workRecordRepository.getTotalWorkDaysByIntern(intern);

        Double monthlyHours = workRecordRepository.getMonthlyHoursByIntern(intern, year, month);
        Long monthlyWorkDays = workRecordRepository.getMonthlyWorkDaysByIntern(intern, year, month);

        return WorkRecordStatsDto.builder()
                .totalHours(totalHours != null ? totalHours : 0.0)
                .monthlyHours(monthlyHours != null ? monthlyHours : 0.0)
                .totalWorkDays(totalWorkDays != null ? totalWorkDays.intValue() : 0)
                .monthlyWorkDays(monthlyWorkDays != null ? monthlyWorkDays.intValue() : 0)
                .currentYear(year)
                .currentMonth(month)
                .build();
    }

    @Override
    public boolean hasWorkRecordForDate(LocalDate workDate, String userEmail) {
        Intern intern = getInternByUserEmail(userEmail);
        return workRecordRepository.existsByInternAndWorkDate(intern, workDate);
    }

    private Intern getInternByUserEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));

        Intern intern = internRepository.findInternByUser(user);
        if (intern == null) {
            throw new ResourceNotFoundException("Intern not found for user: " + userEmail);
        }
        return intern;
    }

    private WorkRecord getWorkRecordByIdAndValidateOwnership(Long workRecordId, String userEmail) {
        WorkRecord workRecord = workRecordRepository.findById(workRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Work record not found with ID: " + workRecordId));

        Intern intern = getInternByUserEmail(userEmail);

        if (workRecord.getIntern().getInternId() != intern.getInternId()) {
            throw new UnauthorizedException("You are not authorized to access this work record");
        }

        return workRecord;
    }
}