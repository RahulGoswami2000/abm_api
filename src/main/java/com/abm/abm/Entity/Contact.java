package com.abm.abm.Entity;

import jakarta.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", updatable = false, nullable = false)
    private Integer contact_id;
    private Integer user_id;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String message;


    public Contact() {

    }

    public Contact (Integer contact_id, Integer user_id, String subject, String message) {
        this.contact_id = contact_id;
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
    }

    public Integer getContact_id() {
		return this.contact_id;
	}

	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
	}

	public Integer getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
