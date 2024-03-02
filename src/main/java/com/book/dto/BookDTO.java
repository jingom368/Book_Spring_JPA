package com.book.dto;

import java.time.LocalDate;

import com.book.entity.BookEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String name;
    private String type;
    private String writer;
    private String status;
    private LocalDate due_date;
    private LocalDate borrowed_date;
    private LocalDate return_date;
    private int borrowedCount;

    private UserDTO user;

        // Entity -> DTO로 이동
        public BookDTO(BookEntity bookEntity) {
            this.id = bookEntity.getId();
            this.name = bookEntity.getName();
            this.type = bookEntity.getType();
            this.writer = bookEntity.getWriter();
            this.status = bookEntity.getStatus();
            this.due_date = bookEntity.getDue_date();
            this.borrowed_date = bookEntity.getBorrowed_date();
            this.return_date = bookEntity.getReturn_date();
            this.borrowedCount = bookEntity.getBorrowedCount();
        }
    
        // DTO -> Entity로 이동 (Builder 패턴 사용)
        public BookEntity dtoToEntity(BookDTO bookDTO) {
            BookEntity bookEntity = BookEntity.builder()
                                    .id(bookDTO.getId())
                                    .name(bookDTO.getName())
                                    .type(bookDTO.getType())
                                    .writer(bookDTO.getWriter())
                                    .status(bookDTO.getStatus())
                                    .due_date(bookDTO.getDue_date())
                                    .borrowed_date(bookDTO.getBorrowed_date())
                                    .return_date(bookDTO.getReturn_date())
                                    .borrowedCount(bookDTO.getBorrowedCount())
                                    .build();
            return bookEntity;
        }
}
