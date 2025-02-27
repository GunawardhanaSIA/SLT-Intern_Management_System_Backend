package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {
    private int attendanceId;
    private Intern intern;
    private Project project;
    private String date;
    private Boolean status;


    public int getAttendanceId() {return attendanceId;}
    public Intern getIntern() {return intern;}
    public Project getProject() {return project;}
    public String getDate() {return date;}
    public Boolean getStatus() {return status;}


    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }
    public void setIntern(Intern intern) { this.intern = intern; }
    public void setProject(Project project) { this.project = project; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(Boolean status) { this.status = status; }


    public AttendanceDto() {}


    public AttendanceDto(int attendanceId, Intern intern, Project project, String date, Boolean status) {
        this.attendanceId = attendanceId;
        this.intern = intern;
        this.project = project;
        this.date = date;
        this.status = status;
    }
}
