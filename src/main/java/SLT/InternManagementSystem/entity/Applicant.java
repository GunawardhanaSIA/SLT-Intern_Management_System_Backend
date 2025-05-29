package SLT.InternManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicantId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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

    @Column(columnDefinition = "TEXT")
    private String resumeText;
    @Column(columnDefinition = "INT DEFAULT 0")
    private int state;
    @Column(columnDefinition = "INT DEFAULT 0")
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
    public String getResumeText() { return resumeText; }
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
    public void setResumeText(String resumeText) { this.resumeText = resumeText; }
    public void setState(int state) { this.state = state; }
    public void setInternState(int internState) { this.internState = internState; }
    public void setInterviewDate(String interviewDate) { this.interviewDate = interviewDate; }
    public void setInterviewTime(String interviewTime) { this.interviewTime = interviewTime; }


    public Applicant() {}

    public Applicant(int applicantId,
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
                        String resumeText,
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
        this.resumeText = resumeText;
        this.state = state;
        this.internState = internState;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
    }
}
