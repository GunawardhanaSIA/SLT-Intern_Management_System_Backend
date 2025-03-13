package SLT.InternManagementSystem.dto;

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
    private String username;
    private String password;
    private Role role;
    private int state;

    public int getId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Role getRole() {return role;}
    public int getState() {return state;}

    public void setId(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setRole(Role role) {this.role = role;}
    public void setState(int state) {this.state = state;}

    public UserDto(int id, String username, String password, Role role, int state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.state = state;
    }

    public UserDto() {}
}
