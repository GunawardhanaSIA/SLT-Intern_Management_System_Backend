package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    ProjectDto createProject(int supervisorId, ProjectDto projectDto);
    List<ProjectDto> getAllProjects();
}
