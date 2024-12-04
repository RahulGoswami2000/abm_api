package com.abm.abm.Entity;

import jakarta.persistence.*;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id", updatable = false, nullable = false)
    private Integer survey_id;
    private Integer user_id;
    private String question_1;
    private String question_2;
    private String question_3;
    private String question_4;
    private String question_5;
    private String question_6;
    private String question_7;
    private String question_8;


    public Survey() {

    }

    public Survey(Integer survey_id, Integer user_id, String question_1, String question_2, String question_3, String question_4, String question_5, String question_6, String question_7, String question_8) {
        this.survey_id = survey_id;
        this.user_id = user_id;
        this.question_1 = question_1;
        this.question_2 = question_2;
        this.question_3 = question_3;
        this.question_4 = question_4;
        this.question_5 = question_5;
        this.question_6 = question_6;
        this.question_7 = question_7;
        this.question_8 = question_8;
    }

	public Integer getSurvey_id() {
		return this.survey_id;
	}

	public void setSurvey_id(Integer survey_id) {
		this.survey_id = survey_id;
	}

	public Integer getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getQuestion_1() {
		return this.question_1;
	}

	public void setQuestion_1(String question_1) {
		this.question_1 = question_1;
	}

	public String getQuestion_2() {
		return this.question_2;
	}

	public void setQuestion_2(String question_2) {
		this.question_2 = question_2;
	}

	public String getQuestion_3() {
		return this.question_3;
	}

	public void setQuestion_3(String question_3) {
		this.question_3 = question_3;
	}

	public String getQuestion_4() {
		return this.question_4;
	}

	public void setQuestion_4(String question_4) {
		this.question_4 = question_4;
	}

	public String getQuestion_5() {
		return this.question_5;
	}

	public void setQuestion_5(String question_5) {
		this.question_5 = question_5;
	}

	public String getQuestion_6() {
		return this.question_6;
	}

	public void setQuestion_6(String question_6) {
		this.question_6 = question_6;
	}

	public String getQuestion_7() {
		return this.question_7;
	}

	public void setQuestion_7(String question_7) {
		this.question_7 = question_7;
	}

	public String getQuestion_8() {
		return this.question_8;
	}

	public void setQuestion_8(String question_8) {
		this.question_8 = question_8;
	}

}
