package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SupervisorDto {
    private int supervisorId;
    private User user;
    private String name;
    private Long mobileNumber;
    private String email;
    private String specialization;
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


    public SupervisorDto() {}

    public SupervisorDto(int supervisorId,
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
