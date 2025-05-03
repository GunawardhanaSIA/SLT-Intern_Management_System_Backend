package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.AuthenticationResponse;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.mapper.UserMapper;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean validateEmail(String username) {
        System.out.println("Inside validateEmail" + username);
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
//            System.out.println("User not found: " + username);
            return false;
        }
        return true;
    }

    @Override
    public UserDto resetPassword(String username, String password) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

//        user.setPassword(passwordEncoder.encode(password));

        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUser);
    }
}
