package com.example.TodoApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.TodoApp.Models.TodoItems;
import com.example.TodoApp.Service.TodoItemService;

import jakarta.validation.Valid;

@Controller
public class TodoFormController {

	@Autowired
	private TodoItemService service;

	@GetMapping("/create-todo")
	public String showCreateForm(TodoItems todoItems) {
		return "new-todo-item";
	}

	@PostMapping("/todo")
	public String createTodoItem(@Valid TodoItems todoItems, BindingResult result, Model model) {

		TodoItems item = new TodoItems();
		item.setDescription(todoItems.getDescription());
		item.setIsComplete(todoItems.getIsComplete());

		service.save(todoItems);
		return "redirect:/";

	}

	@GetMapping("/delete/{id}")
	public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
		TodoItems todoItems = service.getById(id)
				.orElseThrow(() -> new IllegalArgumentException("TODOITEM ID: " + id + "NOT FOUND!"));

		service.delete(todoItems);
		return "redirect:/";

	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		TodoItems todoItem = service.getById(id)
				.orElseThrow(() -> new IllegalArgumentException("TODOITEM ID: " + id + "NOT FOUND!"));

		model.addAttribute("todo", todoItem);

		return "edit-todo-item";
	}

	@PostMapping("/todo/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, @Valid TodoItems todoItem, BindingResult result,
			Model model) {
		TodoItems item = service.getById(id)
				.orElseThrow(() -> new IllegalArgumentException("TODOITEM ID: " + id + "NOT FOUND!"));

		item.setIsComplete(todoItem.getIsComplete());
		item.setDescription(todoItem.getDescription());

		service.save(item);
		return "redirect:/";

	}

}
