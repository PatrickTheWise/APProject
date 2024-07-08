module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.mail;
    requires mysql.connector.j;
    requires commons.math3;
    requires java.scripting;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}