package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.*;
import SLT.InternManagementSystem.entity.Attendance;
import SLT.InternManagementSystem.service.AttendanceService;
import SLT.InternManagementSystem.service.InternService;
import SLT.InternManagementSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {
    private final InternService internService;
    private final ProjectService projectService;
    private final AttendanceService attendanceService;

    public SupervisorController(InternService internService, ProjectService projectService, AttendanceService attendanceService) {
        this.internService = internService;
        this.projectService = projectService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/myInterns")
    public ResponseEntity<List<InternDto>> getAllInterns() {
        List<InternDto> interns = internService.getAllInterns();
        return ResponseEntity.ok(interns);
    }

    @GetMapping("/myProjects")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/createProject")
    public ResponseEntity<ProjectDto> createProject(@RequestParam int supervisorId , @RequestBody ProjectDto projectDto) {
        System.out.println("Received request to create a project");
        System.out.println("Supervisor ID: " + supervisorId);
        System.out.println("Interns: " + projectDto.getInterns());
        ProjectDto savedProject = projectService.createProject(supervisorId, projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @PostMapping("/attendance")
    public ResponseEntity<?> markAttendance(@RequestParam int internId, @RequestParam String date) {
        System.out.println("internid of attendance marking"+ internId);
        System.out.println(date);
        attendanceService.saveAttendance(internId, date);
        return ResponseEntity.ok("Attendance saved successfully!");
    }

    @GetMapping("/attendance/{internId}")
    public ResponseEntity<List<AttendanceDto>> getInternAttendance(@PathVariable int internId) {
        List<AttendanceDto> attendance = attendanceService.getInternAttendance(internId);
        return ResponseEntity.ok(attendance);
    }

//    @GetMapping("/attendance/{projectId}")
//    public ResponseEntity<List<AttendanceDto>> getAttendanceByProject(@PathVariable int projectId) {
//        List<AttendanceDto> attendanceRecords = attendanceService.getAttendanceByProjectId(projectId);
//        return ResponseEntity.ok(attendanceRecords);
//    }
}
