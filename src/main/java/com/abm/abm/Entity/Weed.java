package com.abm.abm.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Weed {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "weed_id", updatable = false, nullable = false)
     private Integer weed_id;
     private String name;

     public Weed() {

     }

     public Weed(Integer weed_id, String name) {
          this.weed_id = weed_id;
          this.name = name;
     }


     public Integer getWeed_id() {
          return this.weed_id;
     }

     public void setWeed_id(Integer weed_id) {
          this.weed_id = weed_id;
     }

     public String getName() {
          return this.name;
     }

     public void setName(String name) {
          this.name = name;
     }
}
