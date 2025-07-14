package SLT.InternManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    // Overview Statistics
    private int totalInterns;
    private int activeInterns;
    private int completedInterns;
    private int droppedInterns;
    private int suspendedInterns;
    private int totalProjects;
    private int activeProjects;
    private int completedProjects;
    private int totalSupervisors;
    private int activeSupervisors;
    private int inactiveSupervisors;
    private int onLeaveSupervisors;

    // Monthly Registration Data
    private Map<String, Integer> monthlyRegistrations;

    // Specialization Distribution
    private Map<String, Integer> specializationDistribution;

    // Department Distribution
    private Map<String, Integer> departmentDistribution;

    // Attendance Statistics
    private Map<String, Double> attendanceRates;

    // Performance Metrics
    private Map<String, Double> averageWorkingHours;
    private Map<String, Integer> completionRates;

    // Top Performers
    private List<Map<String, Object>> topPerformers;

    // Recent Activities
    private List<Map<String, Object>> recentActivities;

    // Project Statistics
    private Map<String, Integer> projectsByTechnology;
    private Map<String, Integer> projectsByStatus;

    // Supervisor Statistics
    private Map<String, Integer> internsPerSupervisor;
    private Map<String, Integer> projectsPerSupervisor;

    // Time-based Analytics
    private Map<String, Map<String, Integer>> dailyAttendance;
    private Map<String, Map<String, Double>> weeklyProductivity;

    // Educational Institute Statistics
    private Map<String, Integer> instituteDistribution;

    // Duration Analysis
    private Map<String, Integer> internshipDurationDistribution;

    // Status Distributions
    private Map<String, Integer> internStatusDistribution;
    private Map<String, Integer> supervisorStatusDistribution;
}
