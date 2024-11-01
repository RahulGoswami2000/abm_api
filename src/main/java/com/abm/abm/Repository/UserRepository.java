package com.abm.abm.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.abm.abm.Entity.MstUsers;

@Repository
public interface UserRepository extends CrudRepository<MstUsers, Integer>{
     Optional<MstUsers> findById(Integer id);
}
