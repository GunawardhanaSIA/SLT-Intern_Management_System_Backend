package SLT.InternManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;
    private String newEmail;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    public Long getId() {return id;}
    public String getToken() {return token;}
    public LocalDateTime getExpirationTime() {return expirationTime;}
    public String getNewEmail() {return newEmail;}
    public User getUser() {return user;}

    public VerificationToken(Long id, String token, LocalDateTime expirationTime, String newEmail, User user) {
        this.id = id;
        this.token = token;
        this.expirationTime = expirationTime;
        this.newEmail = newEmail;
        this.user = user;
    }

}
