package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.AttendanceDto;
import SLT.InternManagementSystem.entity.Attendance;

public class AttendanceMapper {
    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {
        return new AttendanceDto(
                attendance.getAttendanceId(),
                attendance.getIntern(),
                attendance.getProject(),
                attendance.getDate(),
                attendance.getStatus()
        );
    }

    public static Attendance mapToAttendance(AttendanceDto attendanceDto) {
        return new Attendance(
                attendanceDto.getAttendanceId(),
                attendanceDto.getIntern(),
                attendanceDto.getProject(),
                attendanceDto.getDate(),
                attendanceDto.getStatus()
        );
    }
}
