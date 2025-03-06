package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpDto {
    private int id;
    private String email;
    private int otp;
    private LocalDateTime createdAt;

    public int getId() {return id;}
    public String getEmail() {return email;}
    public int getOtp() {return otp;}
    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setId(int id) {this.id = id;}
    public void setOtp(int otp) {this.otp = otp;}
    public void setEmail(String email) {this.email = email;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public OtpDto(int id, String email, int otp, LocalDateTime createdAt) {
        this.id = id;
        this.otp = otp;
        this.email = email;
        this.createdAt = createdAt;
    }
}
