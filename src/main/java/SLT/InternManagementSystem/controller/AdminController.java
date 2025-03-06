package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.service.ApplicantService;
import SLT.InternManagementSystem.service.InternService;
import SLT.InternManagementSystem.service.SupervisorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ApplicantService applicantService;
    private final InternService internService;
    private final SupervisorService supervisorService;

    public AdminController(ApplicantService applicantService, InternService internService, SupervisorService supervisorService) {
        this.applicantService = applicantService;
        this.internService = internService;
        this.supervisorService = supervisorService;
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
    public ResponseEntity<?> setApplicantForInterview(@PathVariable int id, @RequestParam String interviewDate, @RequestParam String interviewTime) {
        ApplicantDto updatedApplicant = applicantService.setApplicantForInterview(id, interviewDate, interviewTime);
        return ResponseEntity.ok(updatedApplicant);
    }

    @PostMapping("/createIntern")
    public ResponseEntity<InternDto> createIntern(@RequestParam int userId , @RequestParam int applicantId, @RequestParam int supervisorId, @RequestBody InternDto internDto) {
        System.out.println("Received request to create an intern");
        System.out.println("User ID: " + userId);
        System.out.println("Applicant ID: " + applicantId);
        System.out.println("Supervisor ID: " + supervisorId);
        InternDto savedIntern = internService.createIntern(userId, applicantId, supervisorId, internDto);
        return new ResponseEntity<>(savedIntern, HttpStatus.CREATED);
    }

}
