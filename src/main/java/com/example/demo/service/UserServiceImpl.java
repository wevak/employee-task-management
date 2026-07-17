package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
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
	
}
