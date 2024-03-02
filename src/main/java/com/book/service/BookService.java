package com.book.service;

import java.time.LocalDate;
import java.util.List;

import com.book.dto.BookDTO;
import com.book.dto.UserDTO;

public interface BookService {
    
    // 1. 도서 조회 기능 (도서 이름, 대출 여부를 검색 조건)
    public List<BookDTO> Booklist(BookDTO book) throws Exception;

    // 2. 도서 대출 기능 (다른 사용자 대출인 경우 불가)
    public void borrowBook(BookDTO book, UserDTO user) throws Exception;

    // 3. 도서 반납 기능
    public void returnBook(BookDTO book, UserDTO user) throws Exception;
 
    // 4. 특정 사용자 대출 도서 목록 조회 기능
    public List<BookDTO> Booklist_specificUser(Long userId) throws Exception;

    // 5. 특정 도서의 대출 이력 조회 가능(기간을 검색 조건으로 사용)
    // 오늘 날짜와 검색 조건
    public List<BookDTO> Booklist_specificBook(LocalDate today, String search) throws Exception;

    // 6. 대출 이력이 가장 많거나 적은 도서 조회 기능
    public List<BookDTO> Booklist_historyBook() throws Exception;
} 