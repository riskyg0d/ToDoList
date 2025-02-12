package com.ToDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoList.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel ,Long> {

}
