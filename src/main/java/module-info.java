module tourism.admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    
    exports com.tourism;
    exports com.tourism.controllers;
    
    opens com.tourism to javafx.fxml;
    opens com.tourism.controllers to javafx.fxml;
}