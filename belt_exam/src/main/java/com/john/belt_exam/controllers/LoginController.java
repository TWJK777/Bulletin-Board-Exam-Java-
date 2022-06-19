package com.john.belt_exam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.john.belt_exam.models.LoginUser;
import com.john.belt_exam.models.User;
import com.john.belt_exam.services.UserService;


@Controller
public class LoginController {

	@Autowired
	private UserService userServ;
	
	
// Login	
	@GetMapping("/")
	public String loginReg(Model model) {
		 model.addAttribute("newUser", new User());
	     model.addAttribute("newLogin", new LoginUser());
	     return "index.jsp";
	}
	
// REGISTER NEW USER
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession sesh) {
		userServ.register(newUser, result);
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {
			userServ.register(newUser, result);
			sesh.setAttribute("user_id", newUser.getId());
			return "redirect:/televisions";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession sesh) {
		User user = userServ.login(newLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {
			sesh.setAttribute("user_id", user.getId());
			return "redirect:/televisions";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "redirect:/";
	}
}
