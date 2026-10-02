package com.san.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.san.spring.demo.exception.TaskNotFoundException;
import com.san.spring.demo.model.Task;
import com.san.spring.demo.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAllTasks() {

		return taskRepository.findAll();
	}

	public Task getTaskById(Long id) {

		return taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("The Task was not found with the id " + id));

	}

	public Task addTask(Task task) {

		return taskRepository.save(task);

	}

	public Task updateTask(Long id, Task task) {
//		try {
//			Optional<Task> res = taskRepository.findById(id);
//
//			res.get().setTitle(task.getTitle());
//			res.get().setDescription(task.getDescription());
//			res.get().setCompleted(task.isCompleted());
//			
//			return taskRepository.save(res.get());
//		}
//		catch(Exception e) {
//			throw new RuntimeException("The required data is not present!");
//		}

		Optional<Task> res = taskRepository.findById(id);

		Task existingTask = res
				.orElseThrow(() -> new TaskNotFoundException("The Task was not found with the id " + id));
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());

		return taskRepository.save(existingTask);

	}

	public void deleteTask(Long id) {

		taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException("The Task was not found with the id " + id));

		taskRepository.deleteById(id);

		System.out.println("The data has been deleted successfully if present!");


	}

}
