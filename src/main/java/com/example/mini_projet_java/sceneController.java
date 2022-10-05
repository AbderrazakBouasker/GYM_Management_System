package com.example.mini_projet_java;
import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;


public class sceneController {

    @FXML
    private Button loginbutton;

    @FXML
    public TextField passwordinput;

    @FXML
    public TextField usernameinput;

    @FXML
    private ProgressBar loadbar;

    @FXML
    private Text loadpercent;


    void loading(ActionEvent event){
        for (int i = 0; i < 1000000; i++) {
            int j=i/1000000;
            loadpercent.setText(j+"%");
            loadbar.setProgress(i);
        }


    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    /*void switchscene(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("main-screen.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/
    void login(ActionEvent event){
    if ((usernameinput.getText()=="kala") && (passwordinput.getText()=="kala") ){
        FXMLLoader fxmlLoader = new FXMLLoader(mainapp.class.getResource("main-screen.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    else{
        System.out.println("non");
    }
    }



}