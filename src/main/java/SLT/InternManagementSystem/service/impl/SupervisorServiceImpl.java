package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.entity.Role;
import SLT.InternManagementSystem.entity.AuthProvider;
import SLT.InternManagementSystem.mapper.SupervisorMapper;
import SLT.InternManagementSystem.repository.SupervisorRepository;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.service.SupervisorService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    private final SupervisorRepository supervisorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SupervisorServiceImpl(SupervisorRepository supervisorRepository, UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.supervisorRepository = supervisorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorRepository.findAll();
        return supervisors.stream().map((supervisor) -> SupervisorMapper.mapToSupervisorDto(supervisor))
                .collect(Collectors.toList());
    }

    @Override
    public SupervisorDto getSupervisorById(int supervisorId) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found with id: " + supervisorId));
        return SupervisorMapper.mapToSupervisorDto(supervisor);
    }

    @Override
    public SupervisorDto createSupervisor(SupervisorDto supervisorDto) {
        // First create a User for the supervisor
        User user = User.builder()
                .email(supervisorDto.getEmail())
                .password(passwordEncoder.encode("12345678")) // Set a default password
                .role(Role.Supervisor)
                .authProvider(AuthProvider.Local)
                .emailVerified(true)
                .build();

        User savedUser = userRepository.save(user);

        // Then create the Supervisor
        Supervisor supervisor = SupervisorMapper.mapToSupervisor(supervisorDto);
        supervisor.setUser(savedUser);
        Supervisor savedSupervisor = supervisorRepository.save(supervisor);
        return SupervisorMapper.mapToSupervisorDto(savedSupervisor);
    }

    @Override
    public SupervisorDto updateSupervisor(int supervisorId, SupervisorDto supervisorDto) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found with id: " + supervisorId));

        // Update supervisor fields
        supervisor.setName(supervisorDto.getName());
        supervisor.setMobileNumber(supervisorDto.getMobileNumber());
        supervisor.setEmail(supervisorDto.getEmail());
        supervisor.setSpecialization(supervisorDto.getSpecialization());

        Supervisor updatedSupervisor = supervisorRepository.save(supervisor);
        return SupervisorMapper.mapToSupervisorDto(updatedSupervisor);
    }

    @Override
    public void deleteSupervisor(int supervisorId) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found with id: " + supervisorId));
        supervisorRepository.delete(supervisor);
    }

    @Override
    public SupervisorDto updateSupervisorStatus(int supervisorId, int status) {
        Supervisor supervisor = supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found with id: " + supervisorId));
        supervisor.setState(status);
        Supervisor updatedSupervisor = supervisorRepository.save(supervisor);
        return SupervisorMapper.mapToSupervisorDto(updatedSupervisor);
    }
}