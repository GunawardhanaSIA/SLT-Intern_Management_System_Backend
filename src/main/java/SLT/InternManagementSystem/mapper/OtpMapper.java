package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.OtpDto;
import SLT.InternManagementSystem.entity.Otp;

public class OtpMapper {
    public static OtpDto mapToOtpDto(Otp otp) {
        return new OtpDto(
                otp.getId(),
                otp.getEmail(),
                otp.getOtp(),
                otp.getCreatedAt()
        );
    }

    public static Otp mapToOtp(OtpDto otpDto) {
        return new Otp(
                otpDto.getId(),
                otpDto.getEmail(),
                otpDto.getOtp(),
                otpDto.getCreatedAt()
        );
    }
}
