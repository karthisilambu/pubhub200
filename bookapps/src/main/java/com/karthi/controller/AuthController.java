package com.karthi.controller;
 
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karthi.form.RegistrationForm;
import com.karthi.model.User;
import com.karthi.service.UserService;
import com.karthi.util.EmailUtil;

@Controller
@RequestMapping("auth")

public class AuthController {

	private static final Logger LOGGER = Logger.getLogger(AuthController.class);

	
	@Autowired
	private UserService userService;
	@Autowired
	private EmailUtil emailUtil;
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap, HttpSession session) {
		
		LOGGER.info("Entering Login");
		LOGGER.debug(new Object[] { email, password });

		User user = userService.findByEmailAndPassword(email, password);
		if (user != null) {
			session.setAttribute("LOGGED-IN-USER", user);
		 	
			//session.setAttribute("LOGGED_IN_USER", user);
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
	public String register(@ModelAttribute @Valid RegistrationForm user, BindingResult result,ModelMap modelMap, 
			HttpSession session) throws Exception {	
		try {

			System.out.println("Registraion Form :" + user);
			
			if (result.hasErrors()) {
				modelMap.addAttribute("errors", result.getAllErrors());
				modelMap.addAttribute("regFormData", user );
				return "user/register";
			}else {
				userService.register(user);
				
				return "redirect:../";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("regFormData", user );
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "user/register";
		}

}
		
		
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../";
	}
}
