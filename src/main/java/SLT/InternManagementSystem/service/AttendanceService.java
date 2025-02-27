package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.AttendanceDto;
import SLT.InternManagementSystem.dto.InternDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    AttendanceDto saveAttendance(int internId, int projectId, String date, Boolean status);

    List<AttendanceDto> getAttendanceByProjectId(int projectId);
}
