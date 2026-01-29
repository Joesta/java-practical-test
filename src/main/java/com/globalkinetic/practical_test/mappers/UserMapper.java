package com.globalkinetic.practical_test.mappers;

import com.globalkinetic.practical_test.dto.UserDTO;
import com.globalkinetic.practical_test.dto.UserRequestDTO;
import com.globalkinetic.practical_test.dto.UserResponseDTO;
import com.globalkinetic.practical_test.enums.Role;
import com.globalkinetic.practical_test.models.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Joesta
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserRequestDTO userRequestDTO);

    List<UserDTO> toUserDTOList(List<User> users);

    default UserResponseDTO toUserResponseDTOList(List<User> users) {
        return new UserResponseDTO(toUserDTOList(users));
    }

    @AfterMapping
    default void setDefaults(@MappingTarget User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
    }
}