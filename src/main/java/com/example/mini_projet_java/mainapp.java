package com.example.mini_projet_java;

import dao.Logindao;
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
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("loading-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("gym manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Logindao logindao =new Logindao();

        Timer timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run()  {

                try {
                    if(logindao.dbcheckexist()){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    s=new Stage();
                                    FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("creatuser-screen.fxml"));
                                    Scene scene = new Scene(fxmlLoader.load());
                                    s.setScene(scene);
                                    s.show();
                                    stage.hide();

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                    }
                    else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    s=new Stage();
                                    FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("login-screen.fxml"));
                                    Scene scene = new Scene(fxmlLoader.load());
                                    s.setScene(scene);
                                    s.show();
                                    stage.hide();

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        timer.schedule(task, 2000);




      }









    public static void main(String[] args) {
        launch();
    }
}
