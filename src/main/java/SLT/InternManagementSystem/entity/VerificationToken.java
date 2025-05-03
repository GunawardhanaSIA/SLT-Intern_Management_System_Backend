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
    private LocalDateTime expiryDate;

    @OneToOne
    private User user;

    public Long getId() {return id;}
    public String getToken() {return token;}
    public LocalDateTime getExpiryDate() {return expiryDate;}
    public User getUser() {return user;}

    public void setId(Long id) {this.id = id;}
    public void setToken(String token) {this.token = token;}
    public void setExpiryDate(LocalDateTime expiryDate) {this.expiryDate = expiryDate;}
    public void setUser(User user) {this.user = user;}

    public VerificationToken(Long id, String token, LocalDateTime expiryDate, User user) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public VerificationToken() {}

}
