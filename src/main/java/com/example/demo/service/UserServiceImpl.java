package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getSpecificUser(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public String specificUserDeletion(Long id) {
		userRepository.deleteById(id);
		return "User Successfully Deleted with ID " + id;
	}

	@Override
	public long getTotalEmployeesCount() {
		return userRepository.countByRole("ROLE_EMPLOYEE");
	}

	@Override
	public User userUpdation(Long id, User user) {
		User existingUser = userRepository.findById(id).orElseThrow();
		existingUser.setUsername(user.getUsername());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(encoder.encode(user.getPassword()));
		
		return userRepository.save(existingUser);
	}
	
	
}
