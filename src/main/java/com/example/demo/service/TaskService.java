package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Task;

public interface TaskService {
	List<Task> getAllTasks();
	
	Task taskCreation(Task task);
}
