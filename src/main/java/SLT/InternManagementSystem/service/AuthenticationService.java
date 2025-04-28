package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.AuthenticationResponse;
import SLT.InternManagementSystem.entity.Role;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.mapper.UserMapper;
import SLT.InternManagementSystem.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserDto request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());

        if (existingUser.isPresent()) {
            String token = jwtService.generateToken(existingUser.get());
            return new AuthenticationResponse(token);
        }

        User user = new User();
        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
//        user.setState(1);

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(UserDto userDto) {
//        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow();
//        String token = jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
        Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());

        if (existingUser.isPresent()) {
            String token = jwtService.generateToken(existingUser.get());
            return new AuthenticationResponse(token);
        }

        User user = new User();
        user.setUsername(user.getUsername());
        user.setRole(Role.Intern);

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

}
