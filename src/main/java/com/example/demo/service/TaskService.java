package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Task;

public interface TaskService {
	/**
	 * Admin
	 */
	
	List<Task> getAllTasks();
	
	Task taskCreation(Task task);
	
	String taskDeletion(Long id);
	
	Long totalTasksCount();
	
	Long totalPendingTasks();
	
	Long totalCompletedTasks();

	/**
	 * Employee
	 */
	
	List<Task> getMyAssignedTasks();
	
	Task taskUpdation(Task task, Long id);
}
