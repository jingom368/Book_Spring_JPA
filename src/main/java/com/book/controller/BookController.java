package com.book.controller;

import java.util.List;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.dto.BookDTO;
import com.book.dto.BorrowRequestDTO;
import com.book.dto.UserDTO;
import com.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookservice;

    @GetMapping("/api/list") // o
    // 1. 도서 조회 기능 (도서 이름, 대출 여부를 검색 조건)
    public List<BookDTO> getBookList(@RequestParam("name") String name,
        @RequestParam("status") String status) throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(name);
        bookDTO.setStatus(status);
        return bookservice.Booklist(bookDTO);
    }

    @PostMapping("/api/borrow") // o 
    // // 2. 도서 대출 기능 (다른 사용자 대출인 경우 불가)
    // public void borrowBook(BookDTO bookDTO) throws Exception;
    public void postborrowBook(@RequestBody BorrowRequestDTO request) throws Exception {
        UserDTO userDTO = request.getUser();
        BookDTO bookDTO = request.getBook();
        bookservice.borrowBook(bookDTO, userDTO);
    }

    // 3. 도서 반납 기능
    @PostMapping("/api/return")
    public void postreturnBook(@RequestBody BorrowRequestDTO request) throws Exception {
        UserDTO userDTO = request.getUser();
        BookDTO bookDTO = request.getBook();
        bookservice.returnBook(bookDTO, userDTO);
    }
 
    @GetMapping("/api/users/{userId}/books")
    // 4. 특정 사용자 대출 도서 목록 조회 기능
    public List<BookDTO> getBooklist_specificUser(@PathVariable Long userId) throws Exception {
        return bookservice.Booklist_specificUser(userId);
    }


    // 5. 특정 도서의 대출 이력 조회 가능(기간을 검색 조건으로 사용)
    // 오늘 날짜와 검색 조건
    @GetMapping("api/duedate")
    public List<BookDTO> getlistBook_specificBook(@RequestParam("today") LocalDate today, @RequestParam("search") String search) throws Exception {
        return bookservice.Booklist_specificBook(today,search);
    }

    @GetMapping("api/listcount")
    // 6. 대출 이력이 가장 많거나 적은 도서 조회 기능
    public List<BookDTO> postBooklist_historyBook() throws Exception {
        return bookservice.Booklist_historyBook();
    }
    
}
