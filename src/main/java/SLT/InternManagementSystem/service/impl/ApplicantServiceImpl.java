package SLT.InternManagementSystem.service.impl;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.entity.Applicant;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.mapper.ApplicantMapper;
import SLT.InternManagementSystem.repository.ApplicantRepository;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.service.ApplicantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private final UserRepository userRepository;
    private ApplicantRepository applicantRepository;

    @Autowired
    public ApplicantServiceImpl(ApplicantRepository applicantRepository, UserRepository userRepository) {
        this.applicantRepository = applicantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApplicantDto createApplicant(ApplicantDto applicantDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);

        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        applicantDto.setUser(userOptional.get());
        Applicant applicant = ApplicantMapper.mapToApplicant(applicantDto);
        Applicant savedApplicant = applicantRepository.save(applicant);
        return ApplicantMapper.mapToApplicantDto(savedApplicant);
    }

    @Override
    public List<ApplicantDto> getAllApplicants() {
        List<Applicant> applicants = applicantRepository.findAll();
        return applicants.stream().map((applicant) -> ApplicantMapper.mapToApplicantDto(applicant)).collect(Collectors.toList());
    }

    @Override
    public ApplicantDto setApplicantForInterview(int applicantId, String interviewDate, String interviewTime) {
        Applicant applicant = applicantRepository.findApplicantByApplicantId(applicantId)
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found with id: " + applicantId));

        applicant.setState(1);
        applicant.setInterviewDate(interviewDate);
        applicant.setInterviewTime(interviewTime);
        Applicant updatedApplicant = applicantRepository.save(applicant);
        return ApplicantMapper.mapToApplicantDto(updatedApplicant);
    }
}
