package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.InternDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InternService {
    InternDto createIntern(int userId, int applicantId, int supervisorId, InternDto internDto);

    List<InternDto> getAllInterns();

    InternDto getIntern(int userId);

    InternDto getInternById(int internId);

    InternDto updateIntern(int internId, InternDto internDto);

    void deleteIntern(int internId);

    InternDto updateInternStatus(int internId, int status);
}