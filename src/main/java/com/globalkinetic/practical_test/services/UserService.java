package com.globalkinetic.practical_test.services;

import com.globalkinetic.practical_test.dto.UserRequestDTO;
import com.globalkinetic.practical_test.dto.UserResponseDTO;
import com.globalkinetic.practical_test.mappers.UserMapper;
import com.globalkinetic.practical_test.models.User;
import com.globalkinetic.practical_test.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserRequestDTO userReq) {
        User user = userMapper.toUserEntity(userReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public UserResponseDTO getAllUsers() {
        return userMapper.toUserResponseDTOList(userRepo.findAll());
    }

}
