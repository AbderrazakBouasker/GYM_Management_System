package com.example.mini_projet_java;


import dao.Logindao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class logincontroller {

    @FXML
    private Button loginbutton;

    @FXML
    private Button forgotbutton;

    @FXML
    public TextField passwordinput;

    @FXML
    public TextField usernameinput;

    @FXML
    public Label wronginfo;

    @FXML
    private TextField forgotmovie;

    @FXML
    private Label forgotpassword;

    @FXML
    private TextField forgotsong;

    @FXML
    private TextField forgotusername;

    @FXML
    private Button forgotback;

    @FXML
    private Button forgotsubmit;

    private Stage stage;
    private Scene scene;
    private Parent root;




    public void login(ActionEvent event) throws IOException, SQLException {
        Logindao logindao=new Logindao();
        logindao.dbgetuserinfo(usernameinput);

        if(usernameinput.getText().equals(logindao.getDbusername()) && passwordinput.getText().equals(logindao.getDbuserpassword())){
            root = FXMLLoader.load((getClass()).getResource("main-screen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (usernameinput.getText().isEmpty() || passwordinput.getText().isEmpty()) {
            wronginfo.setText("Please enter your info");
        }
        else {
            wronginfo.setText("Wrong password or username");
        }
    }

    public void forgotinfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("recover-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void recoverinfo(ActionEvent event) throws SQLException {
        Logindao logindao=new Logindao();
        logindao.dbgetuserinfo(forgotusername);
        if (forgotusername.getText().isEmpty() || forgotmovie.getText().isEmpty() || forgotsong.getText().isEmpty()) {
            forgotpassword.setText("Please answer the questions");
        }
        else if (forgotusername.getText().equals(logindao.getDbusername()) && forgotmovie.getText().equals(logindao.getDbusermovie()) && forgotsong.getText().equals(logindao.getDbusermusic())){
            forgotpassword.setText(logindao.getDbuserpassword());
        }  else{
            forgotpassword.setText("Wrong answers");
        }
    }

    public void gologinscene(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("login-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }






}
