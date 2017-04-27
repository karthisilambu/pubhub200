package com.karthi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karthi.model.Book;
import com.karthi.repository.BookRepository;

@Controller
@RequestMapping("books")
public class BookController {
	private static final Logger LOGGER = Logger.getLogger(BookController.class);

	@Autowired
	private BookRepository bookRepository;

	@GetMapping
	public String list(HttpSession session) {
		LOGGER.info("Entering list");
		List<Book> books = bookRepository.findAll();
		System.out.println(books);
		session.setAttribute("books", books);
		return "book/list";
}
}
