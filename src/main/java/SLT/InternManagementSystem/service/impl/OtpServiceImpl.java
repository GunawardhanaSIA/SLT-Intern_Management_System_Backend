package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.OtpDto;
import SLT.InternManagementSystem.entity.Otp;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.mapper.OtpMapper;
import SLT.InternManagementSystem.repository.OtpRepository;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {
    private final UserRepository userRepository;
    private OtpRepository otpRepository;

    @Autowired
    public OtpServiceImpl(OtpRepository otpRepository, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OtpDto saveOtp(OtpDto otpDto) {
        Otp otp = OtpMapper.mapToOtp(otpDto);
        Otp savedOtp = otpRepository.save(otp);
        return OtpMapper.mapToOtpDto(savedOtp);
    }

    @Override
    public boolean validateOtp(String email, int otp) {
        Otp otpToVerify = otpRepository.findTopByEmailOrderByCreatedAtDesc(email);

        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
//        user.setState(0);
        User updatedUser = userRepository.save(user);

        if (otpToVerify != null) {
            return otpToVerify.getOtp() == otp;
        }
        return false;
    }
}
