package com.example.mini_projet_java;

import dao.Mainscreendao;
import javafx.application.Platform;
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

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;


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
    @FXML
    private TextField costinp;
    @FXML
    private TextField expdaysinp;
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
    private TableColumn<listtableimp, String> listtableviewselect;

    @FXML
    private TableColumn<listtableimp, String> listtableviewstartdate;

    private Parent root;
    Mainscreendao mainscreendao=new Mainscreendao();
    Scenechange scenechange=new Scenechange();
    public mainscreencontroller() throws SQLException {
    }

    @Override

    public void initialize(URL ur, ResourceBundle resourceBundle) {
        try {
            dashboardrevenuelabel.setText(mainscreendao.avgincome()+"Dt");
            dashboardmemberslabel.setText(mainscreendao.getmemcount());
            ObservableList<dashtableimp> obser = null;
            obser = mainscreendao.dashtablefill();
            dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
            dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));
            dashboardexpirationtable.setItems(obser);
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load((getClass()).getResource("login-screen.fxml"));
        scenechange.changesceneto(root,event);
    }

    public void refreshdashboard(ActionEvent event) throws SQLException, ParseException {
        addmemberpane.setVisible(false);
        memberslistpanel.setVisible(false);
        dashboardpanel.setVisible(true);
        dashboardrevenuelabel.setText(mainscreendao.avgincome()+"Dt");
        dashboardmemberslabel.setText(mainscreendao.getmemcount());
        ObservableList<dashtableimp> obser =mainscreendao.dashtablefill();
        dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));
        dashboardexpirationtable.setItems(obser);


    }
    //addmember func

    public void insert(ActionEvent event) throws SQLException {
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
        ObservableList<listtableimp> observ =mainscreendao.getinfo();
        listtableviewidnumber.setCellValueFactory(new PropertyValueFactory<>("listidnumber"));
        listtableviewname.setCellValueFactory(new PropertyValueFactory<>("listname"));
        listtableviewlasname.setCellValueFactory(new PropertyValueFactory<>("listlastname"));
        listtableviewcompany.setCellValueFactory(new PropertyValueFactory<>("listcompanyname"));
        listtableviewreduction.setCellValueFactory(new PropertyValueFactory<>("listpricereduction"));
        listtableviewstartdate.setCellValueFactory(new PropertyValueFactory<>("liststartdate"));
        listtableviewenddate.setCellValueFactory(new PropertyValueFactory<>("listenddate"));
        listtableviewselect.setCellValueFactory(new PropertyValueFactory<>("listselect"));
        listtableview.setItems(observ);
    }


    public void delete(ActionEvent event){
        for (listtableimp tb : listtableview.getItems()){
            if(tb.getListselect().isSelected()){
                Platform.runLater(()->{

                    listtableview.getItems().remove(tb);
                });
            }
        }
    }

}