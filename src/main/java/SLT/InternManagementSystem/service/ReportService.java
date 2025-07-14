package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.ReportDto;
import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.Project;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.entity.Attendance;
import SLT.InternManagementSystem.entity.WorkRecord;
import SLT.InternManagementSystem.repository.InternRepository;
import SLT.InternManagementSystem.repository.ProjectRepository;
import SLT.InternManagementSystem.repository.SupervisorRepository;
import SLT.InternManagementSystem.repository.AttendanceRepository;
import SLT.InternManagementSystem.repository.WorkRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private InternRepository internRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private WorkRecordRepository workRecordRepository;

    public ReportDto generateComprehensiveReport() {
        ReportDto report = new ReportDto();

        // Get all data
        List<Intern> allInterns = internRepository.findAll();
        List<Project> allProjects = projectRepository.findAll();
        List<Supervisor> allSupervisors = supervisorRepository.findAll();
        List<Attendance> allAttendance = attendanceRepository.findAll();
        List<WorkRecord> allWorkRecords = workRecordRepository.findAll();

        // Basic Statistics
        report.setTotalInterns(allInterns.size());
        report.setActiveInterns((int) allInterns.stream().filter(intern -> intern.getState() == 0).count());
        report.setCompletedInterns((int) allInterns.stream().filter(intern -> intern.getState() == 2).count());
        report.setDroppedInterns((int) allInterns.stream().filter(intern -> intern.getState() == 1).count());
        report.setSuspendedInterns((int) allInterns.stream().filter(intern -> intern.getState() == 3).count());

        report.setTotalProjects(allProjects.size());
        report.setActiveProjects((int) allProjects.stream().filter(this::isProjectActive).count());
        report.setCompletedProjects((int) allProjects.stream().filter(this::isProjectCompleted).count());

        report.setTotalSupervisors(allSupervisors.size());
        report.setActiveSupervisors(
                (int) allSupervisors.stream().filter(supervisor -> supervisor.getState() == 1).count());
        report.setInactiveSupervisors(
                (int) allSupervisors.stream().filter(supervisor -> supervisor.getState() == 0).count());
        report.setOnLeaveSupervisors(
                (int) allSupervisors.stream().filter(supervisor -> supervisor.getState() == 2).count());

        // Monthly Registration Data
        report.setMonthlyRegistrations(getMonthlyRegistrations(allInterns));

        // Specialization Distribution
        report.setSpecializationDistribution(getSpecializationDistribution(allInterns));

        // Department Distribution (from work records)
        report.setDepartmentDistribution(getDepartmentDistribution(allWorkRecords));

        // Attendance Statistics
        report.setAttendanceRates(getAttendanceRates(allInterns, allAttendance));

        // Performance Metrics
        report.setAverageWorkingHours(getAverageWorkingHours(allInterns, allWorkRecords));
        report.setCompletionRates(getCompletionRates(allInterns, allProjects));

        // Top Performers
        report.setTopPerformers(getTopPerformers(allInterns, allWorkRecords, allAttendance));

        // Recent Activities
        report.setRecentActivities(getRecentActivities(allInterns, allProjects, allWorkRecords));

        // Project Statistics
        report.setProjectsByTechnology(getProjectsByTechnology(allProjects));
        report.setProjectsByStatus(getProjectsByStatus(allProjects));

        // Supervisor Statistics
        report.setInternsPerSupervisor(getInternsPerSupervisor(allInterns));
        report.setProjectsPerSupervisor(getProjectsPerSupervisor(allProjects));

        // Educational Institute Statistics
        report.setInstituteDistribution(getInstituteDistribution(allInterns));

        // Duration Analysis
        report.setInternshipDurationDistribution(getInternshipDurationDistribution(allInterns));

        // Status Distributions
        report.setInternStatusDistribution(getInternStatusDistribution(allInterns));
        report.setSupervisorStatusDistribution(getSupervisorStatusDistribution(allSupervisors));

        return report;
    }

    private Map<String, Integer> getMonthlyRegistrations(List<Intern> interns) {
        Map<String, Integer> monthlyRegistrations = new LinkedHashMap<>();

        // Get last 12 months
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (int i = 11; i >= 0; i--) {
            LocalDate month = now.minusMonths(i);
            String monthKey = month.format(formatter);
            monthlyRegistrations.put(monthKey, 0);
        }

        // Count registrations by month
        for (Intern intern : interns) {
            if (intern.getStartDate() != null) {
                try {
                    LocalDate startDate = LocalDate.parse(intern.getStartDate());
                    String monthKey = startDate.format(formatter);
                    monthlyRegistrations.put(monthKey,
                            monthlyRegistrations.getOrDefault(monthKey, 0) + 1);
                } catch (Exception e) {
                    // Skip invalid dates
                }
            }
        }

        return monthlyRegistrations;
    }

    private Map<String, Integer> getSpecializationDistribution(List<Intern> interns) {
        return interns.stream()
                .collect(Collectors.groupingBy(
                        intern -> intern.getSpecialization() != null ? intern.getSpecialization() : "Unknown",
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private Map<String, Integer> getDepartmentDistribution(List<WorkRecord> workRecords) {
        return workRecords.stream()
                .collect(Collectors.groupingBy(
                        record -> record.getDepartment() != null ? record.getDepartment().name() : "Unknown",
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private Map<String, Double> getAttendanceRates(List<Intern> interns, List<Attendance> attendanceRecords) {
        Map<String, Double> attendanceRates = new HashMap<>();

        for (Intern intern : interns) {
            List<Attendance> internAttendance = attendanceRecords.stream()
                    .filter(attendance -> attendance.getIntern().getInternId() == intern.getInternId())
                    .collect(Collectors.toList());

            if (!internAttendance.isEmpty()) {
                long presentDays = internAttendance.stream()
                        .filter(attendance -> attendance.getStatus() == 1)
                        .count();
                double attendanceRate = (double) presentDays / internAttendance.size() * 100;
                attendanceRates.put(intern.getName(), attendanceRate);
            }
        }

        return attendanceRates;
    }

    private Map<String, Double> getAverageWorkingHours(List<Intern> interns, List<WorkRecord> workRecords) {
        Map<String, Double> averageHours = new HashMap<>();

        for (Intern intern : interns) {
            List<WorkRecord> internRecords = workRecords.stream()
                    .filter(record -> record.getIntern().getInternId() == intern.getInternId())
                    .collect(Collectors.toList());

            if (!internRecords.isEmpty()) {
                double totalHours = internRecords.stream()
                        .mapToDouble(record -> record.getHoursWorked() != null ? record.getHoursWorked() : 0.0)
                        .sum();
                double averageHours2 = totalHours / internRecords.size();
                averageHours.put(intern.getName(), averageHours2);
            }
        }

        return averageHours;
    }

    private Map<String, Integer> getCompletionRates(List<Intern> interns, List<Project> projects) {
        Map<String, Integer> completionRates = new HashMap<>();

        for (Intern intern : interns) {
            List<Project> internProjects = projects.stream()
                    .filter(project -> project.getInterns().stream()
                            .anyMatch(pi -> pi.getInternId() == intern.getInternId()))
                    .collect(Collectors.toList());

            if (!internProjects.isEmpty()) {
                long completedProjects = internProjects.stream()
                        .filter(this::isProjectCompleted)
                        .count();
                int completionRate = (int) ((double) completedProjects / internProjects.size() * 100);
                completionRates.put(intern.getName(), completionRate);
            }
        }

        return completionRates;
    }

    private List<Map<String, Object>> getTopPerformers(List<Intern> interns, List<WorkRecord> workRecords,
            List<Attendance> attendanceRecords) {
        List<Map<String, Object>> topPerformers = new ArrayList<>();

        for (Intern intern : interns) {
            Map<String, Object> performer = new HashMap<>();
            performer.put("name", intern.getName());
            performer.put("specialization", intern.getSpecialization());

            // Calculate performance score based on work hours and attendance
            List<WorkRecord> internRecords = workRecords.stream()
                    .filter(record -> record.getIntern().getInternId() == intern.getInternId())
                    .collect(Collectors.toList());

            List<Attendance> internAttendance = attendanceRecords.stream()
                    .filter(attendance -> attendance.getIntern().getInternId() == intern.getInternId())
                    .collect(Collectors.toList());

            double totalHours = internRecords.stream()
                    .mapToDouble(record -> record.getHoursWorked() != null ? record.getHoursWorked() : 0.0)
                    .sum();

            double attendanceRate = internAttendance.isEmpty() ? 0
                    : (double) internAttendance.stream()
                            .filter(attendance -> attendance.getStatus() == 1)
                            .count() / internAttendance.size() * 100;

            // Simple performance score calculation
            double performanceScore = (totalHours * 0.6) + (attendanceRate * 0.4);
            performer.put("performanceScore", performanceScore);
            performer.put("totalHours", totalHours);
            performer.put("attendanceRate", attendanceRate);

            topPerformers.add(performer);
        }

        // Sort by performance score and return top 10
        return topPerformers.stream()
                .sorted((a, b) -> Double.compare((Double) b.get("performanceScore"),
                        (Double) a.get("performanceScore")))
                .limit(10)
                .collect(Collectors.toList());
    }

    private List<Map<String, Object>> getRecentActivities(List<Intern> interns, List<Project> projects,
            List<WorkRecord> workRecords) {
        List<Map<String, Object>> activities = new ArrayList<>();

        // Recent intern registrations
        interns.stream()
                .sorted((a, b) -> {
                    if (a.getStartDate() == null)
                        return 1;
                    if (b.getStartDate() == null)
                        return -1;
                    return b.getStartDate().compareTo(a.getStartDate());
                })
                .limit(5)
                .forEach(intern -> {
                    Map<String, Object> activity = new HashMap<>();
                    activity.put("type", "New Intern");
                    activity.put("description",
                            intern.getName() + " joined as " + intern.getSpecialization() + " intern");
                    activity.put("date", intern.getStartDate());
                    activities.add(activity);
                });

        // Recent project creations
        projects.stream()
                .sorted((a, b) -> {
                    if (a.getStartDate() == null)
                        return 1;
                    if (b.getStartDate() == null)
                        return -1;
                    return b.getStartDate().compareTo(a.getStartDate());
                })
                .limit(5)
                .forEach(project -> {
                    Map<String, Object> activity = new HashMap<>();
                    activity.put("type", "New Project");
                    activity.put("description", "Project '" + project.getProjectName() + "' started");
                    activity.put("date", project.getStartDate());
                    activities.add(activity);
                });

        return activities.stream()
                .sorted((a, b) -> {
                    String dateA = (String) a.get("date");
                    String dateB = (String) b.get("date");
                    if (dateA == null)
                        return 1;
                    if (dateB == null)
                        return -1;
                    return dateB.compareTo(dateA);
                })
                .limit(10)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> getProjectsByTechnology(List<Project> projects) {
        return projects.stream()
                .collect(Collectors.groupingBy(
                        project -> project.getTechnology() != null ? project.getTechnology() : "Unknown",
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private Map<String, Integer> getProjectsByStatus(List<Project> projects) {
        Map<String, Integer> statusCount = new HashMap<>();
        statusCount.put("Active", 0);
        statusCount.put("Completed", 0);
        statusCount.put("Upcoming", 0);

        for (Project project : projects) {
            if (isProjectActive(project)) {
                statusCount.put("Active", statusCount.get("Active") + 1);
            } else if (isProjectCompleted(project)) {
                statusCount.put("Completed", statusCount.get("Completed") + 1);
            } else {
                statusCount.put("Upcoming", statusCount.get("Upcoming") + 1);
            }
        }

        return statusCount;
    }

    private Map<String, Integer> getInternsPerSupervisor(List<Intern> interns) {
        return interns.stream()
                .filter(intern -> intern.getSupervisor() != null)
                .collect(Collectors.groupingBy(
                        intern -> intern.getSupervisor().getName(),
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private Map<String, Integer> getProjectsPerSupervisor(List<Project> projects) {
        return projects.stream()
                .filter(project -> project.getSupervisor() != null)
                .collect(Collectors.groupingBy(
                        project -> project.getSupervisor().getName(),
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private Map<String, Integer> getInstituteDistribution(List<Intern> interns) {
        return interns.stream()
                .collect(Collectors.groupingBy(
                        intern -> intern.getEducationalInstitute() != null ? intern.getEducationalInstitute()
                                : "Unknown",
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private Map<String, Integer> getInternshipDurationDistribution(List<Intern> interns) {
        return interns.stream()
                .collect(Collectors.groupingBy(
                        intern -> intern.getInternshipPeriod() + " months",
                        Collectors.collectingAndThen(Collectors.counting(), Math::toIntExact)));
    }

    private boolean isProjectActive(Project project) {
        try {
            LocalDate start = LocalDate.parse(project.getStartDate());
            LocalDate target = LocalDate.parse(project.getTargetDate());
            LocalDate now = LocalDate.now();
            return now.isAfter(start) && now.isBefore(target);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isProjectCompleted(Project project) {
        try {
            LocalDate target = LocalDate.parse(project.getTargetDate());
            LocalDate now = LocalDate.now();
            return now.isAfter(target);
        } catch (Exception e) {
            return false;
        }
    }

    private Map<String, Integer> getInternStatusDistribution(List<Intern> interns) {
        Map<String, Integer> statusDistribution = new HashMap<>();
        statusDistribution.put("Active", (int) interns.stream().filter(intern -> intern.getState() == 0).count());
        statusDistribution.put("Dropped", (int) interns.stream().filter(intern -> intern.getState() == 1).count());
        statusDistribution.put("Completed", (int) interns.stream().filter(intern -> intern.getState() == 2).count());
        statusDistribution.put("Suspended", (int) interns.stream().filter(intern -> intern.getState() == 3).count());
        return statusDistribution;
    }

    private Map<String, Integer> getSupervisorStatusDistribution(List<Supervisor> supervisors) {
        Map<String, Integer> statusDistribution = new HashMap<>();
        statusDistribution.put("Inactive",
                (int) supervisors.stream().filter(supervisor -> supervisor.getState() == 0).count());
        statusDistribution.put("Active",
                (int) supervisors.stream().filter(supervisor -> supervisor.getState() == 1).count());
        statusDistribution.put("On Leave",
                (int) supervisors.stream().filter(supervisor -> supervisor.getState() == 2).count());
        return statusDistribution;
    }
}
