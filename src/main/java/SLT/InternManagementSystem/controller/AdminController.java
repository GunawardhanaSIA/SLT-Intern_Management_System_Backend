package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.dto.ReportDto;
import SLT.InternManagementSystem.service.ApplicantService;
import SLT.InternManagementSystem.service.InternService;
import SLT.InternManagementSystem.service.SupervisorService;
import SLT.InternManagementSystem.service.ReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ApplicantService applicantService;
    private final InternService internService;
    private final SupervisorService supervisorService;
    private final ReportService reportService;

    public AdminController(ApplicantService applicantService, InternService internService,
            SupervisorService supervisorService, ReportService reportService) {
        this.applicantService = applicantService;
        this.internService = internService;
        this.supervisorService = supervisorService;
        this.reportService = reportService;
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantDto>> getAllNewApplicants() {
        List<ApplicantDto> applicants = applicantService.getAllApplicants();
        return ResponseEntity.ok(applicants);
    }

    @GetMapping("/supervisors")
    public ResponseEntity<List<SupervisorDto>> getAllSupervisors() {
        List<SupervisorDto> supervisors = supervisorService.getAllSupervisors();
        return ResponseEntity.ok(supervisors);
    }

    @PutMapping("/setForInterview/{id}")
    public ResponseEntity<?> setApplicantForInterview(@PathVariable int id, @RequestParam String interviewDate,
            @RequestParam String interviewTime) {
        ApplicantDto updatedApplicant = applicantService.setApplicantForInterview(id, interviewDate, interviewTime);
        return ResponseEntity.ok(updatedApplicant);
    }

    @PostMapping("/createIntern")
    public ResponseEntity<InternDto> createIntern(@RequestParam int userId, @RequestParam int applicantId,
            @RequestParam int supervisorId, @RequestBody InternDto internDto) {
        System.out.println("Received request to create an intern");
        System.out.println("User ID: " + userId);
        System.out.println("Applicant ID: " + applicantId);
        System.out.println("Supervisor ID: " + supervisorId);
        InternDto savedIntern = internService.createIntern(userId, applicantId, supervisorId, internDto);
        return new ResponseEntity<>(savedIntern, HttpStatus.CREATED);
    }

    // Intern Management Endpoints
    @GetMapping("/interns")
    public ResponseEntity<List<InternDto>> getAllInterns() {
        List<InternDto> interns = internService.getAllInterns();
        return ResponseEntity.ok(interns);
    }

    @GetMapping("/interns/{id}")
    public ResponseEntity<InternDto> getInternById(@PathVariable int id) {
        InternDto intern = internService.getInternById(id);
        return ResponseEntity.ok(intern);
    }

    @PutMapping("/interns/{id}")
    public ResponseEntity<InternDto> updateIntern(@PathVariable int id, @RequestBody InternDto internDto) {
        InternDto updatedIntern = internService.updateIntern(id, internDto);
        return ResponseEntity.ok(updatedIntern);
    }

    @DeleteMapping("/interns/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable int id) {
        internService.deleteIntern(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/interns/{id}/status")
    public ResponseEntity<InternDto> updateInternStatus(@PathVariable int id, @RequestParam int status) {
        InternDto updatedIntern = internService.updateInternStatus(id, status);
        return ResponseEntity.ok(updatedIntern);
    }

    // Supervisor Management Endpoints
    @GetMapping("/supervisors/{id}")
    public ResponseEntity<SupervisorDto> getSupervisorById(@PathVariable int id) {
        SupervisorDto supervisor = supervisorService.getSupervisorById(id);
        return ResponseEntity.ok(supervisor);
    }

    @PostMapping("/supervisors")
    public ResponseEntity<SupervisorDto> createSupervisor(@RequestBody SupervisorDto supervisorDto) {
        SupervisorDto savedSupervisor = supervisorService.createSupervisor(supervisorDto);
        return new ResponseEntity<>(savedSupervisor, HttpStatus.CREATED);
    }

    @PutMapping("/supervisors/{id}")
    public ResponseEntity<SupervisorDto> updateSupervisor(@PathVariable int id,
            @RequestBody SupervisorDto supervisorDto) {
        SupervisorDto updatedSupervisor = supervisorService.updateSupervisor(id, supervisorDto);
        return ResponseEntity.ok(updatedSupervisor);
    }

    @DeleteMapping("/supervisors/{id}")
    public ResponseEntity<Void> deleteSupervisor(@PathVariable int id) {
        supervisorService.deleteSupervisor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/supervisors/{id}/status")
    public ResponseEntity<SupervisorDto> updateSupervisorStatus(@PathVariable int id, @RequestParam int status) {
        SupervisorDto updatedSupervisor = supervisorService.updateSupervisorStatus(id, status);
        return ResponseEntity.ok(updatedSupervisor);
    }

    // Report Management Endpoints
    @GetMapping("/reports")
    public ResponseEntity<ReportDto> getComprehensiveReport() {
        ReportDto report = reportService.generateComprehensiveReport();
        return ResponseEntity.ok(report);
    }

}
