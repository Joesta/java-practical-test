package com.globalkinetic.practical_test.aop;

import com.globalkinetic.practical_test.dto.LoginRequestDTO;
import com.globalkinetic.practical_test.exceptions.CredentialsRequiredException;
import com.globalkinetic.practical_test.models.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Joesta
 */

@Component
@Aspect
@RequiredArgsConstructor
public class ValidationAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Before("execution(* com.globalkinetic.practical_test.services.AuthService.login(..)) && args(loginReq) ")
    public void validateLogin(LoginRequestDTO loginReq) {
        LOGGER.info("validating login request");
        if (loginReq == null ||
                loginReq.username() == null || loginReq.username().trim().isEmpty() ||
                loginReq.password() == null || loginReq.password().trim().isEmpty()) {

            throw new CredentialsRequiredException("Username and password are required");
        }
    }

    @Before(value = "execution(* com.globalkinetic.practical_test.services.jwt.JwtBlacklistService.blacklist(..)) && args(userId, authHeader, authentication)", argNames = "userId,authHeader,authentication")
    public void checkAuthorization(Long userId, String authHeader, Authentication authentication) {
        LOGGER.info("validating principal on logout request");

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        assert principal != null;
        if (!principal.getId().equals(userId)) {
            throw new RuntimeException("Cannot blacklist token for another user!");
        }
    }
}
