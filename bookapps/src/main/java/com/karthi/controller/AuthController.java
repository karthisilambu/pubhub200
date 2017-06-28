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

import com.karthi.form.LoginForm;
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
	
	public String login(@ModelAttribute @Valid LoginForm logUser, BindingResult result, ModelMap modelMap,
			HttpSession session) throws Exception  {
		LOGGER.info("Entering Login");
		LOGGER.debug(new Object[] { logUser.getEmail(), logUser.getPassword()});
			User userObj = userService.findByEmailAndPassword(logUser.getEmail(), logUser.getPassword());
			if (result.hasErrors()) {
				modelMap.addAttribute("errors", result.getAllErrors());
				modelMap.addAttribute("regFormData", logUser);
				return "index";
			}
			else if (userObj != null) {
				session.setAttribute("LOGGED_IN_USER", userObj);
				session.setAttribute("loguser", userObj.getName());
				session.setAttribute("logid", logUser.getEmail());
				LOGGER.info("login sucess");
				System.out.println("valid user"+userObj);
				return "redirect:../books";
			} 
			else {
				modelMap.addAttribute("msg", "invalid user");
				return "index";
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
				modelMap.addAttribute("regFormData", user);
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
