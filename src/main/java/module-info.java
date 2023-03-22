module com.mainframe.todolistfrontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.mainframe.todolistfrontend to javafx.fxml;
    exports com.mainframe.todolistfrontend;
}