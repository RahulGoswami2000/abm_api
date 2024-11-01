package com.abm.abm.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abm.abm.Entity.Weed;

@Repository
public interface WeedRepository extends CrudRepository<Weed, Integer>{
     
}
