package com.fdxdesarrollos.studentManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fdxdesarrollos.studentManagement.model.Course;
import com.fdxdesarrollos.studentManagement.model.Dashboard;
import com.fdxdesarrollos.studentManagement.model.Grade;
import com.fdxdesarrollos.studentManagement.model.Student;
import com.fdxdesarrollos.studentManagement.util.DBConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DashboardImp implements IDashboard {
	private Connection conn = null;
	private Statement stm = null;
	private PreparedStatement pstm = null;
	private ResultSet rst = null;
	private String sql = "";
	
	/***   Home   ***/
	@Override
	public Dashboard totalsDashboard() {
		Dashboard obj = new Dashboard();
		
		try {
			conn = DBConnection.getInstance().getConnection();
            stm = conn.createStatement();
            rst = stm.executeQuery("SELECT gender, count(gender) as total " +
            					   "FROM student " +
            		               "WHERE status = 'Enrolled' " +
            					   "GROUP BY gender " +
            					   "UNION " +
            					   "SELECT 'Total', COUNT(id) FROM student WHERE status = 'Enrolled';");	
			while(rst.next()) {
				switch(rst.getString("gender")) {
					case "Female":
						obj.setTotalFemale(String.format("%02d", rst.getInt("total")));
						break;
					case "Male":
						obj.setTotalMale(String.format("%02d", rst.getInt("total")));
						break;
					case "Total":
						obj.setTotalEnrolled(String.format("%02d", rst.getInt("total")));
						break;
				}
			}
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
			return null;
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
					
		return obj;
	}
	

	@Override
	public List<Dashboard> chartsDashboard(String gender) {
		List<Dashboard> datos = new ArrayList<Dashboard>();
		
		try {
			conn = DBConnection.getInstance().getConnection();
            stm = conn.createStatement();
            
            if(gender.equals("")) {
            	sql = "SELECT dateReg, COUNT(id) AS total " +
                	  "FROM student " +
                	  "GROUP BY dateReg, status " +
                	  "HAVING status = 'Enrolled' " + 
                	  "ORDER BY TIMESTAMP(dateReg) ASC;";
            }
            else { 
            	sql = "SELECT dateReg, COUNT(id) AS total " +
            		  "FROM student " +
            		  "GROUP BY dateReg, gender, status " +
            		  "HAVING status = 'Enrolled' AND gender = '" + gender + "' " +
            		  "ORDER BY TIMESTAMP(dateReg) ASC;";
            }
            
            rst = stm.executeQuery(sql);	
			while(rst.next()) {
				Dashboard obj = new Dashboard();
				obj.setDateReg(rst.getString("dateReg"));
				obj.setTotalEnrolled(rst.getString("total"));
				datos.add(obj);
			}
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
			return null;
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
					
		return datos;
	}	
	
	/***   Student   ***/
	@Override
	public ObservableList<Student> studentList() {
		Student student;
		ObservableList<Student> listStudents = FXCollections.observableArrayList();
		
		try {
			conn = DBConnection.getInstance().getConnection();
            stm = conn.createStatement();
            rst = stm.executeQuery("SELECT * FROM student;");			
			
            while(rst.next()) {
            	student = new Student();
            	student.setId(rst.getInt("id"));
            	student.setStudentNum(rst.getInt("studentNum"));
            	student.setYear(rst.getString("year"));
            	student.setCourse(rst.getString("course"));
            	student.setFirstName(rst.getString("firstName"));
            	student.setLastName(rst.getString("lastName"));
            	student.setGender(rst.getString("gender"));
            	student.setBirth(rst.getDate("birth"));
            	student.setStatus(rst.getString("status"));
            	student.setImage(rst.getString("image"));
            	listStudents.add(student);
            }
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return listStudents;
	}
	
	
	@Override
	public boolean existStudent(Student student) {
		boolean bflag = false;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			pstm = conn.prepareStatement("SELECT id FROM student WHERE studentNum=?;");
			pstm.setInt(1, student.getStudentNum());
			rst = pstm.executeQuery();	
			if(rst.next()) bflag = true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return bflag;		
	}
	
	@Override
	public boolean saveStudent(Student student) {
		boolean bflag = false;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			
			if(student.getId().equals(0))
				sql = "INSERT INTO student (studentNum, year, course, firstName, lastName, gender, birth, status, image, dateReg) " +
					  "VALUES(?,?,?,?,?,?,?,?,?, CURDATE());";
			else
				sql = "UPDATE student SET studentNum=?, year=?, course=?, firstName=?, lastName=?, gender=?, birth=?, status=?, image=? dateReg=CURDATE() WHERE id=?;";
			
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, student.getStudentNum());
			pstm.setString(2, student.getYear());
			pstm.setString(3, student.getCourse());
			pstm.setString(4, student.getFirstName());
			pstm.setString(5, student.getLastName());
			pstm.setString(6, student.getGender());
			pstm.setDate(7, new java.sql.Date( student.getBirth().getTime()) ); 
			pstm.setString(8, student.getStatus());
			pstm.setString(9, student.getImage());
			
			if(!student.getId().equals(0)) {
				pstm.setInt(10, student.getId());
			}
			
			pstm.executeUpdate();
			bflag = true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return bflag;
	}	
	
	@Override
	public void deleteStudent(Student student) {
		try {
			conn = DBConnection.getInstance().getConnection();
			pstm = conn.prepareStatement("DELETE FROM student WHERE id=?;");
			pstm.setInt(1, student.getId());
			pstm.executeQuery();	
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}		
	}
	
	
	/***   Available Couse   ***/
	@Override
	public ObservableList<Course> courseList(){
		Course course;
		ObservableList<Course> listCourse = FXCollections.observableArrayList();
		
		try {
			conn = DBConnection.getInstance().getConnection();
            stm = conn.createStatement();
            rst = stm.executeQuery("SELECT * FROM course;");			
			
            while(rst.next()) {
            	course = new Course();
            	course.setId(rst.getInt("id"));
            	course.setCourse(rst.getString("course"));
            	course.setDescription(rst.getString("description"));
            	course.setDegree(rst.getString("degree"));	
            	listCourse.add(course);
            }
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return listCourse;		
	}
	
	@Override
	public boolean existCourse(Course course) {
		boolean bflag = false;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			pstm = conn.prepareStatement("SELECT id FROM course WHERE course=?;");
			pstm.setString(1, course.getCourse());
			rst = pstm.executeQuery();	
			if(rst.next()) bflag = true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return bflag;
	}		

	@Override
	public boolean saveCourse(Course course) {
		boolean bflag = false;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			
			if(course.getId().equals(0))
				sql = "INSERT INTO course (course, description, degree) VALUES(?,?,?);";
			else
				sql = "UPDATE course SET course=?, description=?, degree=? WHERE id=?;";
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, course.getCourse());
			pstm.setString(2, course.getDescription());
			pstm.setString(3, course.getDegree());
			
			if(!course.getId().equals(0))
				pstm.setInt(4, course.getId());
			
			pstm.executeUpdate();
			bflag = true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return bflag;
	}
	
	@Override
	public void deleteCourse(Course course) {
		try {
			conn = DBConnection.getInstance().getConnection();
			pstm = conn.prepareStatement("DELETE FROM course WHERE course=?;");
			pstm.setString(1, course.getCourse());
			pstm.executeQuery();	
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/***   Student Grade   ***/
	
	@Override
	public ObservableList<Grade> gradeList(){
		Grade grade;
		ObservableList<Grade> listGrade = FXCollections.observableArrayList();
		
		try {
			conn = DBConnection.getInstance().getConnection();
            stm = conn.createStatement();
            rst = stm.executeQuery("SELECT * FROM grade;");			
			
            while(rst.next()) {
            	grade = new Grade();
            	grade.setId(rst.getInt("id"));
            	grade.setStudentNum(rst.getInt("studentNum"));
            	grade.setYear(rst.getString("year"));
            	grade.setCourse(rst.getString("course"));
            	grade.setFirst(rst.getDouble("first"));	
            	grade.setSecond(rst.getDouble("second"));
            	grade.setFinale(rst.getDouble("finale"));
            	listGrade.add(grade);
            }
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(rst != null) rst.close();
	        	if(stm != null) stm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return listGrade;				
	}
	
	@Override
	public boolean saveGrade(Grade grade) {
		boolean bflag = false;
		Double dFirst = 0.0;
		Double dSecond = 0.0;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			
			if(grade.getId().equals(0))
				sql = "INSERT INTO grade (studentNum, year, course, first, second, finale) VALUES(?,?,?,?,?,?);";
			else
				sql = "UPDATE grade SET studentNum=?, year=?, course=?, first=?, second=?, finale=? WHERE id=?;";
			
			dFirst = grade.getFirst();
			dSecond = grade.getSecond();
			
			if(dFirst == 0 || dSecond == 0)
				grade.setFinale(0.0);
			else
				grade.setFinale( (dFirst + dSecond) / 2 );
			
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, grade.getStudentNum());
			pstm.setString(2, grade.getYear());
			pstm.setString(3, grade.getCourse());
			pstm.setDouble(4, grade.getFirst());
			pstm.setDouble(5, grade.getSecond());
			pstm.setDouble(6, grade.getFinale());
			
			if(!grade.getId().equals(0))
				pstm.setInt(7, grade.getId());
			
			pstm.executeUpdate();
			bflag = true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
	        try {
	        	if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexion a BD !!! \n" + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return bflag;
	}

}
