package com.ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ToDoList.dto.TaskDTO;
import com.ToDoList.model.TaskModel;
import com.ToDoList.model.UserModel;
import com.ToDoList.repository.TaskRepository;
import com.ToDoList.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired 
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Task (Returns DTO instead of Model)
    public TaskDTO createTask(TaskDTO taskDTO, long userId) {
        // Fetch user from DB
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert DTO to Model
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskid(taskDTO.getTaskid());
        taskModel.setTaskname(taskDTO.getTaskname());
        taskModel.setStatus(taskDTO.getStatus());
        taskModel.setDescription(taskDTO.getDescription());
        taskModel.setDuedate(taskDTO.getDuedate());
        taskModel.setCreatedAt(taskDTO.getCreatedAt());
        taskModel.setUpdatedAt(taskDTO.getUpdatedAt());
        taskModel.setUser(user);

        // Save Task
        TaskModel savedTask = taskRepository.save(taskModel);

        // Convert Model to DTO & Return
        return convertToTaskDTO(savedTask);
    }

    // Get All Tasks (Returns List of DTOs)
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }

    // Get Task by ID (Returns DTO)
    public Optional<TaskDTO> getTaskById(Long taskId) {
        return taskRepository.findById(taskId).map(this::convertToTaskDTO);
    }

    // Update Task (Returns DTO)
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTaskDTO) {
        TaskModel taskModel = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Update only changed fields
        if (updatedTaskDTO.getTaskname() != null) {
            taskModel.setTaskname(updatedTaskDTO.getTaskname());
        }
        if (updatedTaskDTO.getStatus() != null) {
            taskModel.setStatus(updatedTaskDTO.getStatus());
        }
        if (updatedTaskDTO.getDescription() != null) {
            taskModel.setDescription(updatedTaskDTO.getDescription());
        }
        if (updatedTaskDTO.getDuedate() != null) {
            taskModel.setDuedate(updatedTaskDTO.getDuedate());
        }
        if (updatedTaskDTO.getCreatedAt() != null) {
            taskModel.setCreatedAt(updatedTaskDTO.getCreatedAt());
        }
        if (updatedTaskDTO.getUpdatedAt() != null) {
            taskModel.setUpdatedAt(updatedTaskDTO.getUpdatedAt());
        }

        // Save and return DTO
        TaskModel updatedTask = taskRepository.save(taskModel);
        return convertToTaskDTO(updatedTask);
    }

    // Delete Task
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    // Convert TaskModel to TaskDTO
    public TaskDTO convertToTaskDTO(TaskModel taskModel) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskid(taskModel.getTaskid());
        taskDTO.setTaskname(taskModel.getTaskname());
        taskDTO.setStatus(taskModel.getStatus());
        taskDTO.setDescription(taskModel.getDescription());
        taskDTO.setDuedate(taskModel.getDuedate());
        taskDTO.setCreatedAt(taskModel.getCreatedAt());
        taskDTO.setUpdatedAt(taskModel.getUpdatedAt());
        return taskDTO;
    }
}
