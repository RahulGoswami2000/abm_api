package com.abm.abm.Repository;

import com.abm.abm.Entity.Survey;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {

     @Query(value = "select * from survey where user_id=?1", nativeQuery=true)
     Survey findSurveys(Integer integer);

}
