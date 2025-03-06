package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    Optional<Supervisor> findBySupervisorId(int supervisorId);
}
