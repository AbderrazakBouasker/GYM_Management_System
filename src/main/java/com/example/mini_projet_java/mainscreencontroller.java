package com.example.mini_projet_java;

import dao.Mainscreendao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainscreencontroller implements Initializable {

    @FXML
    private TableView<dashtableimp> dashboardexpirationtable;

    @FXML
    private TableColumn<dashtableimp, String> dashboardlistdays;

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

    @FXML
    private TableView<listtableimp> listtableview;

    @FXML
    private TableColumn<listtableimp, String> listtableviewcompany;

    @FXML
    private TableColumn<listtableimp, String> listtableviewenddate;

    @FXML
    private TableColumn<listtableimp, String > listtableviewidnumber;

    @FXML
    private TableColumn<listtableimp, String> listtableviewlasname;

    @FXML
    private TableColumn<listtableimp, String> listtableviewname;

    @FXML
    private TableColumn<listtableimp, String> listtableviewreduction;

    @FXML
    private TableColumn<listtableimp, ?> listtableviewselect;

    @FXML
    private TableColumn<listtableimp, String> listtableviewstartdate;


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
    private Button test;
    public void calctimedif(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select count(id) from members");
        int lenlist = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (resultSet.next()){
            lenlist= Integer.parseInt(resultSet.getString("count(id)"));
        }

        for (int i=1;i<lenlist+1;i++){

            resultSet=statement.executeQuery("select startdate,enddate from members where id="+i);

            while (resultSet.next()) {
                dbmstartdate=resultSet.getString("startdate");
                dbmenddate=resultSet.getString("enddate");

            }


            try {
                // parse method is used to parse
                // the text from a string to
                // produce the date
                Date d1 = sdf.parse(dbmstartdate);
                Date d2 = sdf.parse(dbmenddate);
                // Calucalte time difference
                // in milliseconds
                long difference_In_Time = d2.getTime() - d1.getTime();

                // Calucalte time difference in days
                long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
                System.out.print("Difference " + "between two dates is: ");

                System.out.println(difference_In_Days + " days");
                ///if differnceindays<= 3 obser.add or fill all info in list then insert into tableview
            }
            catch (ParseException e){
                e.printStackTrace();
            }


        }






    }


    public void refreshdashboard(ActionEvent event){
        addmemberpane.setVisible(false);
        memberslistpanel.setVisible(false);
        dashboardpanel.setVisible(true);

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement =connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from members");
            while (resultSet.next()) {
                //variables date&i counter ,calcule,if
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

    public void insert(ActionEvent event) throws SQLException {

        Mainscreendao mainscreendao= new Mainscreendao();
        LocalDate startdate=startdateinput.getValue();
        LocalDate enddate=enddateinput.getValue();
        String varcompany=companyinput.getText();
        String varpayment=reductioninput.getText();
        String varname=nameinput.getText();
        String varlastname=lastnameinput.getText();
        String varidnumber=idnumberinput.getText();

        if (nameinput.getText().isEmpty() || lastnameinput.getText().isEmpty() || idnumberinput.getText().isEmpty() || startdate==null || enddate==null){
            outlabel.setText("Fill all necessary fields");
        } else{
            String varstartdate=startdate.toString();
            String varenddate=enddate.toString();
            /*if(companyinput.getText().isEmpty()){
                varcompany=null;
            }*/
            if(reductioninput.getText().isEmpty()){
                varpayment=null;
            }
            mainscreendao.insertnew(varidnumber,varname,varlastname,varcompany,varpayment,varstartdate,varenddate);
            outlabel.setText("");
        }

    }

    public void switchadd(ActionEvent event){
        dashboardpanel.setVisible(false);
        memberslistpanel.setVisible(false);
        addmemberpane.setVisible(true);

    }

    //members list func

    public void switchlist(ActionEvent event) throws SQLException {
        dashboardpanel.setVisible(false);
        addmemberpane.setVisible(false);
        memberslistpanel.setVisible(true);
        Mainscreendao mainscreendao=new Mainscreendao();
        ObservableList<listtableimp> observ =mainscreendao.getinfo();
        listtableviewidnumber.setCellValueFactory(new PropertyValueFactory<>("listidnumber"));
        listtableviewname.setCellValueFactory(new PropertyValueFactory<>("listname"));
        listtableviewlasname.setCellValueFactory(new PropertyValueFactory<>("listlastname"));
        listtableviewcompany.setCellValueFactory(new PropertyValueFactory<>("listcompanyname"));
        listtableviewreduction.setCellValueFactory(new PropertyValueFactory<>("listpricereduction"));
        listtableviewstartdate.setCellValueFactory(new PropertyValueFactory<>("liststartdate"));
        listtableviewenddate.setCellValueFactory(new PropertyValueFactory<>("listenddate"));
        listtableview.setItems(observ);
    }







}