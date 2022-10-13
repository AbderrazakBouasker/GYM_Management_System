package com.example.mini_projet_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;


public class mainapp extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("login-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("gym manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }









    public static void main(String[] args) {
        launch();
    }
}
