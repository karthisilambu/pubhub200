package com.karthi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.karthi.model.Book;
import com.karthi.repository.BookRepository;

public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> findByOrderByPriceDesc() {
		return bookRepository.findByOrderByPriceDesc();
	}

	public List<Book> findByOrderByPriceAsc() {
		return bookRepository.findByOrderByPriceAsc();
	}
//   public List<Book> findmax()
//   {
//	   return bookRepository
//   }
}
      