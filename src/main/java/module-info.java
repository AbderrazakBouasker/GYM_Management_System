module com.example.mini_projet_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mini_projet_java to javafx.fxml;
    exports com.example.mini_projet_java;
}