package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.CreateWorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordDto;
import SLT.InternManagementSystem.dto.WorkRecordStatsDto;
import SLT.InternManagementSystem.service.WorkRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/api/work-records")
@RequiredArgsConstructor
public class WorkRecordController {

    private final WorkRecordService workRecordService;

    @PostMapping
    public ResponseEntity<WorkRecordDto> createWorkRecord(
            @Valid @RequestBody CreateWorkRecordDto createDto,
            Authentication authentication) {

        String userEmail = authentication.getName();
        WorkRecordDto createdRecord = workRecordService.createWorkRecord(createDto, userEmail);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @PutMapping("/{workRecordId}")
    public ResponseEntity<WorkRecordDto> updateWorkRecord(
            @PathVariable Long workRecordId,
            @Valid @RequestBody CreateWorkRecordDto updateDto,
            Authentication authentication) {

        String userEmail = authentication.getName();
        WorkRecordDto updatedRecord = workRecordService.updateWorkRecord(workRecordId, updateDto, userEmail);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{workRecordId}")
    public ResponseEntity<Void> deleteWorkRecord(
            @PathVariable Long workRecordId,
            Authentication authentication) {

        String userEmail = authentication.getName();
        workRecordService.deleteWorkRecord(workRecordId, userEmail);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{workRecordId}")
    public ResponseEntity<WorkRecordDto> getWorkRecordById(
            @PathVariable Long workRecordId,
            Authentication authentication) {

        String userEmail = authentication.getName();
        WorkRecordDto workRecord = workRecordService.getWorkRecordById(workRecordId, userEmail);
        return ResponseEntity.ok(workRecord);
    }

    @GetMapping("/date/{workDate}")
    public ResponseEntity<WorkRecordDto> getWorkRecordByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workDate,
            Authentication authentication) {

        String userEmail = authentication.getName();
        WorkRecordDto workRecord = workRecordService.getWorkRecordByDate(workDate, userEmail);

        if (workRecord == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(workRecord);
    }

    @GetMapping
    public ResponseEntity<List<WorkRecordDto>> getAllWorkRecords(Authentication authentication) {
        String userEmail = authentication.getName();
        List<WorkRecordDto> workRecords = workRecordService.getAllWorkRecordsByIntern(userEmail);
        return ResponseEntity.ok(workRecords);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<WorkRecordDto>> getWorkRecordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Authentication authentication) {

        String userEmail = authentication.getName();
        List<WorkRecordDto> workRecords = workRecordService.getWorkRecordsByDateRange(startDate, endDate, userEmail);
        return ResponseEntity.ok(workRecords);
    }

    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<List<WorkRecordDto>> getWorkRecordsByMonth(
            @PathVariable int year,
            @PathVariable int month,
            Authentication authentication) {

        String userEmail = authentication.getName();
        List<WorkRecordDto> workRecords = workRecordService.getWorkRecordsByMonth(year, month, userEmail);
        return ResponseEntity.ok(workRecords);
    }

    @GetMapping("/stats")
    public ResponseEntity<WorkRecordStatsDto> getWorkRecordStats(Authentication authentication) {
        String userEmail = authentication.getName();
        WorkRecordStatsDto stats = workRecordService.getWorkRecordStats(userEmail);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/{year}/{month}")
    public ResponseEntity<WorkRecordStatsDto> getWorkRecordStatsByMonth(
            @PathVariable int year,
            @PathVariable int month,
            Authentication authentication) {

        String userEmail = authentication.getName();
        WorkRecordStatsDto stats = workRecordService.getWorkRecordStatsByMonth(year, month, userEmail);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/exists/{workDate}")
    public ResponseEntity<Boolean> hasWorkRecordForDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate workDate,
            Authentication authentication) {

        String userEmail = authentication.getName();
        boolean exists = workRecordService.hasWorkRecordForDate(workDate, userEmail);
        return ResponseEntity.ok(exists);
    }
}