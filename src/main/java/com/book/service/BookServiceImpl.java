package com.book.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.book.dto.BookDTO;
import com.book.dto.UserDTO;
import com.book.entity.BookEntity;
import com.book.entity.UserEntity;
import com.book.entity.repository.BookRepository;
import com.book.entity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor        
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // 1. 도서 조회 기능 (도서 이름, 대출 여부를 검색 조건)
    @Override
    public List<BookDTO> Booklist(BookDTO bookDTO) throws Exception {
        List<BookDTO> bookDTOs = new ArrayList<>();
        bookRepository.findByNameContainingAndStatusContaining(bookDTO.getName(), bookDTO.getStatus())
            .stream().forEach(book -> bookDTOs.add(new BookDTO(book)));
        return bookDTOs;
    }

    // 2. 도서 대출 기능 (다른 사용자 대출인 경우 불가)
    @Override
    public void borrowBook(BookDTO book, UserDTO user) throws Exception {
        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("user is not found"));
        BookEntity bookEntity = bookRepository.findById(book.getId()).orElseThrow(() -> new NoSuchElementException("book is not found"));

        // 책을 빌릴 수 있고 책을 빌린 유저가 다른 사용자가 대출인 경우
        if (bookEntity.isBorrowed() && !bookEntity.getUser().equals(userEntity)) {
            throw new IllegalStateException("이미 다른 사용자가 대출 중인 책입니다.");
        }

        // bookEntity에 배정
        bookEntity.setUser(userEntity);
        bookEntity.setStatus("X");
        bookEntity.setBorrowed_date(book.getBorrowed_date());
        bookEntity.setDue_date(book.getDue_date());
        userEntity.borrowBook(bookEntity);

        bookEntity.setBorrowedCount(bookEntity.getBorrowedCount() + 1);

        bookRepository.save(bookEntity);
        userRepository.save(userEntity);
    }

    // 3. 도서 반납 기능
    @Override
    public void returnBook(BookDTO book, UserDTO user) throws Exception {
        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("user is not found"));
        BookEntity bookEntity = bookRepository.findById(book.getId()).orElseThrow(() -> new NoSuchElementException("book is not found"));

        // 책을 반납하려고 하는데 책이 없는 경우
        if(!bookEntity.getUser().equals(userEntity)) {
            throw new IllegalStateException("이 책은 사용자가 대출하고 있지 않습니다.");
        }

        System.out.println("bookDTO.getReturn_date() : " + book.getReturn_date());
        System.out.println("bookDTO.getReturn_date() : " + book.getReturn_date());
        // 책의 대출자 정보를 null로 바꿈, 사용자 대출 목록에서 책 제거
        bookEntity.setUser(null);
        bookEntity.setStatus("O");
        bookEntity.setReturn_date(book.getReturn_date());
        userEntity.returnBook(bookEntity); 

        bookRepository.save(bookEntity);
        userRepository.save(userEntity);
    }

    // 4. 특정 사용자 대출 도서 목록 조회 기능
    @Override
    public List<BookDTO> Booklist_specificUser(Long userId) throws Exception {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user is not found"));
        List<BookEntity> borrowedBooks = userEntity.getBorrowedBooks();
        List<BookDTO> bookDTOs = new ArrayList<>();
        borrowedBooks
            .stream().forEach(book -> bookDTOs.add(new BookDTO(book)));
        return bookDTOs;
    }

    // 5. 특정 도서의 대출 이력 조회 가능(기간을 검색 조건으로 사용)
    // 오늘 날짜와 검색 조건
    @Override
    public List<BookDTO> Booklist_specificBook(LocalDate today, String search) throws Exception {
        Integer searchDays = Integer.parseInt(search);
        LocalDate endDate = today.plusDays(searchDays);
        List<BookDTO> bookDTOs = new ArrayList<>();
        bookRepository.Booklist_specificBook(today, endDate)
            .stream().forEach(book -> bookDTOs.add(new BookDTO(book)));
        return bookDTOs;
    }

    // 6. 대출 이력이 가장 많거나 적은 도서 조회 기능
    @Override
    public List<BookDTO> Booklist_historyBook() throws Exception {
        List<BookDTO> bookDTOs = new ArrayList<>();
        bookRepository.findMostBorrowedBook()
            .stream().forEach(book -> bookDTOs.add(new BookDTO(book)));
        return bookDTOs;
    }
    

}
