package SLT.InternManagementSystem.repository;

import SLT.InternManagementSystem.entity.Intern;
import SLT.InternManagementSystem.entity.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {

    List<WorkRecord> findByInternOrderByWorkDateDesc(Intern intern);

    List<WorkRecord> findByInternAndWorkDateBetweenOrderByWorkDateDesc(
            Intern intern, LocalDate startDate, LocalDate endDate);

    Optional<WorkRecord> findByInternAndWorkDate(Intern intern, LocalDate workDate);

    @Query("SELECT SUM(w.hoursWorked) FROM WorkRecord w WHERE w.intern = :intern")
    Double getTotalHoursByIntern(@Param("intern") Intern intern);

    @Query("SELECT SUM(w.hoursWorked) FROM WorkRecord w WHERE w.intern = :intern " +
            "AND EXTRACT(YEAR FROM w.workDate) = :year " +
            "AND EXTRACT(MONTH FROM w.workDate) = :month")
    Double getMonthlyHoursByIntern(@Param("intern") Intern intern,
                                   @Param("year") int year,
                                   @Param("month") int month);

    @Query("SELECT COUNT(w) FROM WorkRecord w WHERE w.intern = :intern")
    Long getTotalWorkDaysByIntern(@Param("intern") Intern intern);

    @Query("SELECT COUNT(w) FROM WorkRecord w WHERE w.intern = :intern " +
            "AND EXTRACT(YEAR FROM w.workDate) = :year " +
            "AND EXTRACT(MONTH FROM w.workDate) = :month")
    Long getMonthlyWorkDaysByIntern(@Param("intern") Intern intern,
                                    @Param("year") int year,
                                    @Param("month") int month);

    boolean existsByInternAndWorkDate(Intern intern, LocalDate workDate);
}