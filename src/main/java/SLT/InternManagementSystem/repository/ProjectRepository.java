package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByProjectId(int projectId);
}
