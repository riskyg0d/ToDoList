package com.ToDoList.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoList.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

	public UserModel findByEmail(String email);
}
