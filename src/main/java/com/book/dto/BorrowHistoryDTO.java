package com.book.dto;

import com.book.entity.BookEntity;
import com.book.entity.UserEntity;
import com.book.entity.BorrowHistoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowHistoryDTO {
    private Long id;
    private UserEntity user;
    private BookEntity book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Entity -> DTO로 이동
    public BorrowHistoryDTO(BorrowHistoryEntity borrowHistoryEntity) {
        this.id = borrowHistoryEntity.getId();
        this.user = borrowHistoryEntity.getUser();
        this.book = borrowHistoryEntity.getBook();
        this.borrowDate = borrowHistoryEntity.getBorrowDate();
        this.dueDate = borrowHistoryEntity.getDueDate();
        this.returnDate = borrowHistoryEntity.getReturnDate();
    }

    // DTO -> Entity로 이동 (Builder 패턴 사용)
    public BorrowHistoryEntity dtoToEntity(BorrowHistoryDTO borrowHistoryDTO) {
        BorrowHistoryEntity borrowHistoryEntity = BorrowHistoryEntity.builder()
                                    .id(borrowHistoryDTO.getId())
                                    .user(borrowHistoryDTO.getUser())
                                    .book(borrowHistoryDTO.getBook())
                                    .borrowDate(borrowHistoryDTO.getBorrowDate())
                                    .dueDate(borrowHistoryDTO.getDueDate())
                                    .returnDate(borrowHistoryDTO.getReturnDate())
                                    .build();
        return borrowHistoryEntity;
    }
}
