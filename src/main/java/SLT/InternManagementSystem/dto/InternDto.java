package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.Applicant;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
public class InternDto {
    private int internId;
    private User user;
    private Applicant applicant;
    private String name;
    private Long nic;
    private Long mobileNumber;
    private String email;
    private String address;
    private String educationalInstitute;
    private String degree;
    private String academicYear;
    private int internshipPeriod;
    private String specialization;
    private String programmingLanguages;
    private String resumeURL;
    private int state;
    private String startDate;
    private Supervisor supervisor;

    public int getInternId() {
        return internId;
    }

    public User getUser() {
        return user;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public String getName() {
        return name;
    }

    public Long getNic() {
        return nic;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getEducationalInstitute() {
        return educationalInstitute;
    }

    public String getDegree() {
        return degree;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public int getInternshipPeriod() {
        return internshipPeriod;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getProgrammingLanguages() {
        return programmingLanguages;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public int getState() {
        return state;
    }

    public String getStartDate() {
        return startDate;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Integer getSupervisorId() {
        return supervisor != null ? supervisor.getSupervisorId() : null;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(Long nic) {
        this.nic = nic;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEducationalInstitute(String educationalInstitute) {
        this.educationalInstitute = educationalInstitute;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setInternshipPeriod(int internshipPeriod) {
        this.internshipPeriod = internshipPeriod;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public InternDto() {
    }

    public InternDto(int internId,
                     User user,
                     Applicant applicant,
                     String name,
                     Long nic,
                     Long mobileNumber,
                     String email,
                     String address,
                     String educationalInstitute,
                     String degree,
                     String academicYear,
                     int internshipPeriod,
                     String specialization,
                     String programmingLanguages,
                     String resumeURL,
                     int state,
                     String startDate,
                     Supervisor supervisor) {
        this.internId = internId;
        this.user = user;
        this.applicant = applicant;
        this.name = name;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.educationalInstitute = educationalInstitute;
        this.degree = degree;
        this.academicYear = academicYear;
        this.internshipPeriod = internshipPeriod;
        this.specialization = specialization;
        this.programmingLanguages = programmingLanguages;
        this.resumeURL = resumeURL;
        this.state = state;
        this.startDate = startDate;
        this.supervisor = supervisor;
    }
}