package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.dto.ProjectDto;
import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Project;

public class ProjectMapper {
    public static ProjectDto mapToProjectDto(Project project) {
        return new ProjectDto(
                project.getProjectId(),
                project.getGroupName(),
                project.getProjectName(),
                project.getDescription(),
                project.getTechnology(),
                project.getStartDate(),
                project.getTargetDate(),
                project.getSupervisor(),
                project.getInterns()
        );
    }

    public static Project mapToProject(ProjectDto projectDto) {
        return new Project(
                projectDto.getProjectId(),
                projectDto.getGroupName(),
                projectDto.getProjectName(),
                projectDto.getDescription(),
                projectDto.getTechnology(),
                projectDto.getStartDate(),
                projectDto.getTargetDate(),
                projectDto.getSupervisor(),
                projectDto.getInterns()
        );
    }
}
