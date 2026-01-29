package com.globalkinetic.practical_test.services.jwt;

import com.globalkinetic.practical_test.models.BlacklistedToken;
import com.globalkinetic.practical_test.repository.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Joesta
 *
 */
@Service
@RequiredArgsConstructor
public class JwtBlacklistService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;
    private final JwtService jwtService;

    public void blacklist(Long userId, String authHeader, Authentication authentication) {

        String token = authHeader.substring(7);

        BlacklistedToken blacklistedToken = new BlacklistedToken();

        blacklistedToken.setJti(jwtService.extractJti(token));
        blacklistedToken.setExpiresAt(jwtService.getExpiration(token).toInstant());
        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isBlacklisted(String jti) {
        return blacklistedTokenRepository.existsById(jti);
    }
}
