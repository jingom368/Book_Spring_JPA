package com.book.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.BorrowHistoryEntity;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistoryEntity, Long>{

} 