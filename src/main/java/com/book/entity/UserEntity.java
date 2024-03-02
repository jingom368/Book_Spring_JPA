package com.book.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
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
@Entity(name="user")
@Table(name="tbl_user")
public class UserEntity {
    
    @Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", length =10, nullable=true)
	private String name;
	
	@Column(name="email", length =50, nullable=true)
	private String email;
	
	@Column(name="gender", length =10, nullable=true)
	private String gender;
	
	@Column(name="phone", length =20, nullable=true)
	private String phone;

	@Builder.Default
    // Builder 패턴 클래스를 생성하는 @Builder
    // 기본적으로 클래스의 필드별로 값을 설정하는 방식을 제공
    // https://quark-hugger-d60.notion.site/4bc2bb6a62b346508bce9456b4adcdda?pvs=74
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookEntity> borrowedBooks = new ArrayList<>();

	public void borrowBook(BookEntity book) {
        borrowedBooks.add(book);
    }

	public void returnBook(BookEntity book) {
		borrowedBooks.remove(book);
	}
}
