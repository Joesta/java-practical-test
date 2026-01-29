package com.globalkinetic.practical_test.services.jwt;

import com.globalkinetic.practical_test.repository.BlacklistedTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author Joesta
 */

@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMs;
    private BlacklistedTokenRepository jwtRepository;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration-ms}") Duration expiration) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 256 bits (32 bytes)");
        }

        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expirationMs = expiration.toMillis();
    }

    @Autowired
    public void setJwtRepository(BlacklistedTokenRepository jwtRepository) {
        this.jwtRepository = jwtRepository;
    }

    public String generateToken(String username) {
        String jti = UUID.randomUUID().toString();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");

        return Jwts.builder()
                .setClaims(claims)
                .setId(jti)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractJti(String token) {
        return extractClaim(token, Claims::getId);
    }

    public Date getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = getUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}
