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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainscreencontroller implements Initializable {

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
    @FXML
    private Pane addmemberpane;
    //addmember dec

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
    //member list dec

    @FXML
    private Pane memberslistpanel;


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

    public void initialize(URL ur, ResourceBundle resourceBundle) {
        try{
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from members");
            while (resultSet.next()) {
                obser.add(new dashtableimp(resultSet.getString("name"),resultSet.getString("lastname")
                        ,resultSet.getString("enddate")));
            }
            resultSet=statement.executeQuery("select count(idnumber) from members");

            while (resultSet.next()) {
                membersnumber=resultSet.getString("count(idnumber)");

            }
            dashboardmemberslabel.setText(membersnumber);
        }
        catch (SQLException e){
            Logger.getLogger(mainscreencontroller.class.getName()).log(Level.SEVERE,null,e);
        }


        dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));



        dashboardexpirationtable.setItems(obser);

    }

    /*public void dbgetmembersinfo() throws SQLException {
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
    }*/


    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("login-screen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshdashboard(ActionEvent event){
        addmemberpane.setVisible(false);
        dashboardpanel.setVisible(true);

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement =connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from members");
            while (resultSet.next()) {
                obser.add(new dashtableimp(resultSet.getString("name"),resultSet.getString("lastname")
                        ,resultSet.getString("enddate")));
            }
            resultSet=statement.executeQuery("select count(idnumber) from members");

            while (resultSet.next()) {
                membersnumber=resultSet.getString("count(idnumber)");

            }
            dashboardmemberslabel.setText(membersnumber);
        }
        catch (SQLException e){
            Logger.getLogger(mainscreencontroller.class.getName()).log(Level.SEVERE,null,e);
        }


        dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));



        dashboardexpirationtable.setItems(obser);




    }
    //addmember func

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

    public void switchadd(ActionEvent event){
        dashboardpanel.setVisible(false);
        addmemberpane.setVisible(true);

    }

    //members list func

}