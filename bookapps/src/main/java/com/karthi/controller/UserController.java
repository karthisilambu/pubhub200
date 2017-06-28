package com.karthi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.karthi.model.User;
import com.karthi.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public String list(HttpSession session) {
		List<User> users = userService.findAll();
		System.out.println(users);
		session.setAttribute("USERS_LIST", users);
return "user/allusers";
} 
}
