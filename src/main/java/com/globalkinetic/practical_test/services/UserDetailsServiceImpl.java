package com.globalkinetic.practical_test.services;

import com.globalkinetic.practical_test.models.User;
import com.globalkinetic.practical_test.models.UserPrincipal;
import com.globalkinetic.practical_test.repository.UserRepo;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    @Nonnull
    public UserDetails loadUserByUsername(@Nonnull String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Username not found with username: " + username);
        }

        return new UserPrincipal(user);
    }
}
