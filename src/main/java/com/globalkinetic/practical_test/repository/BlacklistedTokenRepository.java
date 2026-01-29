package com.globalkinetic.practical_test.repository;

import com.globalkinetic.practical_test.models.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Joesta
 */
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, String> {
}
