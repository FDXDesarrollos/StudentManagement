package com.fdxdesarrollos.studentManagement.dao;

import java.util.List;

import com.fdxdesarrollos.studentManagement.model.Course;
import com.fdxdesarrollos.studentManagement.model.Dashboard;
import com.fdxdesarrollos.studentManagement.model.Grade;
import com.fdxdesarrollos.studentManagement.model.Student;

import javafx.collections.ObservableList;

public interface IDashboard {
	/***   Home   ***/
	public Dashboard totalsDashboard();
	public List<Dashboard> chartsDashboard(String gender);
	
	/***   Add Students   ***/
	
	public ObservableList<Student> studentList();
	public boolean existStudent(Student student);
	public boolean saveStudent (Student student);
	public void deleteStudent(Student student);
	
	/***   Available Couse   ***/
	
	public ObservableList<Course> courseList();
	public boolean existCourse(Course course);
	public boolean saveCourse (Course course);
	public void deleteCourse(Course course);
	
	/***   Student Grade   ***/
	
	public ObservableList<Grade> gradeList();
	public boolean saveGrade (Grade grade);
}
