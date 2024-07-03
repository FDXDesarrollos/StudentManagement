package com.fdxdesarrollos.studentManagement.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.fdxdesarrollos.studentManagement.App;
import com.fdxdesarrollos.studentManagement.dao.DashboardImp;
import com.fdxdesarrollos.studentManagement.model.Course;
import com.fdxdesarrollos.studentManagement.model.Dashboard;
import com.fdxdesarrollos.studentManagement.model.Grade;
import com.fdxdesarrollos.studentManagement.model.Student;
import com.fdxdesarrollos.studentManagement.util.PrintReport;
import com.fdxdesarrollos.studentManagement.util.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class DashboardController implements Initializable {
	
    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button btnMinimize;
    
    @FXML
    private Button btnClose;
    
    @FXML
    private Label lblUserName;
    
	@FXML
	private Button btnHome;
	
	@FXML
	private Button btnAddStudents;
	
	@FXML
	private Button btnAvailableCouse;
	
	@FXML
	private Button btnStudentGrade;
	
	@FXML
	private Button btnLogOut;    
    
    /***   Home   ***/

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_total;

    @FXML
    private BarChart<?, ?> home_totalChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;    
	
	/***   Add Students   ***/
    @FXML
    private Button addStudents_btnSave;
    
    @FXML
    private Button addStudents_btnDelete; 
    
    @FXML
    private Button addStudents_btnClear;
    
    @FXML
    private Button addStudents_btnPrint;

    @FXML
    private Button addStudents_btnloadImage;

    @FXML
    private DatePicker addStudents_calBirthDate;

    @FXML
    private ComboBox<String> addStudents_cboYear;    
    
    @FXML
    private ComboBox<String> addStudents_cboCourse;

    @FXML
    private ComboBox<String> addStudents_cboGender;

    @FXML
    private ComboBox<String> addStudents_cboStatus;
    
    @FXML
    private TableColumn<Student, String> addStudents_col_id;    
    
    @FXML
    private TableColumn<Student, String> addStudents_col_student;
    
    @FXML
    private TableColumn<Student, String> addStudents_col_year;
    
    @FXML
    private TableColumn<Student, String> addStudents_col_course;    
    
    @FXML
    private TableColumn<Student, String> addStudents_col_firstName;    
    
    @FXML
    private TableColumn<Student, String> addStudents_col_lastName;
    
    @FXML
    private TableColumn<Student, String> addStudents_col_gender;    
    
    @FXML
    private TableColumn<Student, String> addStudents_col_birthDate;

    @FXML
    private TableColumn<Student, String> addStudents_col_status;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ImageView addStudents_imgStudent;

    @FXML
    private TextField addStudents_search;

    @FXML
    private TableView<Student> addStudents_tableView;

    @FXML
    private TextField addStudents_txtFirstName;

    @FXML
    private TextField addStudents_txtLastName;

    @FXML
    private TextField addStudents_txtStudent;
    
    /***   Available Course   ***/
    
    @FXML
    private Button availableCouse_btnAdd;

    @FXML
    private Button availableCouse_btnClear;

    @FXML
    private Button availableCouse_btnDelete;

    @FXML
    private Button availableCouse_btnSave;

    @FXML
    private TableColumn<Course, String> availableCouse_col_course;

    @FXML
    private TableColumn<Course, String> availableCouse_col_degree;

    @FXML
    private TableColumn<Course, String> availableCouse_col_description;

    @FXML
    private AnchorPane availableCouse_form;

    @FXML
    private TableView<Course> availableCouse_tableView;

    @FXML
    private TextField availableCouse_txtCourse;

    @FXML
    private TextField availableCouse_txtDegree;

    @FXML
    private TextField availableCouse_txtDescription;
    
    /***   Student Grade   ***/
    
    @FXML
    private Button studentGrade_btnClear;

    @FXML
    private Button studentGrade_btnSave;

    @FXML
    private TableColumn<Grade, Integer> studentGrade_col_id;    
    
    @FXML
    private TableColumn<Grade, Integer> studentGrade_col_studentNum;
    
    @FXML
    private TableColumn<Grade, String> studentGrade_col_year;

    @FXML
    private TableColumn<Grade, String> studentGrade_col_course;

    @FXML
    private TableColumn<Grade, Double> studentGrade_col_firstSem;

    @FXML
    private TableColumn<Grade, Double> studentGrade_col_secondSem;
    
    @FXML
    private TableColumn<Grade, Double> studentGrade_col_final;    

    @FXML
    private AnchorPane studentGrade_form;

    @FXML
    private Label studentGrade_lblCourse;

    @FXML
    private Label studentGrade_lblYear;

    @FXML
    private TableView<Grade> studentGrade_tableView;

    @FXML
    private TextField studentGrade_txtFinal;

    @FXML
    private TextField studentGrade_txtFirstSem;

    @FXML
    private TextField studentGrade_txtSearch;

    @FXML
    private TextField studentGrade_txtSecondSem;

    @FXML
    private TextField studentGrade_txtStudent;
    
	private Alert alert = null;
	private File fileImg = null;
	private String pathImg = "";
	private Integer indexId = 0;
	
	
	/*******************************************************************************************/
	/***                                        Main                                         ***/
	/*******************************************************************************************/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblUserName.setText(LoginController.user);
		btnHome.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d)");
		pathImg = System.getProperty("user.dir") + "/target/classes/com/fdxdesarrollos/studentManagement/img/";
		
		homeDisplayTotals();
		homeDisplayCharts();
		
		studentShowListData();
		studentsYearList();
		studentsGenderList();
		studentStatusList();
		studentCourseList();
		
		courseShowListData();
		
		gradeShowListData();
	}
	
	public void switchForm(ActionEvent event) {
		if(event.getSource() == btnHome) {
			home_form.setVisible(true);
			addStudents_form.setVisible(false);
			availableCouse_form.setVisible(false);
			studentGrade_form.setVisible(false);
			
			btnHome.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d)");
			btnAddStudents.setStyle("-fx-background-color:transparent");
			btnAvailableCouse.setStyle("-fx-background-color:transparent");
			btnStudentGrade.setStyle("-fx-background-color:transparent");
			
			homeDisplayTotals();
			homeDisplayCharts();
			
		}else if( event.getSource() == btnAddStudents ) {
			home_form.setVisible(false);
			addStudents_form.setVisible(true);
			availableCouse_form.setVisible(false);
			studentGrade_form.setVisible(false);
			
			btnHome.setStyle("-fx-background-color:transparent");
			btnAddStudents.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d)");
			btnAvailableCouse.setStyle("-fx-background-color:transparent");
			btnStudentGrade.setStyle("-fx-background-color:transparent");
			
			studentShowListData();
			studentCourseList();
			studentSearch();
			
		}else if ( event.getSource() == btnAvailableCouse ) {
			home_form.setVisible(false);
			addStudents_form.setVisible(false);
			availableCouse_form.setVisible(true);
			studentGrade_form.setVisible(false);
			
			btnHome.setStyle("-fx-background-color:transparent");
			btnAddStudents.setStyle("-fx-background-color:transparent");
			btnAvailableCouse.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d)");
			btnStudentGrade.setStyle("-fx-background-color:transparent");
			
			courseShowListData();
			
		} else if ( event.getSource() == btnStudentGrade ) {
			home_form.setVisible(false);
			addStudents_form.setVisible(false);
			availableCouse_form.setVisible(false);
			studentGrade_form.setVisible(true);
			
			btnHome.setStyle("-fx-background-color:transparent");
			btnAddStudents.setStyle("-fx-background-color:transparent");
			btnAvailableCouse.setStyle("-fx-background-color:transparent");
			btnStudentGrade.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d)");
			
			gradeSearch();
			
		} else if ( event.getSource() == btnLogOut ) {
			this.logout();
		}
	}
	
	public void minimize() {
		Stage stage = (Stage)mainForm.getScene().getWindow();
		stage.setIconified(true);
	}
	
	public void logout() {
		try {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Student Management System");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure to logout the application?");
			
			if(alert.showAndWait().get().equals(ButtonType.OK)) {
                //HIDE YOUR DASHBOARD FORM
				btnLogOut.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(App.class.getResource("view/login.fxml"));
                Stage stage = new Stage();
                Util.dragWindow(root, stage);
                stage.setScene( new Scene(root) );
                stage.show();				
	        } else {
	            return;
	        }			
        } catch (Exception e) {
            e.printStackTrace();
        }
	}	
	
	/*******************************************************************************************/
	/***                                      Home                                           ***/
	/*******************************************************************************************/
	
	public void homeDisplayTotals() {
		Dashboard obj = new DashboardImp().totalsDashboard();
		home_totalFemale.setText( obj.getTotalFemale() );
		home_totalMale.setText( obj.getTotalMale() );
		home_total.setText( obj.getTotalEnrolled() );
	}
	
	public void homeDisplayCharts() {
		List<Dashboard> datos = null;
		
		// Total Enrolled Chart
		home_totalChart.getData().clear();
		XYChart.Series chart1 = new XYChart.Series();	
		datos = new DashboardImp().chartsDashboard("");
		
		datos.forEach((obj) -> {
			chart1.getData().add(new XYChart.Data( obj.getDateReg(), Integer.parseInt(obj.getTotalEnrolled()) ));
		});
		
		home_totalChart.getData().add(chart1);
		
		
		// Enrolled Female Chart
		home_totalFemaleChart.getData().clear();
		XYChart.Series chart2 = new XYChart.Series();
		datos = new DashboardImp().chartsDashboard("Female");

		datos.forEach((obj) -> {
			chart2.getData().add(new XYChart.Data( obj.getDateReg(), Integer.parseInt(obj.getTotalEnrolled()) ));
		});		
		
		home_totalFemaleChart.getData().add(chart2);
		
		
		// Enrolled Male Chart
		home_totalMaleChart.getData().clear();
		XYChart.Series chart3 = new XYChart.Series();
		datos = new DashboardImp().chartsDashboard("Male");
		
		datos.forEach((obj) -> {
			chart3.getData().add(new XYChart.Data( obj.getDateReg(), Integer.parseInt(obj.getTotalEnrolled()) ));
		});		
		
		home_totalMaleChart.getData().add(chart3);
	}
	
	/*******************************************************************************************/
	/***                                   Add Students                                      ***/
	/*******************************************************************************************/
	private String[] genderList = {"Male", "Female"};
	private String[] yearsList = {"First", "Second", "Third", "Fourth", "Fifthy", "Sixthy"};
	private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};
	private ObservableList<Student> listStudents;
	private ObservableList<Course> addCourseListData;
	
	public ObservableList<Student> studentList(){
		listStudents = new DashboardImp().studentList();
		return listStudents;
	}	
	
	public void studentsYearList() {
		List<String> yearsData = new ArrayList<>();
		Arrays.stream(yearsList).forEach(year -> yearsData.add(year));
		ObservableList list = FXCollections.observableArrayList(yearsData);
		addStudents_cboYear.setItems(list);
	}
	
	public void studentsGenderList() {
		List<String> genderData = new ArrayList<>();
		Arrays.stream(genderList).forEach(gender -> genderData.add(gender));
		ObservableList list = FXCollections.observableArrayList(genderData);
		addStudents_cboGender.setItems(list);
	}
	
	public void studentStatusList() {
		List<String> statusData = new ArrayList<>();
		Arrays.stream(statusList).forEach(status -> statusData.add(status));
		ObservableList list = FXCollections.observableArrayList(statusData);
		addStudents_cboStatus.setItems(list);
	}
	
	public void studentCourseList() {
		List<String> courseData = new ArrayList<>();
		ObservableList<Course> listCourses = new DashboardImp().courseList();
		
		for(int i=0; i<listCourses.size(); i++) {
			courseData.add( listCourses.get(i).getCourse() );
		}
		
		ObservableList list = FXCollections.observableArrayList(courseData);
		addStudents_cboCourse.setItems(list);
	}
	
	public void studentShowListData(){
		addStudents_col_id.setCellValueFactory( new PropertyValueFactory<>("id") );
		addStudents_col_student.setCellValueFactory( new PropertyValueFactory<>("studentNum") );
		addStudents_col_year.setCellValueFactory( new PropertyValueFactory<>("year") );
		addStudents_col_course.setCellValueFactory( new PropertyValueFactory<>("course") );
		addStudents_col_firstName.setCellValueFactory( new PropertyValueFactory<>("firstName") );
		addStudents_col_lastName.setCellValueFactory( new PropertyValueFactory<>("lastName") );
		addStudents_col_gender.setCellValueFactory( new PropertyValueFactory<>("gender") );
		addStudents_col_birthDate.setCellValueFactory( new PropertyValueFactory<>("birth") );
	    addStudents_col_status.setCellValueFactory( new PropertyValueFactory<>("status") );
	    addStudents_tableView.setItems(studentList());
	}
	
	public void studentSelect() throws FileNotFoundException {
		Student student = addStudents_tableView.getSelectionModel().getSelectedItem();
		int num = addStudents_tableView.getSelectionModel().getSelectedIndex();
		
		if((num -1) < -1) { return; }
		
		indexId = student.getId();
		addStudents_txtStudent.setText(String.valueOf(student.getStudentNum()));
		addStudents_cboYear.setValue(student.getYear());
		addStudents_cboCourse.setValue(student.getCourse());
		addStudents_txtFirstName.setText(student.getFirstName());
		addStudents_txtLastName.setText(student.getLastName());
		addStudents_cboGender.setValue(student.getGender());
		addStudents_cboStatus.setValue(student.getStatus());
		addStudents_calBirthDate.setValue( LocalDate.parse(String.valueOf(student.getBirth())) );
		addStudents_imgStudent.setImage(null);
		
		if(student.getImage() != null && !student.getImage().equals("")) {
			//	App.class.getResource("img/" + student.getImage()).getPath()
			fileImg = new File( pathImg + student.getImage());
			
			if(fileImg.exists()) {
				InputStream inputStream = App.class.getResourceAsStream("img/" + student.getImage());
				Image image = new Image(inputStream, 118, 148, false, true);
				addStudents_imgStudent.setImage(image);
			}
			else { fileImg = null; }
		}
	}
	
	public void studentAddImage() {
		FileChooser open = new FileChooser();
		open.setTitle("Open Image File");
		open.getExtensionFilters().add(new ExtensionFilter("Image File", "*.jpg", "*.png"));
		
		fileImg = open.showOpenDialog(mainForm.getScene().getWindow());
		
		if(fileImg != null) {
			Image image = new Image(fileImg.toURI().toString(), 120, 149, false, true);
			addStudents_imgStudent.setImage(image);
		}
	}
	
	public void studentSave() {
	    if(addStudents_txtStudent.getText().isEmpty()
		    || addStudents_cboYear.getSelectionModel().getSelectedItem() == null
		    || addStudents_cboCourse.getSelectionModel().getSelectedItem() == null
		    || addStudents_txtFirstName.getText().isEmpty()
		    || addStudents_txtLastName.getText().isEmpty()
		    || addStudents_cboGender.getSelectionModel().getSelectedItem() == null
		    || addStudents_calBirthDate.getValue() == null
		    || addStudents_cboStatus.getSelectionModel().getSelectedItem() == null) {
	    	
		   alert = new Alert(AlertType.ERROR);
		   alert.setTitle("Student Management System");
		   alert.setHeaderText(null);
		   alert.setContentText("Please fill all blank fields");
		   alert.showAndWait();
	    }
	    else {
	    	Student student = new Student();
	    	DashboardImp dao = new DashboardImp();
	    	
	    	student.setId(indexId);
		    student.setStudentNum(Integer.valueOf(addStudents_txtStudent.getText()));
			student.setYear((String) addStudents_cboYear.getSelectionModel().getSelectedItem());
			student.setCourse((String) addStudents_cboCourse.getSelectionModel().getSelectedItem());
			student.setFirstName(addStudents_txtFirstName.getText());
			student.setLastName(addStudents_txtLastName.getText());
			student.setGender((String) addStudents_cboGender.getSelectionModel().getSelectedItem());
			student.setBirth( Date.from( addStudents_calBirthDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant() ));
			student.setStatus((String) addStudents_cboStatus.getSelectionModel().getSelectedItem());	    	
			
			if(fileImg != null) 
				student.setImage(fileImg.getName());
			
			if(student.getId().equals(0)) {
				if(dao.existStudent(student)) {
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("Student Management System");
					alert.setHeaderText(null);
					alert.setContentText("There is already a student with that number");
					alert.showAndWait();
					return;
				}
			}
				
			if(dao.saveStudent(student)) {				
				if(fileImg != null && !fileImg.getName().equals("")) {
					//	new File(App.class.getResource("img/" + fileImg.getName()).toString()).toPath()
					try {
						Files.copy((Path) fileImg.toPath(),
								   (Path) new File(pathImg + fileImg.getName()).toPath(), 
								   StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(student.getId().equals(0)) {
					Grade grade = new Grade();
					grade.setStudentNum( student.getStudentNum() );
					grade.setYear( student.getYear() );
					grade.setCourse( student.getCourse() );
					grade.setFirst( Double.valueOf("0.0") );
					grade.setSecond( Double.valueOf("0.0") );
					grade.setFinale( Double.valueOf("0.0") );
					dao.saveGrade(grade);
				}
				
				studentClear();
				
			    alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Student Management System");
			    alert.setHeaderText(null);
			    alert.setContentText("Information recorded successfully");
			    alert.showAndWait();
			}
			else {
			    alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Student Management System");
			    alert.setHeaderText(null);
			    alert.setContentText("Error to recorded");
			    alert.showAndWait();
			}
	    }
	}
	
	public void studentDelete() {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Student Management System");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete?");
		
		if(alert.showAndWait().get().equals(ButtonType.OK)) {
			Student student = addStudents_tableView.getSelectionModel().getSelectedItem();
			int num = addStudents_tableView.getSelectionModel().getSelectedIndex();
			
			if((num -1) < -1)
				return;
			else {
				new DashboardImp().deleteStudent(student);
				studentClear();
				
			    alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Student Management System");
			    alert.setHeaderText(null);
			    alert.setContentText("Information deleted");
			    alert.showAndWait();
			}
		}		
	}	
	
	public void studentClear() {
		indexId = 0;
		fileImg = null;
		addStudents_txtStudent.setText("");
		addStudents_cboYear.getSelectionModel().clearSelection();	//	setValue(null);
		addStudents_cboCourse.getSelectionModel().clearSelection();	//	setValue(null);
		addStudents_txtFirstName.setText("");
		addStudents_txtLastName.setText("");
		addStudents_cboGender.getSelectionModel().clearSelection();	//	setValue(null);
		addStudents_calBirthDate.setValue(null);
		addStudents_cboStatus.getSelectionModel().clearSelection();	//	setValue(null);
		addStudents_imgStudent.setImage(null);
		studentShowListData();
	}
	
	public void studentSearch() {
		FilteredList<Student>filter = new FilteredList<>(listStudents, e -> true);
		
		addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicate -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String searchKey = newValue.toLowerCase();
				
				if(predicate.getStudentNum().toString().contains(searchKey)) {
					return true;
				} else if(predicate.getYear().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getCourse().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getFirstName().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getLastName().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getGender().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getBirth().toString().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getStatus().toString().toLowerCase().contains(searchKey)) {
					return true;
				} else { return false; }
			});
		});
		
		SortedList<Student> sortList = new SortedList<>(filter);
		sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
		addStudents_tableView.setItems(sortList);
	}
	
	public void studentPrintList() {
        Map param = new HashMap();
        param.put("pTitulo", "Student Management System");
        param.put("pLogo", App.class.getResource("img/graduation-cap.png").getPath());
        		
		new PrintReport("students.jrxml", param).showReport();
	}
	
	/*******************************************************************************************/
	/***                                 Available Couse                                     ***/
	/*******************************************************************************************/
	public ObservableList<Course> courseList(){
		ObservableList<Course> listCourses = new DashboardImp().courseList();
		return listCourses;
	}	

	public void courseShowListData() {
		addCourseListData = courseList();
		availableCouse_col_course.setCellValueFactory( new PropertyValueFactory<>("id") );
	    availableCouse_col_course.setCellValueFactory( new PropertyValueFactory<>("course") );
	    availableCouse_col_degree.setCellValueFactory( new PropertyValueFactory<>("description") );
	    availableCouse_col_description.setCellValueFactory( new PropertyValueFactory<>("degree") );
	    availableCouse_tableView.setItems(addCourseListData);
	}
	
	public void courseSelect() {
		Course course = availableCouse_tableView.getSelectionModel().getSelectedItem();
		int num = availableCouse_tableView.getSelectionModel().getSelectedIndex();
		
		if((num -1) < -1) { return; }
		
		indexId = course.getId();
		availableCouse_txtCourse.setText(String.valueOf(course.getCourse()));
		availableCouse_txtDescription.setText(course.getDescription());
		availableCouse_txtDegree.setText(course.getDegree());
	}
	
	public void availableCouseSave() {
		if(availableCouse_txtCourse.getText().isEmpty() 
			|| availableCouse_txtDescription.getText().isEmpty() 
			|| availableCouse_txtDegree.getText().isEmpty()) {
		   
		   alert = new Alert(AlertType.ERROR);
		   alert.setTitle("Student Management System");
		   alert.setHeaderText(null);
		   alert.setContentText("Please fill all blank fields");
		   alert.showAndWait();
		}
		else {
			Course course = new Course();
			DashboardImp dao = new DashboardImp();
			
			course.setId( indexId );
			course.setCourse( availableCouse_txtCourse.getText().toUpperCase().trim() );
			course.setDescription( availableCouse_txtDescription.getText().toUpperCase().trim() );
			course.setDegree( availableCouse_txtDegree.getText().toUpperCase().trim() );
			
			if(!dao.existCourse(course)) {
				if(dao.saveCourse(course)) {
					availableCouseClear();
					
				    alert = new Alert(AlertType.INFORMATION);
				    alert.setTitle("Student Management System");
				    alert.setHeaderText(null);
				    alert.setContentText("Information recorded successfully");
				    alert.showAndWait();
				}
				else {
				    alert = new Alert(AlertType.ERROR);
				    alert.setTitle("Student Management System");
				    alert.setHeaderText(null);
				    alert.setContentText("Error to recorded");
				    alert.showAndWait();
				}
			}
			else {
				alert = new Alert(AlertType.WARNING);
				alert.setTitle("Student Management System");
				alert.setHeaderText(null);
				alert.setContentText("There is already a course with that name");
				alert.showAndWait();
			}
		}
	}
	
	public void availableCouseDelete() {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Student Management System");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete?");
		
		if(alert.showAndWait().get().equals(ButtonType.OK)) {
			Course course = availableCouse_tableView.getSelectionModel().getSelectedItem();
			int num = availableCouse_tableView.getSelectionModel().getSelectedIndex();
			
			if((num -1) < -1)
				return;
			else {
				new DashboardImp().deleteCourse(course);
				availableCouseClear();
				
			    alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Student Management System");
			    alert.setHeaderText(null);
			    alert.setContentText("Information deleted successfully");
			    alert.showAndWait();
			}
		}		
	}	
	
	public void availableCouseClear() {
		indexId = 0;
		availableCouse_txtCourse.setText("");
		availableCouse_txtDescription.setText("");
		availableCouse_txtDegree.setText("");
		courseShowListData();
	}
	
	/*******************************************************************************************/
	/***                                   Student Grade                                     ***/
	/*******************************************************************************************/
	private ObservableList<Grade> gradeList;
	
	public ObservableList<Grade> gradeList(){
		ObservableList<Grade> listGrades = new DashboardImp().gradeList();
		return listGrades;
	}

	public void gradeShowListData() {
		gradeList = gradeList();	 
		studentGrade_col_id.setCellValueFactory( new PropertyValueFactory<>("id") );
	    studentGrade_col_studentNum.setCellValueFactory( new PropertyValueFactory<>("studentNum") );
	    studentGrade_col_year.setCellValueFactory( new PropertyValueFactory<>("year") );
	    studentGrade_col_course.setCellValueFactory( new PropertyValueFactory<>("course") );
	    studentGrade_col_firstSem.setCellValueFactory( new PropertyValueFactory<>("first") );
	    studentGrade_col_secondSem.setCellValueFactory( new PropertyValueFactory<>("second") );
	    studentGrade_col_final.setCellValueFactory( new PropertyValueFactory<>("finale") );
	    studentGrade_tableView.setItems(gradeList);
	}
	
	public void gradeSelect() throws FileNotFoundException {
		Grade grade = studentGrade_tableView.getSelectionModel().getSelectedItem();
		int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();
		
		if((num -1) < -1) { return; }
		
		indexId = grade.getId();
		studentGrade_txtStudent.setText(String.valueOf(grade.getStudentNum()));
		studentGrade_lblYear.setText(grade.getYear());
		studentGrade_lblCourse.setText(grade.getCourse());
		studentGrade_txtFirstSem.setText(String.valueOf(grade.getFirst()));
		studentGrade_txtSecondSem.setText(String.valueOf(grade.getSecond()));
		studentGrade_txtFinal.setText(String.valueOf(grade.getFinale()));
	}
	
	public void gradeSave() {
		if(studentGrade_txtFirstSem.getText().isEmpty() 
			|| studentGrade_txtSecondSem.getText().isEmpty() 
			|| studentGrade_txtFinal.getText().isEmpty()) {
		   
		   alert = new Alert(AlertType.ERROR);
		   alert.setTitle("Student Management System");
		   alert.setHeaderText(null);
		   alert.setContentText("Please fill all grades blank fields");
		   alert.showAndWait();
		}
		else {
			Grade grade = new Grade();
			DashboardImp dao = new DashboardImp();
			
			grade.setId( indexId );
			grade.setStudentNum(Integer.valueOf(studentGrade_txtStudent.getText()));
			grade.setYear(studentGrade_lblYear.getText());
			grade.setCourse(studentGrade_lblCourse.getText());
			grade.setFirst(Double.valueOf(studentGrade_txtFirstSem.getText()));
			grade.setSecond(Double.valueOf(studentGrade_txtSecondSem.getText()));
			grade.setFinale(Double.valueOf(studentGrade_txtFinal.getText()));
			
			if(dao.saveGrade(grade)) {
				gradeClear();
				
			    alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Student Management System");
			    alert.setHeaderText(null);
			    alert.setContentText("Successfully Updated");
			    alert.showAndWait();
			}
			else {
			    alert = new Alert(AlertType.ERROR);
			    alert.setTitle("Student Management System");
			    alert.setHeaderText(null);
			    alert.setContentText("Error to recorded");
			    alert.showAndWait();
			}
		}
	}	
	
	public void gradeClear() {
		indexId = 0;
		studentGrade_txtStudent.setText("");
		studentGrade_lblYear.setText("");
		studentGrade_lblCourse.setText("");
		studentGrade_txtFirstSem.setText("");
		studentGrade_txtSecondSem.setText("");
		studentGrade_txtFinal.setText("");
		gradeShowListData();
	}
	
	public void gradeSearch() {
		FilteredList<Grade>filter = new FilteredList<>(gradeList, e -> true);
		
		studentGrade_txtSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicate -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String searchKey = newValue.toLowerCase();
				
				if(predicate.getStudentNum().toString().contains(searchKey)) {
					return true;
				} else if(predicate.getYear().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getCourse().toLowerCase().contains(searchKey)) {
					return true;
				} else if(predicate.getFirst().toString().contains(searchKey)) {
					return true;
				} else if(predicate.getSecond().toString().contains(searchKey)) {
					return true;
				} else if(predicate.getFinale().toString().contains(searchKey)) {
					return true;
				} else { return false; }
			});
		});
		
		SortedList<Grade> sortList = new SortedList<>(filter);
		sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
		studentGrade_tableView.setItems(sortList);
	}	
}



