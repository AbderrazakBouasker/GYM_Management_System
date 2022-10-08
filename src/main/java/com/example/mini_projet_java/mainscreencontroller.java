package com.example.mini_projet_java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class mainscreencontroller {

    @FXML
    private TableView<dashtableimp> dashboardexpirationtable;

    @FXML
    private TableColumn<dashtableimp, Integer> dashboardlistdays;

    @FXML
    private TableColumn<dashtableimp, String> dashboardlistlastname;

    @FXML
    private TableColumn<dashtableimp, String> dashboardlistname;

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

    @FXML
    private Pane dashboardpanel;

    private String dbmidnumber;
    private String dbmname;
    private String dbmlastname;
    private String dbmcompanyname;
    private String dbmpaymentreduction;
    private String dbmstartdate;
    private String dbmenddate;
    private String membersnumber;

    private Stage stage;
    private Scene scene;
    private Parent root;

    String url="jdbc:mysql://localhost:3306/miniprojdb";
    String user="root";
    String password="24506544";

    ObservableList<dashtableimp> obser = FXCollections.observableArrayList();
    @Override

    public void initialize(URL ur, ResourceBundle resourceBundle) throws SQLException {
        dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));

        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from members");

        while (resultSet.next()) {
            obser.add(new dashtableimp(resultSet.getString("name"),resultSet.getString("lastname")
                    ,resultSet.getString("enddate")));


        }
        dashboardexpirationtable.setItems(obser);

    }

    public void dbgetmembersinfo() throws SQLException {
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


    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("login-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshdashboard(ActionEvent event) throws SQLException,IOException {
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select count(idnumber) from members");

        while (resultSet.next()) {
            membersnumber=resultSet.getString("count(idnumber)");

        }
        dashboardmemberslabel.setText(membersnumber);



    }




}