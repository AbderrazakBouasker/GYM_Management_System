package com.example.mini_projet_java;

import dao.Logindao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class mainapp extends Application {
    @FXML
    private ProgressBar progressbar;
    @FXML
    private Label loadingmessage;

    @FXML
    private Label loadingvalue;
    Stage s;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("loading-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("gym manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        //Splashscreencontroller splashscreencontroller=new Splashscreencontroller();
        //splashscreencontroller.start(stage);
        Logindao logindao =new Logindao();
        //mainapp ma=new mainapp();

        /*for (int i = 0; i < 100; i++) {
            Thread.sleep(20);
            System.out.println(i);
            loadingvalue.setText(i+"%");
            if (i==20){
                loadingmessage.setText("Turning modules on...");
            }
            if (i==40){
                loadingmessage.setText("Loading modules...");
            }
            if (i==60){
                loadingmessage.setText("Connecting to Database...");
            }
            if (i==80){
                loadingmessage.setText("Launching...");
            }
            progressbar.setProgress(i);
        }*/
        /*if (logindao.dbcheckexist()){
            s=new Stage();
            fxmlLoader = new FXMLLoader(mainapp.class.getResource("creatuser-screen.fxml"));
            scene = new Scene(fxmlLoader.load());
            s.setTitle("gym manager");
            s.setResizable(false);
            s.setScene(scene);
            s.show();
            stage.hide();
        }else {
            s=new Stage();
            fxmlLoader = new FXMLLoader(mainapp.class.getResource("login-screen.fxml"));
            scene = new Scene(fxmlLoader.load());
            s.setTitle("gym manager");
            s.setResizable(false);
            s.setScene(scene);
            s.show();
            stage.hide();
        }*/

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
                                    s.setTitle("gym manager");
                                    s.setResizable(false);
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
                                    s.setTitle("gym manager");
                                    s.setResizable(false);
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

        timer.schedule(task, 3000);




      }









    public static void main(String[] args) {
        launch();
    }
}
