package com.globalkinetic.practical_test.repository;

import com.globalkinetic.practical_test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Joesta
 */

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
