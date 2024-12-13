package com.abm.abm.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.abm.abm.Entity.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{
     List<Task> findAllByUserId(Integer user_id);
}
