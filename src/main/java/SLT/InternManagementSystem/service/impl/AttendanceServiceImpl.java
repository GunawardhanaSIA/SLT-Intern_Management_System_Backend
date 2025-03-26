package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.AttendanceDto;
import SLT.InternManagementSystem.entity.*;
import SLT.InternManagementSystem.mapper.AttendanceMapper;
import SLT.InternManagementSystem.mapper.InternMapper;
import SLT.InternManagementSystem.mapper.ProjectMapper;
import SLT.InternManagementSystem.repository.AttendanceRepository;
import SLT.InternManagementSystem.repository.InternRepository;
import SLT.InternManagementSystem.repository.ProjectRepository;
import SLT.InternManagementSystem.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final InternRepository internRepository;
    private final ProjectRepository projectRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, InternRepository internRepository, ProjectRepository projectRepository) {
        this.attendanceRepository = attendanceRepository;
        this.internRepository = internRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public AttendanceDto saveAttendance(int internId, String date) {
        Intern intern = internRepository.findByInternId(internId)
                    .orElseThrow(() -> new RuntimeException("Intern not found"));
//        Project project = projectRepository.findByProjectId(projectId)
//                .orElseThrow(() -> new RuntimeException("Project not found"));

        Attendance attendance = new Attendance();
        attendance.setIntern(intern);
//        attendance.setProject(project);
        attendance.setDate(date);
        attendance.setStatus(1);

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return AttendanceMapper.mapToAttendanceDto(savedAttendance);
    }

    @Override
    public List<AttendanceDto> getInternAttendance(int internId) {
        Intern intern = internRepository.findByInternId(internId)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        List<Attendance> attendances = attendanceRepository.findByIntern(intern);
        return attendances.stream().map((attendance) -> AttendanceMapper.mapToAttendanceDto(attendance)).collect(Collectors.toList());
    }

//    @Override
//    public List<AttendanceDto> saveAttendance(List<AttendanceDto> attendanceDtos) {
//        return attendanceDtos.stream().map(request -> {
//            Intern intern = internRepository.findByInternId(request.getIntern().getInternId())
//                    .orElseThrow(() -> new RuntimeException("Intern not found"));
//            Attendance attendance = AttendanceMapper.mapToAttendance(request);
//            attendance.setIntern(intern);
//            Attendance savedAttendance = attendanceRepository.save(attendance);
//            return AttendanceMapper.mapToAttendanceDto(savedAttendance);
//        }).collect(Collectors.toList());  // ✅ Returning the list
//    }

//    @Override
//    public List<AttendanceDto> getAttendanceByProjectId(int projectId) {
//        Project project = projectRepository.findByProjectId(projectId)
//                .orElseThrow(() -> new RuntimeException("Project not found"));
//        List<Attendance> projectAttendanceRecords = attendanceRepository.findByProject(project);
//        return projectAttendanceRecords.stream().map((projectAttendanceRecord) -> AttendanceMapper.mapToAttendanceDto(projectAttendanceRecord)).collect(Collectors.toList());
//
//    }
}
