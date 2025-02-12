package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/intern")
public class InternController {
    private ApplicantService applicantService;

    @Autowired
    public InternController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicantDto> createApplicant(@RequestBody ApplicantDto applicantDto) {
        ApplicantDto savedApplicant = applicantService.createApplicant(applicantDto);
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }
}
