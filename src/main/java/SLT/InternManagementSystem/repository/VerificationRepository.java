package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUserId(int userId);
    Optional<VerificationToken> findByUserIdAndToken(int userId, String token);
}
