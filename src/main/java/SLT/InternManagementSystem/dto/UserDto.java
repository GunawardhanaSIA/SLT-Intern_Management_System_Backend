package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.AuthProvider;
import SLT.InternManagementSystem.entity.Role;
import SLT.InternManagementSystem.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private int id;
    private String email;
    private String password;
    private Role role;
    private AuthProvider authProvider;
    private boolean emailVerified;

    public int getId() {
        return id;
    }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() {
        return role;
    }
    public AuthProvider getAuthProvider() { return authProvider; }
    public boolean isEmailVerified() { return emailVerified; }

    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setAuthProvider(AuthProvider authProvider) { this.authProvider = authProvider; }
    public void setEmailVerified(boolean emailVerified) { this.emailVerified = emailVerified; }

    public UserDto(int id, String email, String password, Role role, AuthProvider authProvider, boolean emailVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.authProvider = authProvider;
        this.emailVerified = emailVerified;
    }

    public UserDto() {}
}
