package com.shashi.foodOrdering.repository;

import com.shashi.foodOrdering.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    // Custom Method
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
