package com.globalkinetic.practical_test.services;

import com.globalkinetic.practical_test.dto.LoginRequestDTO;
import com.globalkinetic.practical_test.dto.LoginResponseDTO;
import com.globalkinetic.practical_test.models.UserPrincipal;
import com.globalkinetic.practical_test.services.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 */
@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

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