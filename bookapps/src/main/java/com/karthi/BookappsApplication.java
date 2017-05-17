package com.karthi;

import org.apache.log4j.Logger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookappsApplication {
	private static final Logger LOGGER = Logger.getLogger(BookappsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookappsApplication.class, args);
		LOGGER.info("Application Started");
 
	} 
}
