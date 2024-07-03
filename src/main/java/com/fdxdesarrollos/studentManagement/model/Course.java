package com.fdxdesarrollos.studentManagement.model;

public class Course {
	private Integer id = 0;
	private String course = "";
	private String description = "";
	private String degree = "";
	
	public Course() {
		super();
	}
	
	public Course(Integer id, String course, String description, String degree) {
		super();
		this.id = id;
		this.course = course;
		this.description = description;
		this.degree = degree;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", course=" + course + ", description=" + description + ", degree=" + degree + "]";
	}
	
}
