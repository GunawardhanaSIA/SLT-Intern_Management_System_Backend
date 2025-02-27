package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.entity.Supervisor;

public class SupervisorMapper {
    public static SupervisorDto mapToSupervisorDto(Supervisor supervisor) {
        return new SupervisorDto(
                supervisor.getSupervisorId(),
                supervisor.getUser(),
                supervisor.getName(),
                supervisor.getMobileNumber(),
                supervisor.getEmail(),
                supervisor.getSpecialization(),
                supervisor.getState()
        );
    }

    public static Supervisor mapToSupervisor(SupervisorDto supervisorDto) {
        return new Supervisor(
                supervisorDto.getSupervisorId(),
                supervisorDto.getUser(),
                supervisorDto.getName(),
                supervisorDto.getMobileNumber(),
                supervisorDto.getEmail(),
                supervisorDto.getSpecialization(),
                supervisorDto.getState()
        );
    }
}
