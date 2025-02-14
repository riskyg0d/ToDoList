package com.ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ToDoList.model.TaskModel;
import com.ToDoList.model.UserModel;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.repository.UserRepository;

import java.util.*;

@Service
public class TaskService {
	@Autowired 
	private TaskRepository taskrepository;
	@Autowired
	private UserRepository userRepository;
	public TaskModel createTask(TaskModel taskm, long userId) {
	    // Fetch the user from the database using the userId
	    UserModel user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	    // Set the user for the task
	    taskm.setUser(user);

	    // Save the task and return the saved task
	    return taskrepository.save(taskm);
	}

	public List<TaskModel> getAllTasks()
	{
		return taskrepository.findAll();
	}
	public TaskModel updateTask(Long taskid, TaskModel updatedTask)
	{
		if(taskrepository.existsById(taskid))
		{
			updatedTask.setTaskid(taskid);
			return taskrepository.save(updatedTask);
		}
		return null;
	}
	public void deleteTask(Long taskid)
	{
		taskrepository.deleteById(taskid);	
	}
	public Optional<TaskModel> getTaskById(Long taskid) 
	{
	    return taskrepository.findById(taskid);
	}

	
}
