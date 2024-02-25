package com.sam.demo.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.sam.demo.dto.CountType;
import com.sam.demo.entity.Task;
import com.sam.demo.service.TaskService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin("*")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/task")
	public List<Task> getTask(){
		return taskService.getTask();
	}
	
	@PostMapping("/task")
	public Task addTask(@RequestBody Task task) {
		return taskService.save(task);
	}
	
	@GetMapping("task/{id}")
	public Task getById(@PathVariable Integer id) {
		return taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested task not found"));
	}
	
	@PutMapping("/task/{id}")
	public ResponseEntity<?> addTask(@RequestBody Task taskPara, @PathVariable Integer id) {
		if(taskService.checkById(id)) {
			Task task = taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested task not found"));
			task.setTitle(taskPara.getTitle());
			task.setDescription(taskPara.getDescription());
			task.setDueDate(taskPara.getDueDate());
			task.setType(taskPara.getType());
			taskService.save(task);
			return ResponseEntity.ok().body(task);
		}else {
			// To be changed
			System.out.println("Not found");
			HashMap<String, String> message = new HashMap<>();
			message.put("message", id + "task not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
		if(taskService.checkById(id)) {
			taskService.delete(id);
			HashMap<String, String> message = new HashMap<>();
			message.put("message ", id + "task deleted");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}else {
			// To be changed
			System.out.println("Not found");
			HashMap<String, String> message = new HashMap<>();
			message.put("message", id + "task not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	
	@GetMapping("task/vData/percent")
	public List<CountType> getCountTask(){
		return taskService.getPercentageGroupByType();
	}
	
	
	}
