package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.SupervisorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupervisorService {
    List<SupervisorDto> getAllSupervisors();
}
