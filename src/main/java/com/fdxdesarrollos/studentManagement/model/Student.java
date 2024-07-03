package com.fdxdesarrollos.studentManagement.model;

import java.util.Date;

public class Student {
	private Integer id = 0;
	private Integer studentNum = 0;
	private String year = "";
	private String course = "";
	private String firstName = "";
	private String lastName = "";
	private String gender = "";
	private Date birth = null;
	private String status = "";
	private String image = "";
	
	
	public Student() {
		super();
	}
	
	public Student(Integer id, Integer studentNum, String year, String course, 
			       String firstName, String lastName, String gender,
			       Date birth, String status, String image) {
		super();
		this.id = id;
		this.studentNum = studentNum;
		this.year = year;
		this.course = course;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birth = birth;
		this.status = status;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentNum=" + studentNum + ", year=" + year + ", course=" + course + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender + ", birth=" + birth + ", status="
				+ status + ", image=" + image + "]";
	}
	
}
