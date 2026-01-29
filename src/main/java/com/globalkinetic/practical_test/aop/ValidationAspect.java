package com.globalkinetic.practical_test.aop;

import com.globalkinetic.practical_test.dto.LoginRequestDTO;
import com.globalkinetic.practical_test.exceptions.CredentialsRequiredException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Joesta
 */

@Component
@Aspect
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
}
