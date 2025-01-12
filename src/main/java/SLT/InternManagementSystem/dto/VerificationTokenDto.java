package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationTokenDto {
    private Long id;
    private String token;
    private LocalDateTime expirationTime;
    private String newEmail;
    private User user;

    public Long getId() {return id;}
    public String getToken() {return token;}
    public LocalDateTime getExpirationTime() {return expirationTime;}
    public String getNewEmail() {return newEmail;}
    public User getUser() {return user;}

    public VerificationTokenDto(Long id, String token, LocalDateTime expirationTime, String newEmail, User user) {
        this.id = id;
        this.token = token;
        this.expirationTime = expirationTime;
        this.newEmail = newEmail;
        this.user = user;
    }
}
