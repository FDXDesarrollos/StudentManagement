package com.fdxdesarrollos.studentManagement.util;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Util {
	private static double x = 0, y = 0;
	
	public static void dragWindow(Parent root, Stage stage) {
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
	}
}
