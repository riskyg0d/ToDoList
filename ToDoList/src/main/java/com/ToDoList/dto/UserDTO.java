package com.ToDoList.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserDTO {
	@Getter @Setter private Long id;
	@Getter @Setter private String email;
	@Getter @Setter private List<TaskDTO> tasks; // Prevents infinite recursion
}
