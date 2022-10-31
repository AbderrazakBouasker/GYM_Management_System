package com.example.mini_projet_java;

import dao.Creatuserdao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private PasswordField creatuserpassword1;

    @FXML
    private TextField creatusersong;
    @FXML
    private Button creatlogin;


    private String varname;
    private String varpass;
    private String varq1;
    private String varq2;
    private Parent root;

    Scenechange scenechange=new Scenechange();
    Creatuserdao creatuserdao=new Creatuserdao();

    public creatusercontroller() throws SQLException {
    }

    @FXML
    void adduser(ActionEvent event) throws SQLException {

        if(creatusername.getText().isEmpty()||creatuserpassword.getText().isEmpty() ||creatuserpassword1.getText().isEmpty()||creatusermovie.getText().isEmpty()||creatusersong.getText().isEmpty()){
            creatlabel.setText("Fill all fields");
        } else if (creatuserpassword.getText()!=creatuserpassword1.getText()) {
            creatlabel.setText("Password and confirm password should be the same");
        } else {
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
        scenechange.changesceneto(root,event);


    }



}
