package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	/**
	 * Task Routes
	 */
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/my-tasks")
	public ResponseEntity<List<Task>> getLoggedInUserTasks() {
		
        List<Task> tasks = taskService.getMyAssignedTasks();
        return ResponseEntity.ok(tasks);
    }
	
	@PutMapping("/task-updation/{id}")
	public Task taskUpdation(@RequestBody Task task, @PathVariable Long id) {
		return taskService.taskUpdation(task, id);
	}
}
