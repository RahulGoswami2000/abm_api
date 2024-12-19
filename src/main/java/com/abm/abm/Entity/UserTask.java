package com.abm.abm.Entity;

import jakarta.persistence.*;

@Entity
public class UserTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_task_id", updatable = false, nullable = false)
    private Integer user_task_id;
    @Column(name = "time_on_negative", columnDefinition = "Double(4,2) default '00.00'")
    private Double time_on_negative;
    @Column(name = "time_on_positive", columnDefinition = "Double(4,2) default '00.00'")
    private Double time_on_positive;
    private Integer user_id;
    private Integer count;
    @Column(name = "reward", columnDefinition = "Double(5,2) default '00.00'")
    private Double reward;

    public UserTask(){

    }

    public UserTask(Integer user_task_id, Double time_on_negative, Double time_on_positive, Integer user_id, Integer count, Double reward) {
        this.user_task_id = user_task_id;
        this.time_on_negative = time_on_negative;
        this.time_on_positive = time_on_positive;
        this.user_id = user_id;
        this.reward = reward;
        this.count = count;
    }

    public Integer getUser_task_id() {
		return this.user_task_id;
	}

	public void setUser_task_id(Integer user_task_id) {
		this.user_task_id = user_task_id;
	}


	public Double getTime_on_negative() {
		return this.time_on_negative;
	}

	public void setTime_on_negative(Double time_on_negative) {
		this.time_on_negative = time_on_negative;
	}

	public Double getTime_on_positive() {
		return this.time_on_positive;
	}

	public void setTime_on_positive(Double time_on_positive) {
		this.time_on_positive = time_on_positive;
	}

	public Integer getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getReward() {
		return this.reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}
}
