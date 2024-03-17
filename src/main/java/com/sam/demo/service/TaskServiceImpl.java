package com.sam.demo.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.demo.dto.CountType;
import com.sam.demo.entity.Task;
import com.sam.demo.repo.TaskRepository;
import com.sam.demo.utility.EmailUtils;
import com.sam.demo.utility.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<Task> getTask() {
		return taskRepo.getAllTaskDueDateDesc();
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
	
	@Override
	public List<CountType> getPercentageGroupByType() {
		// TODO Auto-generated method stub
		return taskRepo.getGroupByType();
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("Tasks.pdf");
		List<Task> tasks = taskRepo.findAll();
		boolean status = pdfGenerator.exportPdf(response,tasks,file);
		
		String subject = "Test Mail Subject";
		String body = "<h1>Test Mail Body</h1>";
		String to = "yn.naveen00@gmail.com";
		
		emailUtils.sendMail(subject, body, to, file);

		file.delete();
		return status;
	}
}
