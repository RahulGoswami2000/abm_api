package com.abm.abm.Entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class MstUsers {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "user_id", updatable = false, nullable = false)
     private Integer user_id;
     private String first_name;
     private String last_name;
     private String email;
     private String password;
     private Integer user_type;
     @Temporal(TemporalType.TIMESTAMP)
     private LocalDateTime created_at;
     @Temporal(TemporalType.TIMESTAMP)
     private LocalDateTime updated_at;
     @Temporal(TemporalType.TIMESTAMP)
     private LocalDateTime deleted_at;
     private Integer weed_id;

     public MstUsers() {

     }

     public MstUsers(Integer user_id, String first_name, String last_name,String email, String password, LocalDateTime created_at, LocalDateTime deleted_at, LocalDateTime updated_at, Integer user_type, Integer weed_id) {
          this.user_id = user_id;
          this.first_name = first_name;
          this.email = email;
          this.password = password;
          this.last_name = last_name;
          this.created_at = created_at;
          this.updated_at = updated_at;
          this.deleted_at = deleted_at;
          this.user_type = user_type;
          this.weed_id = weed_id;
     }

     public Integer getUser_id() {
          return this.user_id;
     }

     public void setUser_id(Integer user_id) {
          this.user_id = user_id;
     }

     public String getFirst_name() {
          return this.first_name;
     }

     public void setFirst_name(String first_name) {
          this.first_name = first_name;
     }

     public String getLast_name() {
          return this.last_name;
     }

     public void setLast_name(String last_name) {
          this.last_name = last_name;
     }

     public String getEmail() {
          return this.email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPassword() {
          return this.password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public Integer getUser_type() {
          return this.user_type;
     }

     public void setUser_type(Integer user_type) {
          this.user_type = user_type;
     }

     public LocalDateTime getCreated_at() {
          return this.created_at;
     }

     public void setCreated_at(LocalDateTime created_at) {
          this.created_at = created_at;
     }

     public LocalDateTime getUpdated_at() {
          return this.updated_at;
     }

     public void setUpdated_at(LocalDateTime updated_at) {
          this.updated_at = updated_at;
     }

     public LocalDateTime getDeleted_at() {
          return this.deleted_at;
     }

     public void setDeleted_at(LocalDateTime deleted_at) {
          this.deleted_at = deleted_at;
     }

     @PrePersist
     public void onCreate() {
          created_at = LocalDateTime.now();
          updated_at = LocalDateTime.now();
     }

     @PreUpdate
     public void onUpdate() {
          updated_at = LocalDateTime.now();
     }

     public Integer getWeed_id() {
          return this.weed_id;
     }

     public void setWeed_id(Integer weed_id) {
          this.weed_id = weed_id;
     }

}
