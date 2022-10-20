package com.example.mini_projet_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idnuminp.setText("");
        nameinp.setText("");
        lastnameinp.setText("");
        companyinp.setText("");
        reductioninp.setText("");
        //startdateinp;
        //enddateinp;
    }

    public void editmem(ActionEvent event){


    }
}
