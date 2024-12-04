package com.abm.abm.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.abm.abm.Entity.MstUsers;

@Repository
public interface UserRepository extends CrudRepository<MstUsers, Integer>{
     Optional<MstUsers> findById(Integer id);

     @Query(value = "select * from mst_users where user_id = ?1", nativeQuery = true)
     List<Map<String, Object>> me(Integer id);

     boolean existsByEmail(String email);
}
