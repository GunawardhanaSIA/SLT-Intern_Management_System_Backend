package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Attendance;
import SLT.InternManagementSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByProjectAndDate(Project project, String date);

    List<Attendance> findByProject(Project project);
}
