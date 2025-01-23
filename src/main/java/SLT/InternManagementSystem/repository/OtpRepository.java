package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Otp findByEmail(String email);

    Otp findTopByEmailOrderByCreatedAtDesc(String email);
}
