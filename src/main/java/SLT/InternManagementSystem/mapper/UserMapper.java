package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.SupervisorDto;
import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.Supervisor;
import SLT.InternManagementSystem.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getState()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getState()
        );
    }
}
