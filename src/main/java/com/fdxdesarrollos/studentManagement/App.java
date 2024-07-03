package com.fdxdesarrollos.studentManagement;

import java.io.IOException;

import com.fdxdesarrollos.studentManagement.util.Util;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application {
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
    	Util.dragWindow(root, stage);
        stage.setScene( new Scene(root) );
        stage.show();
        
		stage.setOnCloseRequest(event -> {
			event.consume();
			logout(stage);
		});        
    }
    
	public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Student Management System");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to close the application?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("You successfully logged out!");
			stage.close();
		}
	}

    public static void main(String[] args) {
        launch();
    }
}


//private static Scene scene;
//scene = new Scene(loadFXML("login"), 640, 480);
/*private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
}*/