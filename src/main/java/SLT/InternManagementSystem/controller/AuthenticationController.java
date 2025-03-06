package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.dto.OtpDto;
import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.AuthenticationResponse;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.service.AuthenticationService;
import SLT.InternManagementSystem.service.OtpService;
import SLT.InternManagementSystem.service.UserService;
import SLT.InternManagementSystem.service.impl.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final OtpService otpService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authService, OtpService otpService, UserDetailsServiceImpl userDetailsServiceImpl, UserService userService) {

        this.authService = authService;
        this.otpService = otpService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        System.out.println("Received login request: Email = " + request.getUsername() + ", Password = " + request.getPassword());
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/storeOTP")
    public ResponseEntity<OtpDto> storeOtp(@RequestBody OtpDto otpDto) {
        OtpDto storedOtp = otpService.saveOtp(otpDto);
        return new ResponseEntity<>(storedOtp, HttpStatus.CREATED);
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpDto otpDto) {
        String email = otpDto.getEmail();
        int otp = otpDto.getOtp();

        boolean isValidOtp = otpService.validateOtp(email, otp);

        if (isValidOtp) {
            return ResponseEntity.ok("OTP verified");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        System.out.println("Inside verifyEmail" + username);
        boolean isValidEmail = userService.validateEmail(username);

        if (isValidEmail) {
            return ResponseEntity.ok("User verified");
        } else {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
        }
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        UserDto updatedUser= userService.resetPassword(username, password);
        return ResponseEntity.ok(updatedUser);
    }
}
