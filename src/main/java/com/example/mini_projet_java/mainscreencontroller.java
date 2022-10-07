package com.example.mini_projet_java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;

public class mainscreencontroller {

    @FXML
    private TableView<?> dashboardexpirationtable;

    @FXML
    private TableColumn<?, ?> dashboardlistdays;

    @FXML
    private TableColumn<?, ?> dashboardlistlastname;

    @FXML
    private TableColumn<?, ?> dashboardlistname;

    @FXML
    private Label dashboardmemberslabel;

    @FXML
    private Label dashboardrevenuelabel;

    @FXML
    private Button mainaddbutton;

    @FXML
    private Button maindashboardbutton;

    @FXML
    private Button mainlistbutton;

    @FXML
    private Button mainlogoutbutton;

    private String dbmidnumber;
    private String dbmname;
    private String dbmlastname;
    private String dbmcompanyname;
    private String dbmpaymentreduction;
    private String dbmstartdate;
    private String dbmenddate;

    String url="jdbc:mysql://localhost:3306/miniprojdb";
    String user="root";
    String password="24506544";

    public void dbgetmemberscount() throws SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from members");

        while (resultSet.next()) {
            dbmidnumber=resultSet.getString("idnumber");
            dbmname=resultSet.getString("name");
            dbmlastname=resultSet.getString("lastname");
            dbmcompanyname=resultSet.getString("companyname");
            dbmpaymentreduction=resultSet.getString("paymentreduction");
            dbmstartdate=resultSet.getString("startdate");
            dbmenddate=resultSet.getString("enddate");

        }
    }





}