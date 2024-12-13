package com.abm.abm.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "task_id", updatable = false, nullable = false)
     private Integer task_id;
     @Column(name = "user_id")
     private Integer userId;
     @Column(name = "negative", columnDefinition = "Double(4,2) default '00.00'")
     private Double negative;
     @Column(name = "positive", columnDefinition = "Double(4,2) default '00.00'")
     private Double positive;


     public Task() {

     }

     public Task(Integer task_id, Integer userId, Double negative, Double positive) {
          this.task_id = task_id;
          this.userId = userId;
          this.negative = negative;
          this.positive = positive;
     }

     public Integer getTask_id() {
          return this.task_id;
     }

     public void setTask_id(Integer task_id) {
          this.task_id = task_id;
     }

     public Integer getUser_id() {
          return this.userId;
     }

     public void setUser_id(Integer user_id) {
          this.userId = user_id;
     }

     public Double getNegative() {
          return this.negative;
     }

     public void setNegative(Double negative) {
          this.negative = negative;
     }

     public Double getPositive() {
          return this.positive;
     }

     public void setPositive(Double positive) {
          this.positive = positive;
     }

}
