package com.globalkinetic.practical_test.services;

import com.globalkinetic.practical_test.dto.LoginRequestDTO;
import com.globalkinetic.practical_test.dto.LoginResponseDTO;
import com.globalkinetic.practical_test.models.UserPrincipal;
import com.globalkinetic.practical_test.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO req) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        LoginResponseDTO loginResponse = null;

        if (authenticate.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authenticate.getPrincipal();

            if (principal != null) {
                String token = jwtService.generateToken(req.username());
                Long userId = principal.getId();
                loginResponse = new LoginResponseDTO(userId, token);
            }
        }

        return loginResponse;
    }
}