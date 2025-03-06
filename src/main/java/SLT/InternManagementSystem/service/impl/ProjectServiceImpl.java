package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.ProjectDto;
import SLT.InternManagementSystem.entity.*;
import SLT.InternManagementSystem.mapper.ProjectMapper;
import SLT.InternManagementSystem.repository.ProjectRepository;
import SLT.InternManagementSystem.repository.SupervisorRepository;
import SLT.InternManagementSystem.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final SupervisorRepository supervisorRepository;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(SupervisorRepository supervisorRepository, ProjectRepository projectRepository) {
        this.supervisorRepository = supervisorRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDto createProject(int supervisorId, ProjectDto projectDto) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));
//        Intern intern1 = internRepository.findByInternId(member1)
//                .orElseThrow(() -> new RuntimeException("Member1 not found"));
//        Intern intern2 = internRepository.findByInternId(member2)
//                .orElseThrow(() -> new RuntimeException("Member2 not found"));
//        Intern intern3 = internRepository.findByInternId(member3)
//                .orElseThrow(() -> new RuntimeException("Member3 not found"));
//        Intern intern4 = internRepository.findByInternId(member4)
//                .orElseThrow(() -> new RuntimeException("Member4 not found"));

        Project project = ProjectMapper.mapToProject(projectDto);
        project.setSupervisor(supervisor);
        Project savedProject = projectRepository.save(project);
        return ProjectMapper.mapToProjectDto(savedProject);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map((project) -> ProjectMapper.mapToProjectDto(project)).collect(Collectors.toList());
    }
}
