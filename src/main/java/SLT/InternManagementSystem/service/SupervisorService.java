package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.SupervisorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupervisorService {
    List<SupervisorDto> getAllSupervisors();

    SupervisorDto getSupervisorById(int supervisorId);

    SupervisorDto createSupervisor(SupervisorDto supervisorDto);

    SupervisorDto updateSupervisor(int supervisorId, SupervisorDto supervisorDto);

    void deleteSupervisor(int supervisorId);

    SupervisorDto updateSupervisorStatus(int supervisorId, int status);
}