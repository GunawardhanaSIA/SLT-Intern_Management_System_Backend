package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.OtpDto;
import SLT.InternManagementSystem.entity.Otp;
import SLT.InternManagementSystem.mapper.OtpMapper;
import SLT.InternManagementSystem.repository.OtpRepository;
import SLT.InternManagementSystem.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {
    private OtpRepository otpRepository;

    @Autowired
    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
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
        if (otpToVerify != null) {
            return otpToVerify.getOtp() == otp;
        }
        return false;
    }
}
