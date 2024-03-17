package com.sam.demo.service;

import java.util.List;
import java.util.Optional;

import com.sam.demo.dto.CountType;
import com.sam.demo.entity.Task;

import jakarta.servlet.http.HttpServletResponse;

public interface TaskService {
	
	public List<Task> getTask();

	public Task save(Task task);
	
	public boolean checkById(Integer Id);
	
	public Optional<Task> getTaskById(Integer Id);

	public void delete(Integer Id);
	
	public List<CountType> getPercentageGroupByType();
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;

}
