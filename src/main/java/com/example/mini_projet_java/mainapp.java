package com.example.mini_projet_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mainapp extends Application {

    String url="jdbc:mysql://localhost:3306/miniprojdb";
    String user="root";
    String password="24506544";



    /*{
        try {
            Connection myconn = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("gym manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
