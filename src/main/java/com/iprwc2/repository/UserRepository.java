package com.iprwc2.repository;

import com.iprwc2.model.User;
import com.iprwc2.model.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<UserProjection> findUserProjectionById(Long id);

    Boolean existsByEmail(String email);
}

