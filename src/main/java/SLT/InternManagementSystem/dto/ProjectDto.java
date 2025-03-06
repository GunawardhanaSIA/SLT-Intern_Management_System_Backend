package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Supervisor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private int projectId;
    private String groupName;
    private String projectName;
    private String description;
    private String technology;
    private String startDate;
    private String targetDate;
    private Supervisor supervisor;
    private List<Intern> interns;
//    private Intern member1;
//    private Intern member2;
//    private Intern member3;
//    private Intern member4;


    public int getProjectId() {
        return projectId;
    }
    public String getGroupName() {
        return groupName;
    }
    public String getProjectName() { return projectName; }
    public String getDescription() { return description; }
    public String getTechnology() { return technology; }
    public String getStartDate() { return startDate; }
    public String getTargetDate() { return targetDate; }
    public Supervisor getSupervisor() { return supervisor; }
    public List<Intern> getInterns() { return interns; }



    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public void setDescription(String description) { this.description = description; }
    public void setTechnology(String technology) { this.technology = technology; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setTargetDate(String targetDate) { this.targetDate = targetDate; }
    public void setSupervisor(Supervisor supervisor) { this.supervisor = supervisor; }
    public void setInterns(List<Intern> interns) { this.interns = interns; }


    public ProjectDto() {}

    public ProjectDto(int projectId,
                   String groupName,
                   String projectName,
                   String description,
                   String technology,
                   String startDate,
                   String targetDate,
                   Supervisor supervisor,
                   List<Intern> interns) {
        this.projectId = projectId;
        this.groupName = groupName;
        this.projectName = projectName;
        this.description = description;
        this.technology = technology;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.supervisor = supervisor;
        this.interns = interns;
    }
}
