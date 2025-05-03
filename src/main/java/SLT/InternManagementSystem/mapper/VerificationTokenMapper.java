package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.VerificationTokenDto;
import SLT.InternManagementSystem.entity.VerificationToken;
import SLT.InternManagementSystem.repository.VerificationRepository;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenMapper {
    private final VerificationRepository verificationRepository;

    public VerificationTokenMapper(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    public static VerificationTokenDto mapToVerificationTokenDto(VerificationToken token) {
        return new VerificationTokenDto(
                token.getId(),
                token.getToken(),
                token.getExpiryDate(),
                token.getUser()
        );
    }

    public static VerificationToken mapToVerificationToken(VerificationTokenDto tokenDto) {
        return new VerificationToken(
                tokenDto.getId(),
                tokenDto.getToken(),
                tokenDto.getExpiryDate(),
                tokenDto.getUser()
        );
    }
}
