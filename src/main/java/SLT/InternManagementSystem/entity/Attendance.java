package SLT.InternManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "attendance", uniqueConstraints = @UniqueConstraint(columnNames = {"intern_id", "date"}))
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;

    @ManyToOne
    @JoinColumn(name = "intern_id", nullable = false)
    private Intern intern;

//    @ManyToOne
//    @JoinColumn(name = "project_id", nullable = false)
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


    public Attendance() {}


    public Attendance(int attendanceId, Intern intern, String date, int status) {
        this.attendanceId = attendanceId;
        this.intern = intern;
        this.date = date;
        this.status = status;
    }
}
