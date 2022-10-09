package com.example.mini_projet_java;


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

    private String dbusername;
    private String dbuserpassword;
    private String dbusermovie;
    private String dbusermusic;

    String url="jdbc:mysql://localhost:3306/miniprojdb";
    String user="root";
    String password="24506544";


    public void dbgetuserinfo(TextField inputusername) throws SQLException {
        //String username=usernameinput.getText().toString();
        String query="select * from logininfo where username=\""+inputusername.getText().toString()+ "\"";
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);

        while (resultSet.next()) {
            dbuserpassword=resultSet.getString("password");
            dbusername=resultSet.getString("username");
            dbusermovie=resultSet.getString("qmovie");
            dbusermusic=resultSet.getString("qmusic");

        }
    }






    public void login(ActionEvent event) throws IOException, SQLException {
        dbgetuserinfo(usernameinput);
        if(usernameinput.getText().toString().equals(dbusername) && passwordinput.getText().toString().equals(dbuserpassword)){
            root = FXMLLoader.load((getClass()).getResource("main-screen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (usernameinput.getText().isEmpty() && passwordinput.getText().isEmpty()) {
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

    public void recoverinfo(ActionEvent event) throws IOException, SQLException {
        dbgetuserinfo(forgotusername);
        if (forgotusername.getText().toString().equals(dbusername) && forgotmovie.getText().toString().equals(dbusermovie) && forgotsong.getText().toString().equals(dbusermusic)){
            forgotpassword.setText(dbuserpassword);
        } else if (forgotusername.getText().isEmpty() && forgotmovie.getText().isEmpty() && forgotsong.getText().isEmpty()) {
            forgotpassword.setText("Please answer the questions");
        } else{
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

    /*public void insert(ActionEvent event) throws IOException,SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        statement.execute("insert into logininfo values (default,\"mohsen\",147852369,\"avenger\",\"avenger2\");");
    }*/




}
