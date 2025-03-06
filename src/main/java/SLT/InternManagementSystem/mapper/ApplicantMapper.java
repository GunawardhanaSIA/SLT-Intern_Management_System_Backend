package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.ApplicantDto;
import SLT.InternManagementSystem.entity.Applicant;

public class ApplicantMapper {
    public static ApplicantDto mapToApplicantDto(Applicant applicant) {
        return new ApplicantDto(
                applicant.getApplicantId(),
                applicant.getUser(),
                applicant.getName(),
                applicant.getNic(),
                applicant.getMobileNumber(),
                applicant.getEmail(),
                applicant.getAddress(),
                applicant.getEducationalInstitute(),
                applicant.getDegree(),
                applicant.getAcademicYear(),
                applicant.getInternshipPeriod(),
                applicant.getSpecialization(),
                applicant.getProgrammingLanguages(),
                applicant.getResumeURL(),
                applicant.getState(),
                applicant.getInterviewDate(),
                applicant.getInterviewTime()
        );
    }

    public static Applicant mapToApplicant(ApplicantDto applicantDto) {
        return new Applicant(
                applicantDto.getApplicantId(),
                applicantDto.getUser(),
                applicantDto.getName(),
                applicantDto.getNic(),
                applicantDto.getMobileNumber(),
                applicantDto.getEmail(),
                applicantDto.getAddress(),
                applicantDto.getEducationalInstitute(),
                applicantDto.getDegree(),
                applicantDto.getAcademicYear(),
                applicantDto.getInternshipPeriod(),
                applicantDto.getSpecialization(),
                applicantDto.getProgrammingLanguages(),
                applicantDto.getResumeURL(),
                applicantDto.getState(),
                applicantDto.getInterviewDate(),
                applicantDto.getInterviewTime()
        );
    }
}
