package com.example.mini_projet_java;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.w3c.dom.events.Event;


public class sceneController {

    @FXML
    private Button loginbutton;

    @FXML
    private TextField passwordinput;

    @FXML
    private TextField usernameinput;

    @FXML
    private Rectangle loadbar;

    @FXML
    private Text loadpercent;


    void loading(ActionEvent event){
        for (int i = 0; i < 1000000; i++) {
            int j=i/1000000;
            loadpercent.setText(j+"%");
            loadbar.setWidth(i*0.374);
        }


    }
    void login(ActionEvent event){

    }



}