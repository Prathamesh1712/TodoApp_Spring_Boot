package com.example.TodoApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import com.example.TodoApp.Service.TodoItemService;

@Controller
public class HomeController {

	@Autowired
	private TodoItemService service;

	
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("todoItems", service.getAll());
		return modelAndView;

	}

}
