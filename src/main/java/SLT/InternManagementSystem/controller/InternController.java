package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.dto.ProjectDto;
import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.service.ApplicantService;
import SLT.InternManagementSystem.service.InternService;
import SLT.InternManagementSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/intern")
public class InternController {
    private final InternService internService;
    private ApplicantService applicantService;
    private ProjectService projectService;
    private static final String RESUME_DIR = "C:/Users/Sandani Gunawardhana/OneDrive/Desktop/SLT/Intern Management System/Frontend/public/Resumes";

    @Autowired
    public InternController(ApplicantService applicantService, InternService internService, ProjectService projectService) {
        this.applicantService = applicantService;
        this.internService = internService;
        this.projectService = projectService;
    }

    @PostMapping("/apply/resumeUpload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("resume") MultipartFile file) {
        try {
            // Create directory if it doesn't exist
            File directory = new File(RESUME_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save file with timestamp to prevent name clashes
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path filePath = Paths.get(RESUME_DIR, fileName);
            Files.write(filePath, file.getBytes());

            // Return URI to frontend
            String fileUri = filePath.toUri().toString();
            System.out.println("Saved resume path: " + filePath);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Upload successful");
            response.put("uri", fileUri);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }


    @PostMapping("/apply")
    public ResponseEntity<ApplicantDto> createApplicant(@RequestBody ApplicantDto applicantDto) {
        ApplicantDto savedApplicant = applicantService.createApplicant(applicantDto);
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }


    @GetMapping("/getIntern/{userId}")
    public ResponseEntity<?> getIntern(@PathVariable int userId) {
        InternDto intern = internService.getIntern(userId);
        if (intern != null) {
            List<ProjectDto> projects = projectService.getProjectsByInternId(intern.getInternId());
            Map<String, Object> response = new HashMap<>();
            response.put("intern", intern);
            response.put("projects", projects);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Intern with userId " + userId + " not found.");
        }
    }
}
