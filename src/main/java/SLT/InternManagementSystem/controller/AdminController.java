package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.service.ApplicantService;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public AdminController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantDto>> getAllNewApplicants() {
        List<ApplicantDto> applicants = applicantService.getAllApplicants();
        return ResponseEntity.ok(applicants);
    }

    @PutMapping("/setForInterview/{id}")
    public ResponseEntity<?> setApplicantForInterview(@PathVariable int id, @RequestParam String interviewDate, @RequestParam String interviewTime) {
        ApplicantDto updatedApplicant = applicantService.setApplicantForInterview(id, interviewDate, interviewTime);
        return ResponseEntity.ok(updatedApplicant);
    }
}
