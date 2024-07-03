package com.fdxdesarrollos.studentManagement.model;

public class Grade {
	private Integer id = 0;
	private Integer studentNum = 0;
	private String year = "";
	private String course = "";
	private Double first = 0.0;
	private Double second = 0.0;
	private Double finale = 0.0;
	
	public Grade() {
		super();
	}

	public Grade(Integer id, Integer studentNum, String year, String course, Double first, Double second,
			Double finale) {
		super();
		this.id = id;
		this.studentNum = studentNum;
		this.year = year;
		this.course = course;
		this.first = first;
		this.second = second;
		this.finale = finale;
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

	public Double getFirst() {
		return first;
	}

	public void setFirst(Double first) {
		this.first = first;
	}

	public Double getSecond() {
		return second;
	}

	public void setSecond(Double second) {
		this.second = second;
	}

	public Double getFinale() {
		return finale;
	}

	public void setFinale(Double finale) {
		this.finale = finale;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", studentNum=" + studentNum + ", year=" + year + ", course=" + course + ", first="
				+ first + ", second=" + second + ", finale=" + finale + "]";
	}
	
}
