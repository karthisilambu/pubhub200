package com.karthi.controller;
 
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karthi.model.User;
import com.karthi.repository.UserRepository;
import com.karthi.util.EmailUtil;

@Controller
@RequestMapping("auth")

public class AuthController {

	private static final Logger LOGGER = Logger.getLogger(AuthController.class);

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailUtil emailUtil;
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap, HttpSession session) {
		
		LOGGER.info("Entering Login");
		LOGGER.debug(new Object[] { email, password });

		User user = userRepository.findByEmailAndPassword(email, password);
		if (user != null) {
			session.setAttribute("User_Login", user);
			
			LOGGER.info("Login Success");
			return "redirect:../books";
		} else{
			LOGGER.error("Login Failure");
			modelMap.addAttribute("error_msg", "invalid username or password");
			return "fail";
	}


	
	}


	@GetMapping("/register")
	public String showRegsiter() {
		return "user/register";
	}

	@PostMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("password") String password) throws Exception {
		

		LOGGER.info("Registration");
		LOGGER.debug(new Object[] { name,email, password });

		User user=new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		Object obj1=userRepository.save(user);
		String subject="Registered successfuly";
		String body="welcome to Revature";
		emailUtil.send(email, subject, body);
		return "index";	
		
	}

}
