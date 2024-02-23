package com.sam.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.demo.entity.Task;
import com.sam.demo.repo.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Override
	public List<Task> getTask() {
		return taskRepo.findAll();
	}
	
	@Override
	public Task save(Task task) {
		// TODO Auto-generated method stub
		return taskRepo.save(task);
	}
	
	@Override
	public boolean checkById(Integer Id) {
		// TODO Auto-generated method stub
		return taskRepo.existsById(Id);
	}
	
	@Override
	public Optional<Task> getTaskById(Integer Id) {
		// TODO Auto-generated method stub
		return taskRepo.findById(Id);
	}
	
	@Override
	public void delete(Integer Id) {
		// TODO Auto-generated method stub
		taskRepo.deleteById(Id);
	}
}
