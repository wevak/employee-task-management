package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Task;
import com.example.demo.model.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
	long countByStatus(String status);
	
	List<Task> findByAssignedEmployee(User assignedEmployee);
}
