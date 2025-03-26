package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {
    private int attendanceId;
    private Intern intern;
//    private Project project;
    private String date;
    private int status;


    public int getAttendanceId() {return attendanceId;}
    public Intern getIntern() {return intern;}
//    public Project getProject() {return project;}
    public String getDate() {return date;}
    public int getStatus() {return status;}


    public void setAttendanceId(int attendanceId) { this.attendanceId = attendanceId; }
    public void setIntern(Intern intern) { this.intern = intern; }
//    public void setProject(Project project) { this.project = project; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(int status) { this.status = status; }


    public AttendanceDto() {}


    public AttendanceDto(int attendanceId, Intern intern, String date, int status) {
        this.attendanceId = attendanceId;
        this.intern = intern;
//        this.project = project;
        this.date = date;
        this.status = status;
    }
}
