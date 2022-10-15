package com.example.mini_projet_java;

import dao.Creatuserdao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class creatusercontroller {

    @FXML
    private Label creatlabel;

    @FXML
    private Button creatsubmitbutton;

    @FXML
    private TextField creatusermovie;

    @FXML
    private TextField creatusername;

    @FXML
    private PasswordField creatuserpassword;

    @FXML
    private TextField creatusersong;
    @FXML
    private Button creatlogin;


    private String varname;
    private String varpass;
    private String varq1;
    private String varq2;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void adduser(ActionEvent event) throws SQLException {
        Creatuserdao creatuserdao=new Creatuserdao();
        if(creatusername.getText().isEmpty()||creatuserpassword.getText().isEmpty()||creatusermovie.getText().isEmpty()||creatusersong.getText().isEmpty()){

            creatlabel.setText("Fill all fields");
        }
        else {
            varname=creatusername.getText();
            varpass=creatuserpassword.getText();
            varq1=creatusermovie.getText();
            varq2=creatusersong.getText();
            creatuserdao.add(varname,varpass,varq1,varq2);
            creatsubmitbutton.setDisable(true);
            creatlabel.setText("");
        }
    }

    public void switchlogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("login-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }



}
