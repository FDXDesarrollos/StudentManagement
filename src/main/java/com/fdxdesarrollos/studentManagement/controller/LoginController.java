package com.fdxdesarrollos.studentManagement.controller;

import java.io.IOException;

import com.fdxdesarrollos.studentManagement.App;
import com.fdxdesarrollos.studentManagement.dao.LoginImp;
import com.fdxdesarrollos.studentManagement.model.Login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
	public static String user = "";
	private Alert alert = null;
	double x = 0, y = 0;
	
    @FXML
    private AnchorPane frmMain;
    
    @FXML
    private TextField txtUser;    

    @FXML
    private PasswordField txtPass;
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private Button btnClose;
    
    
    public void login() {
    	this.user = txtUser.getText().trim();
    	String pass = txtPass.getText().trim();
    	
    	if(!this.user.isEmpty() && !pass.isEmpty()) {
	    	if( new LoginImp().access(new Login(this.user, pass)) ) {
	    		btnLogin.getScene().getWindow().hide();
	    		
	    		try {
					Parent root = FXMLLoader.load(App.class.getResource("view/dashboard.fxml"));
					Stage stage = new Stage();
					
			        root.setOnMousePressed((MouseEvent event) -> {
			        	x = event.getSceneX();
			        	y = event.getSceneY();
			        });
			        
			        root.setOnMouseDragged((MouseEvent event) -> {
			        	stage.setX(event.getScreenX() -x);
			        	stage.setY(event.getScreenY() -y);
			        	stage.setOpacity(.8);
			        });
			        
			        root.setOnMouseReleased((MouseEvent event) -> {
			        	stage.setOpacity(1);
			        });					
					
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.setScene( new Scene(root) );
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	else {
	    		alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Student Management System");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Wrong Username/Password!");
	    		alert.showAndWait();
	    	}
    	}
    }
    
	public void logout() {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Student Management System");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to close the application?");
		
		if(alert.showAndWait().get().equals(ButtonType.OK)) {
			System.exit(0);
		}
	}    
}
