package com.example.TodoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TodoApp.Models.TodoItems;

public interface TodoItemRepository extends JpaRepository<TodoItems, Long> {

}
