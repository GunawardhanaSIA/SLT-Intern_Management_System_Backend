package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.mapper.SupervisorMapper;
import SLT.InternManagementSystem.repository.SupervisorRepository;
import SLT.InternManagementSystem.service.SupervisorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    private final SupervisorRepository supervisorRepository;

    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorRepository.findAll();
        return supervisors.stream().map((supervisor) -> SupervisorMapper.mapToSupervisorDto(supervisor)).collect(Collectors.toList());
    }
}
