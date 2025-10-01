package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.OtpDto;
import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.AuthenticationResponse;
import SLT.InternManagementSystem.service.AuthenticationService;
import SLT.InternManagementSystem.service.OtpService;
import SLT.InternManagementSystem.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin("http://localhost:5173")
@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final OtpService otpService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authService, OtpService otpService, UserService userService) {
        this.authService = authService;
        this.otpService = otpService;
        this.userService = userService;
    }

    @PostMapping("/signup/manual")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request) throws MessagingException {
        System.out.println("user request: " + request);
        return ResponseEntity.ok(authService.ManualRegister(request));
    }

    @GetMapping("verify")
    public void verifyAccount(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        authService.VerifyToken(token, response);
    }

    @PostMapping("/signup/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        return ResponseEntity.ok(authService.GoogleRegister(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserDto userDto) {
        System.out.println("Received login request: Email = " + userDto.getEmail());
        System.out.println("Received login request: Password = " + userDto.getPassword());
        return ResponseEntity.ok(authService.authenticate(userDto));
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