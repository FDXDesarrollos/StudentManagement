module com.fdxdesarrollos.studentManagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.graphics;
	requires jasperreports;
	requires java.desktop;

    opens com.fdxdesarrollos.studentManagement to javafx.fxml;
    exports com.fdxdesarrollos.studentManagement;
    
    opens com.fdxdesarrollos.studentManagement.controller to javafx.fxml;
    exports com.fdxdesarrollos.studentManagement.controller;
    
    exports com.fdxdesarrollos.studentManagement.model;
}
