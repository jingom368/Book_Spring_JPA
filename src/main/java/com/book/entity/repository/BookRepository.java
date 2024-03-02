package com.book.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.entity.BookEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long>{

    // 1. 도서 조회 기능 (도서 이름, 대출 여부를 검색 조건)
    public List<BookEntity> findByNameContainingAndStatusContaining(String name, String status);

    // 2. 도서 대출 기능 (다른 사용자 대출인 경우 불가)

    // 3. 도서 반납 기능
 
    // 4. 특정 사용자 대출 도서 목록 조회 기능

    // 5. 특정 도서의 대출 이력 조회 가능(기간을 검색 조건으로 사용)
    // 오늘 날짜와 검색 조건 -> JPQL
    @Query("select b from book b where b.borrowed_date >= :today and b.borrowed_date <= :endDate") 
    public List<BookEntity> Booklist_specificBook(@Param("today") LocalDate today, @Param("endDate") LocalDate endDate);

    // 6. 대출 이력이 가장 많거나 적은 도서 조회 기능
    @Query("SELECT b FROM book b ORDER BY b.borrowedCount DESC")
    public List<BookEntity> findMostBorrowedBook();
    
    @Query("SELECT b FROM book b ORDER BY b.borrowedCount ASC")
    public List<BookEntity> findLeastBorrowedBook();
} 