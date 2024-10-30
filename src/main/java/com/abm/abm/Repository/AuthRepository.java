package com.abm.abm.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abm.abm.Entity.MstUsers;

@Repository
public interface AuthRepository extends CrudRepository<MstUsers, Integer>{
     MstUsers findOneByEmail(String email);

     MstUsers findOneByEmailAndPassword(String email, String password);
}
