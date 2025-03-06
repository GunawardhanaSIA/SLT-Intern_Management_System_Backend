package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.service.ApplicantService;
import SLT.InternManagementSystem.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/intern")
public class InternController {
    private final InternService internService;
    private ApplicantService applicantService;

    @Autowired
    public InternController(ApplicantService applicantService, InternService internService) {
        this.applicantService = applicantService;
        this.internService = internService;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicantDto> createApplicant(@RequestBody ApplicantDto applicantDto) {
        ApplicantDto savedApplicant = applicantService.createApplicant(applicantDto);
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }

    @GetMapping("/getIntern/{email}")
    public ResponseEntity<?> getIntern(@PathVariable String email) {
        InternDto intern = internService.getIntern(email);

        if (intern != null) {
            return ResponseEntity.ok(intern);
        } else {
            System.out.println("Intern not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Intern with email " + email + " not found.");
        }
    }
}
