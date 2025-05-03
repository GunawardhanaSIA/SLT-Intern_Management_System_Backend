package SLT.InternManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private boolean emailVerified = false;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }

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

    public User(int id, String email, String password, Role role, AuthProvider authProvider, boolean emailVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.authProvider = authProvider;
        this.emailVerified = emailVerified;
    }

    public User() {}

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
