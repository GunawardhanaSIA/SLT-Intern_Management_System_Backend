package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VerificationTokenDto {
    private Long id;
    private String token;
    private LocalDateTime expiryDate;
    private User user;

    public Long getId() {return id;}
    public String getToken() {return token;}
    public LocalDateTime getExpiryDate() {return expiryDate;}
    public User getUser() {return user;}

    public void setId(Long id) {this.id = id;}
    public void setToken(String token) {this.token = token;}
    public void setExpiryDate(LocalDateTime expiryDate) {this.expiryDate = expiryDate;}
    public void setUser(User user) {this.user = user;}

    public VerificationTokenDto(Long id, String token, LocalDateTime expiryDate, User user) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public VerificationTokenDto() {}
}
