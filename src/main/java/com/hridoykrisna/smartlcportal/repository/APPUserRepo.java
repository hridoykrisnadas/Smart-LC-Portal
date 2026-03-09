package com.hridoykrisna.smartlcportal.repository;

import com.hridoykrisna.smartlcportal.entity.AppUser;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface APPUserRepo extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByUsername(@NotBlank String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
