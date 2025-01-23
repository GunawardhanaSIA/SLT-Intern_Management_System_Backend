package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.OtpDto;
import org.springframework.stereotype.Service;

@Service
public interface OtpService {
    OtpDto saveOtp(OtpDto otpDto);

    boolean validateOtp(String email, int otp);
}
