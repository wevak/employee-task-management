package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task taskCreation(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Long totalTasksCount() {
		return taskRepository.count();
	}

	@Override
	public Long totalPendingTasks() {
		return taskRepository.countByStatus("pending");
	}

	@Override
	public Long totalCompletedTasks() {
		return taskRepository.countByStatus("completed");
	}

	/**
	 * Employee services
	 */
	@Override
	public List<Task> getMyAssignedTasks() {
		// 1. Get the username of the currently logged-in user from Security Context
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // 2. Fetch the User entity from the database (assuming your repository has findByUsername)
        User currentUser = userRepository.findByUsername(currentUsername);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUsername));

        // 3. Query and return the tasks assigned to this user
        return taskRepository.findByAssignedEmployee(currentUser);
	}

	@Override
	public Task taskUpdation(Task task, Long id) {
		Task existingTask = taskRepository.findById(id).orElseThrow();
		
		existingTask.setStatus(task.getStatus());
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		
		/**
		 * to be updated
		 */
		existingTask.setAssignedEmployee(task.getAssignedEmployee());
		existingTask.setCreatedBy(task.getCreatedBy());
		
		return taskRepository.save(existingTask);
	}

	@Override
	public String taskDeletion(Long id) {
		taskRepository.deleteById(id);
		return "Task with id " + id + " successfully deleted";
	}
}
