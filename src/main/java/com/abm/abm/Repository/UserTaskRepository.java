package com.abm.abm.Repository;

import com.abm.abm.Entity.UserTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTaskRepository extends CrudRepository<UserTask, Integer> {

    @Query(value = "select * from user_task where user_id = ?1", nativeQuery = true)
    Optional<UserTask> findUser(Integer integer);

    @Query(value = "select * from user_task where user_id = ?1", nativeQuery = true)
    Optional<UserTask> getTime(Integer integer);
}
