package com.ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoList.model.UserModel;
import com.ToDoList.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userrepository;
	
	public UserModel registerUser(UserModel userm)
	{
		return userrepository.save(userm);
	}
	public UserModel getUserbyEmail(String email)
	{
		return userrepository.findByEmail(email);
		
	}
}
