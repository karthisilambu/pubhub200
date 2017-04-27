package com.karthi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthi.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	

}
