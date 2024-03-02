package com.book.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
