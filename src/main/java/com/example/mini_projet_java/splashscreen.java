package com.example.mini_projet_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class splashscreen implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    /*timer();
    System.out.println("waa333333");*/
        Timer timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                System.out.println("sisalasalazesqldqsd");
                //root = FXMLLoader.load((getClass()).getResource("main-screen.fxml"));
                stage = (Stage)pane.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        };

        timer.schedule(task, 2000);






    }



    /*public void timer(){
        try{
            Thread.sleep(10000);

        }
        catch (Exception e){
        }
    }*/


}
