package SLT.InternManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supervisors")
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supervisorId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private Long mobileNumber;
    private String email;
    private String specialization;

    @Column(columnDefinition = "INT DEFAULT 1")
    private int state;


    public int getSupervisorId() {
        return supervisorId;
    }
    public User getUser() { return user; }
    public String getName() {
        return name;
    }
    public Long getMobileNumber() {
        return mobileNumber;
    }
    public String getEmail() { return email; }
    public String getSpecialization() { return specialization; }
    public int getState() { return state; }



    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }
    public void setUser(User user) { this.user = user; }
    public void setName(String name) { this.name = name; }
    public void setMobileNumber(Long mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setState(int state) { this.state = state; }


    public Supervisor() {}

    public Supervisor(int supervisorId,
                  User user,
                  String name,
                  Long mobileNumber,
                  String email,
                  String specialization,
                  int state) {
        this.supervisorId = supervisorId;
        this.user = user;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.specialization = specialization;
        this.state = state;
    }
}
