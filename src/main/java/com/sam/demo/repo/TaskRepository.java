package com.sam.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sam.demo.dto.CountType;
import com.sam.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {

	@Query(value="select * from task order by due_date desc" ,nativeQuery = true)
	public List<Task> getAllTaskDueDateDesc();
	
	@Query(value="select new com.sam.demo.dto.CountType(COUNT(*)/ (select count(*) from Task) *100, type) from Task GROUP BY type")
	public List<CountType> getGroupByType();
	
}
