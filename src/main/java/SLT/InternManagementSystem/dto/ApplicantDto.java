package SLT.InternManagementSystem.dto;

import SLT.InternManagementSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ApplicantDto {
    private int applicantId;
    private User user;
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
    private int internState;
    private String interviewDate;
    private String interviewTime;



    public int getApplicantId() {
        return applicantId;
    }
    public User getUser() { return user; }
    public String getName() {
        return name;
    }
    public Long getNic() {
        return nic;
    }
    public Long getMobileNumber() {
        return mobileNumber;
    }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getEducationalInstitute() { return educationalInstitute; }
    public String getDegree() { return degree; }
    public String getAcademicYear() { return academicYear; }
    public int getInternshipPeriod() { return internshipPeriod; }
    public String getSpecialization() { return specialization; }
    public String getProgrammingLanguages() { return programmingLanguages; }
    public String getResumeURL() { return resumeURL; }
    public int getState() { return state; }
    public int getInternState() { return internState; }
    public String getInterviewDate() { return interviewDate; }
    public String getInterviewTime() { return interviewTime; }


    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }
    public void setUser(User user) { this.user = user; }
    public void setName(String name) { this.name = name; }
    public void setNic(Long nic) { this.nic = nic; }
    public void setMobileNumber(Long mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setEducationalInstitute(String educationalInstitute) { this.educationalInstitute = educationalInstitute; }
    public void setDegree(String degree) { this.degree = degree; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
    public void setInternshipPeriod(int internshipPeriod) { this.internshipPeriod = internshipPeriod; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setProgrammingLanguages(String programmingLanguages) { this.programmingLanguages = programmingLanguages; }
    public void setResumeURL(String resumeURL) { this.resumeURL = resumeURL; }
    public void setState(int state) { this.state = state; }
    public void setInternState(int internState) { this.internState = internState; }
    public void setInterviewDate(String interviewDate) { this.interviewDate = interviewDate; }
    public void setInterviewTime(String interviewTime) { this.interviewTime = interviewTime; }

    public ApplicantDto(int applicantId,
                        User user,
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
                        int internState,
                        String interviewDate,
                        String interviewTime) {
        this.applicantId = applicantId;
        this.user = user;
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
        this.internState = internState;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
    }
}
