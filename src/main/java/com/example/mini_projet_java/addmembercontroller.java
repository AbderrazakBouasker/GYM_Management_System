package com.example.mini_projet_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class addmembercontroller {
    @FXML
    private TextField companyinput;

    @FXML
    private DatePicker enddateinput;

    @FXML
    private TextField idnumberinput;

    @FXML
    private TextField lastnameinput;

    @FXML
    private TextField nameinput;

    @FXML
    private TextField reductioninput;

    @FXML
    private Button returnbutton;

    @FXML
    private DatePicker startdateinput;

    @FXML
    private Button submitbutton;

    @FXML
    private Label outlabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    //String numberid;




    String url="jdbc:mysql://localhost:3306/miniprojdb";
    String user="root";
    String password="24506544";


    public void returntomain(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("main-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /*public void dbgetidnumber(TextField idnumber) throws SQLException {
        String query="select idnumber from members where idnumber=\""+idnumber.getText().toString()+ "\"";
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery(query);

        while (resultSet.next()) {
            numberid=resultSet.getString("idnumber");

        }



    }*/


    public void insert(ActionEvent event) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();

        LocalDate startdate=startdateinput.getValue();
        LocalDate enddate=enddateinput.getValue();
        String varcompany=companyinput.getText();
        String varpayment=reductioninput.getText();
        String varname=nameinput.getText();
        String varlastname=lastnameinput.getText();
        String varidnumber=idnumberinput.getText();
        //dbgetidnumber(idnumberinput);

        if (nameinput.getText().isEmpty() && lastnameinput.getText().isEmpty() && idnumberinput.getText().isEmpty() && startdate==null && enddate==null){
            outlabel.setText("Fill all necessary fields");
        /*} else if (numberid.equals(idnumberinput)) {
            outlabel.setText("user already exist");*/
        } else{
            String varstartdate=startdate.toString();
            String varenddate=enddate.toString();
            if(companyinput.getText().isEmpty()){
            varcompany=null;
            }
            if(reductioninput.getText().isEmpty()){
                varpayment=null;
            }
            statement.execute("insert into members values ("+varidnumber+",\""+varname+"\",\""+varlastname+"\",\""+varcompany+"\","+varpayment+",\""+varstartdate+"\",\""+varenddate+"\")");
            outlabel.setText("");
        }

    }



}
