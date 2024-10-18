package com.selvam.dreamshops.repository;

import com.selvam.dreamshops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
