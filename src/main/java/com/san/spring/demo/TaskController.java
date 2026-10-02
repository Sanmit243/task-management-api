package com.san.spring.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.san.spring.demo.model.Task;
import com.san.spring.demo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	ResponseEntity<List<Task>> getTasks() {
		
		return ResponseEntity.ok(taskService.getAllTasks());
		
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Task> getTask(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.getTaskById(id));
	}
	
	@PostMapping
	ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(task));
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Task> updateTask(@RequestBody @Valid Task task, @PathVariable Long id) {
		
		return ResponseEntity.ok(taskService.updateTask(id, task));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Object> deleteTask(@PathVariable Long id) {
		
		taskService.deleteTask(id);

		return ResponseEntity.noContent().build();
	}
}
