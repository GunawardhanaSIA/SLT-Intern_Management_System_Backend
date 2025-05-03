package SLT.InternManagementSystem.mapper;

import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getAuthProvider(),
                user.isEmailVerified()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getAuthProvider(),
                userDto.isEmailVerified()
        );
    }
}
