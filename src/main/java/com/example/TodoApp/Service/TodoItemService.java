package com.example.TodoApp.Service;

import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TodoApp.Models.TodoItems;
import com.example.TodoApp.Repository.TodoItemRepository;

@Service
public class TodoItemService {

	@Autowired
	private TodoItemRepository repository;

	public Iterable<TodoItems> getAll() {
		return repository.findAll();
	}

	public Optional<TodoItems> getById(Long id) {
		return repository.findById(id);
	}

	public TodoItems save(TodoItems todoItems) {
		if (todoItems.getId() == null) {
			todoItems.setCreatedAt(Instant.now());
		}
		todoItems.setUpdatedAt(Instant.now());
		return repository.save(todoItems);
	}

	public void delete(TodoItems todoItems) {
		repository.delete(todoItems);
	}

}
