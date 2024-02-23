package com.sam.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {

}
