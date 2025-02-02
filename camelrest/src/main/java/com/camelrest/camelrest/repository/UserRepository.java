package com.camelrest.camelrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camelrest.camelrest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}