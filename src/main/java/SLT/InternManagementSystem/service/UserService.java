package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean validateEmail(String username);
    UserDto resetPassword(String username, String password);

}
