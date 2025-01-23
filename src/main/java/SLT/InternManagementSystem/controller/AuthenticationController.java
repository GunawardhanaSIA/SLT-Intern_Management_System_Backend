package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.dto.OtpDto;
import SLT.InternManagementSystem.entity.AuthenticationResponse;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.service.AuthenticationService;
import SLT.InternManagementSystem.service.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("*")
@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final OtpService otpService;

    public AuthenticationController(AuthenticationService authService, OtpService otpService) {

        this.authService = authService;
        this.otpService = otpService;
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
}
