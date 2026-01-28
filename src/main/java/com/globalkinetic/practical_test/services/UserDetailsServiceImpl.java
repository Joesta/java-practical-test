package com.globalkinetic.practical_test.services;

import com.globalkinetic.practical_test.models.User;
import com.globalkinetic.practical_test.models.UserPrincipal;
import com.globalkinetic.practical_test.repository.UserRepo;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(@Nonnull String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Username not found with username: " + username);
        }

        return new UserPrincipal(user);
    }
}
