package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.InternDto;
import SLT.InternManagementSystem.entity.Intern;

public class InternMapper {
    public static InternDto mapToInternDto(Intern intern) {
        return new InternDto(
                intern.getInternId(),
                intern.getUser(),
                intern.getApplicant(),
                intern.getName(),
                intern.getNic(),
                intern.getMobileNumber(),
                intern.getEmail(),
                intern.getAddress(),
                intern.getEducationalInstitute(),
                intern.getDegree(),
                intern.getAcademicYear(),
                intern.getInternshipPeriod(),
                intern.getSpecialization(),
                intern.getProgrammingLanguages(),
                intern.getResumeURL(),
                intern.getState(),
                intern.getStartDate(),
                intern.getSupervisor()
        );
    }

    public static Intern mapToIntern(InternDto internDto) {
        return new Intern(
                internDto.getInternId(),
                internDto.getUser(),
                internDto.getApplicant(),
                internDto.getName(),
                internDto.getNic(),
                internDto.getMobileNumber(),
                internDto.getEmail(),
                internDto.getAddress(),
                internDto.getEducationalInstitute(),
                internDto.getDegree(),
                internDto.getAcademicYear(),
                internDto.getInternshipPeriod(),
                internDto.getSpecialization(),
                internDto.getProgrammingLanguages(),
                internDto.getResumeURL(),
                internDto.getState(),
                internDto.getStartDate(),
                internDto.getSupervisor()
        );
    }
}
