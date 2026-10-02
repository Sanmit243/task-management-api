package com.san.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.san.spring.demo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
