package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.AttendanceDto;
import SLT.InternManagementSystem.dto.InternDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    AttendanceDto saveAttendance(int internId, String date);

    List<AttendanceDto> getInternAttendance(int internId);

//    List<AttendanceDto> getAttendanceByProjectId(int projectId);
}
