package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InternRepository extends JpaRepository<Intern, Integer> {
    Optional<Intern> findByInternId(int internId);

    Optional<Intern> findInternByInternId(int internId);

    Intern findInternByEmail(String email);

    Intern findInternByUser(User user);

    List<Intern> findBySupervisor(Supervisor supervisor);
}
