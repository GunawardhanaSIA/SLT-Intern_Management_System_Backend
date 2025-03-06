package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Optional<Applicant> findApplicantByApplicantId(int applicantId);
}
