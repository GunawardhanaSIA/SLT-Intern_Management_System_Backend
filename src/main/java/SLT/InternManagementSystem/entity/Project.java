package SLT.InternManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    private String groupName;
    private String projectName;
    private String description;
    private String technology;
    private String startDate;
    private String targetDate;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = false)
    private Supervisor supervisor;

    @ManyToMany
    @JoinTable(
            name = "project_interns", // Create a separate table to manage the relation
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "intern_id")
    )
    private List<Intern> interns; // List to hold multiple interns

//    @ManyToOne
//    @JoinColumn(name = "intern_id", nullable = false)
//    private Intern member1;
//
//    @ManyToOne
//    @JoinColumn(name = "intern_id", nullable = false)
//    private Intern member2;
//
//    @ManyToOne
//    @JoinColumn(name = "intern_id", nullable = false)
//    private Intern member3;
//
//    @ManyToOne
//    @JoinColumn(name = "intern_id", nullable = false)
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


    public Project() {}

    public Project(int projectId,
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
