package com.example.mini_projet_java;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class mainapp extends Application {

    Stage s;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("loading-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("gym manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Timer timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                System.out.println("sisalasalazesqldqsd");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            s=new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader(splashscreen.class.getResource("login-screen.fxml"));
                            Scene scene = new Scene(fxmlLoader.load());
                            s.setScene(scene);
                            s.show();
                            stage.hide();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


                // Hide this current window (if this is what you want)
                //((Node)(event.getSource())).getScene().getWindow().hide();

            }
        };

        timer.schedule(task, 2000);




      }









    public static void main(String[] args) {
        launch();
    }
}
