package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.ApplicantDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface ApplicantService {
    ApplicantDto createApplicant(ApplicantDto applicantDto);

    List<ApplicantDto> getAllApplicants();

    ApplicantDto setApplicantForInterview(int applicantId, String interviewDate, String interviewTime);
}
