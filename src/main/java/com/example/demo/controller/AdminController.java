package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	/**
	 * User Routes
	 */
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		try {
			return ResponseEntity.ok(userService.getAllUsers());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getSpecificUser(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(userService.getSpecificUser(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/user-deletion/{id}")
	public ResponseEntity<?> userDeletion(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(userService.specificUserDeletion(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping("/user-updation/{id}")
	public ResponseEntity<?> userUpdation(@PathVariable Long id, @RequestBody User user) {
		try {
			return ResponseEntity.ok(userService.userUpdation(id, user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Task Routes
	 */
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public ResponseEntity<?> getAllTasks() {
		try {
			return ResponseEntity.ok(taskService.getAllTasks());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/task-add")
	public ResponseEntity<?> taskCreation(@RequestBody Task task) {
		try {
//			User assignedEmpId = userService.getSpecificUser(task.assignedEmployee) 
			
			return ResponseEntity.status(HttpStatus.CREATED).body(taskService.taskCreation(task));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/task-deletion/{id}")
	public ResponseEntity<?> taskDeletion(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(taskService.taskDeletion(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	/**
	 * Dashboard Stats
	 */
	@GetMapping("/total-employees")
	public ResponseEntity<?> totalEmplyeesCount() {
		try {			
			return ResponseEntity.ok(userService.getTotalEmployeesCount());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/total-tasks")
	public ResponseEntity<?> totalTasksCount() {
		try {			
			return ResponseEntity.ok(taskService.totalTasksCount());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/total-pending-tasks")
	public ResponseEntity<?> totalPendingTasksCount() {
		try {			
			return ResponseEntity.ok(taskService.totalPendingTasks());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/total-completed-tasks")
	public ResponseEntity<?> totalCompletedTasksCount() {
		try {			
			return ResponseEntity.ok(taskService.totalCompletedTasks());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
