package com.karthi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karthi.model.Book; 
import com.karthi.service.BookService;

@Controller
@RequestMapping("books")
public class BookController {
	private static final Logger LOGGER = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookService;
	

	@GetMapping
	public String list(@RequestParam(value = "price", required = false) String priceFilter, HttpSession session) {
		LOGGER.info("Entering list");

		List<Book> books = null;

		if (priceFilter != null) {
			if (priceFilter.equals("desc")) {
				books = bookService.findByOrderByPriceDesc();
			} else if (priceFilter.equals("asc")) {
				books = bookService.findByOrderByPriceAsc();
			}// else if (priceFilter.equals("high")) {
				//books = bookService.findmax();
			//} 
		} else {
			books = bookService.findAll();
		}
		System.out.println(books);
		session.setAttribute("books", books);
		return "book/list";
	}
       
}

