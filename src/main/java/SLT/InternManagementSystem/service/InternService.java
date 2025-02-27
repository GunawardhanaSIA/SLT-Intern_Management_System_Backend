package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.InternDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InternService {
    InternDto createIntern(int userId, int applicantId, int supervisorId, InternDto internDto);

    List<InternDto> getAllInterns();
}
