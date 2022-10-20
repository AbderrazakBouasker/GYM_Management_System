package com.example.mini_projet_java;

import dao.Mainscreendao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class editmembercontroller implements Initializable {
    @FXML
    private TextField companyinp;

    @FXML
    private Button editbtn;

    @FXML
    private Label editclosestage;

    @FXML
    private DatePicker enddateinp;

    @FXML
    private TextField idnuminp;

    @FXML
    private TextField lastnameinp;

    @FXML
    private TextField nameinp;

    @FXML
    private TextField reductioninp;

    @FXML
    private DatePicker startdateinp;
    @FXML
    private Label editconflabel;
    private String tempid;

    Mainscreendao mainscreendao=new Mainscreendao();

    public editmembercontroller() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void editmem(ActionEvent event) throws SQLException {
        if(idnuminp.getText().isEmpty()||nameinp.getText().isEmpty()||lastnameinp.getText().isEmpty()||companyinp.getText().isEmpty()||reductioninp.getText().isEmpty()||startdateinp.getValue().equals(null)||enddateinp.getValue().equals(null)){
            editconflabel.setText("Fill all fields");
        }
        else {
        LocalDate startdate=startdateinp.getValue();
        String varstartdate=startdate.toString();
        LocalDate enddate=enddateinp.getValue();
        String varenddate=enddate.toString();
        mainscreendao.editmeminfo(tempid,idnuminp.getText(),nameinp.getText(),lastnameinp.getText(),companyinp.getText(),reductioninp.getText(),varstartdate,varenddate);
        editconflabel.setText("Edited successfully");
    }}
    public void setall(String id,String idn,String name,String lastname,String company,String reduction,String start,String end){
        tempid=id;
        idnuminp.setText(idn);
        nameinp.setText(name);
        lastnameinp.setText(lastname);
        companyinp.setText(company);
        reductioninp.setText(reduction);
        LocalDate startdate=LocalDate.parse(start);
        startdateinp.setValue(startdate);
        LocalDate enddate=LocalDate.parse(end);
        enddateinp.setValue(enddate);
    }

}
