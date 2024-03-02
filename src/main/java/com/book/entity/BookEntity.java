package com.book.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity(name="book")
@Table(name="tbl_book")
public class BookEntity {
    
    @Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length =10, nullable=true)
	private String name;
	
	@Column(name="type", length =10, nullable=true)
	private String type;
	
	@Column(name="writer", length =10, nullable=true)
	private String writer;
	
	@Column(name="status", length =10, nullable=true)
	private String status;

	@Column(name="borrowed_date", length =20, nullable=true)
	private LocalDate borrowed_date;

	@Column(name="due_date", length =20, nullable=true)
	private LocalDate due_date;

	@Column(name="return_date", length =20, nullable=true)
	private LocalDate return_date;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public boolean isBorrowed() {
        return user != null;
    }

	@Builder.Default
	@Column(name = "borrowed_count")
    private int borrowedCount = 0;
}
