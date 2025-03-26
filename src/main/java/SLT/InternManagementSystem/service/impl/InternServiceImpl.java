package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.entity.Applicant;
import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.mapper.ApplicantMapper;
import SLT.InternManagementSystem.mapper.InternMapper;
import SLT.InternManagementSystem.repository.ApplicantRepository;
import SLT.InternManagementSystem.repository.InternRepository;
import SLT.InternManagementSystem.repository.SupervisorRepository;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InternServiceImpl implements InternService {
    private final InternRepository internRepository;
    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final SupervisorRepository supervisorRepository;


    @Autowired
    public InternServiceImpl(InternRepository internRepository, UserRepository userRepository, ApplicantRepository applicantRepository, SupervisorRepository supervisorRepository) {
        this.internRepository = internRepository;
        this.userRepository = userRepository;
        this.applicantRepository = applicantRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public InternDto createIntern(int userId, int applicantId, int supervisorId, InternDto internDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Applicant applicant = applicantRepository.findApplicantByApplicantId(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
        Supervisor supervisor = supervisorRepository.findBySupervisorId(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));

        Intern intern = InternMapper.mapToIntern(internDto);
        intern.setUser(user);
        intern.setApplicant(applicant);
        intern.setSupervisor(supervisor);
        Intern savedIntern = internRepository.save(intern);
        return InternMapper.mapToInternDto(savedIntern);
    }

    @Override
    public List<InternDto> getAllInterns() {
        List<Intern> interns = internRepository.findAll();
        return interns.stream().map((intern) -> InternMapper.mapToInternDto(intern)).collect(Collectors.toList());
    }

    @Override
    public InternDto getIntern(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Intern intern = internRepository.findInternByUser(user);
        if (intern != null) {
            return InternMapper.mapToInternDto(intern);
        }
        return null;
    }
}
