package com.globalkinetic.practical_test.services;

import com.globalkinetic.practical_test.dto.UserRequestDTO;
import com.globalkinetic.practical_test.dto.UserResponseDTO;
import com.globalkinetic.practical_test.mappers.UserMapper;
import com.globalkinetic.practical_test.models.User;
import com.globalkinetic.practical_test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 */

@Service
public class UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(UserRequestDTO userReq) {
        System.out.println(userReq);
        User user = userMapper.toUserEntity(userReq);
        userRepo.save(user);
    }

    public UserResponseDTO getAllUsers() {
        return userMapper.toUserResponseDTOList(userRepo.findAll());
    }

}
