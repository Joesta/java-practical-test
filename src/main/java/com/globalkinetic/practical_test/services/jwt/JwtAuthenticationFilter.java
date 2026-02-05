package com.globalkinetic.practical_test.services.jwt;


import com.globalkinetic.practical_test.models.User;
import com.globalkinetic.practical_test.repository.UserRepo;
import com.globalkinetic.practical_test.services.UserDetailsServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Joesta
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final ApplicationContext appContext;
    private final JwtBlacklistService jwtBlacklistService;

    @Override

    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);

           try {
               username = jwtService.getUsername(token);
           } catch (RuntimeException re) {
               throw new ServletException("Invalid JWT token", re);
           }

            User user = userRepo.findByUsername(username);

            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = appContext.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    String jti = jwtService.extractJti(token);
                    if (jwtBlacklistService.isBlacklisted(jti)) {
                        throw new ServletException("JWT token expired. Please login again");
                    }

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
