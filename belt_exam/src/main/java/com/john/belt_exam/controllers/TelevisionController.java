package com.john.belt_exam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.john.belt_exam.models.Television;
import com.john.belt_exam.models.User;
import com.john.belt_exam.services.TelevisionService;
import com.john.belt_exam.services.UserService;

@Controller
public class TelevisionController {
	@Autowired
	private TelevisionService televisionService;
	@Autowired
	private UserService userServ;

	@RequestMapping("/televisions")
	public String showtelevision(@ModelAttribute("television") Television television, Model model, HttpSession s) {
		List<Television> allTelevisions = televisionService.allTelevisions();
		model.addAttribute("allTelevisions", allTelevisions);
		// Route guard - check if user is in session
		Long userId = (Long) s.getAttribute("user_id");
		// check if userID is null
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisLoggedInUser = userServ.findOne(userId);
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
			return "television.jsp";
		}
	}
	
	

	// Create TELEVISION JSP
	@GetMapping("/new")
	public String newTelevision(@ModelAttribute("television") Television television, Model model, HttpSession s) {
		// Route guard - check if user is in session
		Long userId = (Long) s.getAttribute("user_id");
		// check if userID is null
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisLoggedInUser = userServ.findOne(userId);
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);

			List<Television> allTelevisions = televisionService.allTelevisions();
			model.addAttribute("allTelevisions", allTelevisions);
			return "/new.jsp";
		}
	}

	// Create Method
	@PostMapping("/televisions")
	public String create(@Valid @ModelAttribute("television") Television television, BindingResult result, Model model,
			HttpSession s) {
		if (result.hasErrors()) {
			// GRAB THE USER AGAIN
			Long userId = (Long) s.getAttribute("user_id");
			User thisLoggedInUser = userServ.findOne(userId);
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
			return "/new.jsp";
		} else {
			televisionService.createTelevision(television);
			return "redirect:/televisions";
		}
	}
	
	
	
	
	

	// Show One Render
	@GetMapping("/televisions/{id}")
	public String showOne(@PathVariable("id") Long id, Model model, HttpSession s) {
		// Route guard - check if user is in session
		Long userId = (Long) s.getAttribute("user_id");
		// check if userID is null
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisLoggedInUser = userServ.findOne(userId);
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
		Television oneTelevision = televisionService.findTelevision(id);
		model.addAttribute("oneTelevision", oneTelevision);
		return "/show.jsp";
	}
	}

	// Edit JSP
	@RequestMapping("/televisions/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession s) {
		// Route guard - check if user is in session
		Long userId = (Long) s.getAttribute("user_id");
		// check if userID is null
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisLoggedInUser = userServ.findOne(userId);
			model.addAttribute("thisLoggedInUser", thisLoggedInUser);
		Television television = televisionService.findTelevision(id);
		model.addAttribute("television", television);
		return "/edit.jsp";
	}
	}

	// EDIT Method
	@RequestMapping(value = "/televisions/{id}", method = RequestMethod.PUT)
	public String update(@Valid @ModelAttribute("television") Television television, BindingResult result) {
		if (result.hasErrors()) {
			return "/edit.jsp";
		} else {
			televisionService.updateTelevision(television);
			return "redirect:/televisions";
		}
	}

	// Delete
	@RequestMapping(value = "/televisions/{id}/edit", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
		televisionService.deleteTelevision(id);
		return "redirect:/televisions";
	}

}
