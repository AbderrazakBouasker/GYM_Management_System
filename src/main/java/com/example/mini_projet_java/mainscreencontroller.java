package com.example.mini_projet_java;

import dao.Mainscreendao;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
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
    private Label editlabel;

    @FXML
    private TextField searchfield;
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
    @FXML
    private Button changepassscene;

    @FXML
    private Button music;
    @FXML
    private Button addmusic;
    @FXML
    private Button editmusic;
    @FXML
    private Button deletemusic;
    @FXML
    private Button playmusic;
    @FXML
    private TextField editmusicid;

    @FXML
    private TextField editmusiclink;

    @FXML
    private TextField editmusictitle;
    @FXML
    private TableColumn<Musictable, String> musicidcol;
    @FXML
    private TableColumn<Musictable, String> musictitlecol;

    @FXML
    private TableColumn<Musictable, String> musiclinkcol;

    @FXML
    private TableView<Musictable> musictable;

    @FXML
    private Pane musicpanel;

    boolean selection=false;
    //delete verification


    private Parent root;
    Mainscreendao mainscreendao=new Mainscreendao();
    Scenechange scenechange=new Scenechange();
    public mainscreencontroller() throws SQLException {
    }

    @Override

    public void initialize(URL ur, ResourceBundle resourceBundle) {
        try {
            dashboardrevenuelabel.setText(mainscreendao.avgincome(costinp.getText())+"Dt");
            dashboardmemberslabel.setText(mainscreendao.getmemcount());
            ObservableList<dashtableimp> obser = null;
            obser = mainscreendao.dashtablefill(Integer.parseInt(expdaysinp.getText()));
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
        musicpanel.setVisible(false);
        dashboardpanel.setVisible(true);
        dashboardrevenuelabel.setText(mainscreendao.avgincome(costinp.getText())+"Dt");
        dashboardmemberslabel.setText(mainscreendao.getmemcount());
        ObservableList<dashtableimp> obser =mainscreendao.dashtablefill(Integer.parseInt(expdaysinp.getText()));
        dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));
        dashboardexpirationtable.setItems(obser);


    }
    @FXML
    void updatedashbydays(KeyEvent event) throws SQLException, ParseException {
        if (!expdaysinp.getText().matches("[0-9]*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input Error");
            alert.setContentText("Dates field should be numeric!");
            alert.showAndWait();
            expdaysinp.setText("");
        }
        else if (expdaysinp.getText().isEmpty()){
            ObservableList<dashtableimp> obser =mainscreendao.dashtablefill(0);
            dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
            dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));
            dashboardexpirationtable.setItems(obser);
        }else {
            ObservableList<dashtableimp> obser = mainscreendao.dashtablefill(Integer.parseInt(expdaysinp.getText()));
            dashboardlistname.setCellValueFactory(new PropertyValueFactory<>("name"));
            dashboardlistlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            dashboardlistdays.setCellValueFactory(new PropertyValueFactory<>("remaindays"));
            dashboardexpirationtable.setItems(obser);
        }
    }

    @FXML
    void revenuebyinp(KeyEvent event) throws SQLException, ParseException {
        if (!costinp.getText().matches("[0-9]*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input Error");
            alert.setContentText("Cost field input should be numeric!");
            alert.showAndWait();
            costinp.setText("");
        }
        else if (costinp.getText().isEmpty()){
            dashboardrevenuelabel.setText(mainscreendao.avgincome("60")+"Dt");
        }else {
            dashboardrevenuelabel.setText(mainscreendao.avgincome(costinp.getText()) + "Dt");
        }
    }

    //addmember func

    public void insert(ActionEvent event) throws SQLException, ParseException {
        LocalDate startdate=startdateinput.getValue();
        LocalDate enddate=enddateinput.getValue();
        String varcompany=companyinput.getText();
        String varpayment=reductioninput.getText();
        String varname=nameinput.getText();
        String varlastname=lastnameinput.getText();
        String varidnumber=idnumberinput.getText();

        if (nameinput.getText().isEmpty() || lastnameinput.getText().isEmpty() || idnumberinput.getText().isEmpty() || startdate==null || enddate==null) {
            outlabel.setText("Fill all necessary fields");
        }
        else if (!idnumberinput.getText().matches("[0-9]*")|| !reductioninput.getText().matches("[0-9]*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input Error");
            alert.setContentText("Id field and Reduction field input should be numeric!");
            outlabel.setText("");
            alert.showAndWait();
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdf.parse(String.valueOf(startdate));
            Date d2 = sdf.parse(String.valueOf(enddate));
            long diff = d2.getTime() - d1.getTime();
            if (diff <= 0) {
                outlabel.setText("Start date should be earlier than end date");
            } else {
                String varstartdate = startdate.toString();
                String varenddate = enddate.toString();
                if (reductioninput.getText().isEmpty()) {
                    varpayment = null;
                }
                mainscreendao.insertnew(varidnumber, varname, varlastname, varcompany, varpayment, varstartdate, varenddate);
                outlabel.setText("Added successfully");
                companyinput.setText("");
                reductioninput.setText("");
                nameinput.setText("");
                lastnameinput.setText("");
                idnumberinput.setText("");
            }
        }
    }

    public void switchadd(ActionEvent event){
        dashboardpanel.setVisible(false);
        memberslistpanel.setVisible(false);
        musicpanel.setVisible(false);
        addmemberpane.setVisible(true);

    }

    //members list func

    public void switchlist(ActionEvent event) throws SQLException {
        dashboardpanel.setVisible(false);
        addmemberpane.setVisible(false);
        musicpanel.setVisible(false);
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
    @FXML
    void search(KeyEvent event) throws SQLException {
        if (searchfield.getText().isEmpty()){
            switchlist(new ActionEvent());
        }else {
            ObservableList<listtableimp> observ = mainscreendao.getinfobyname(searchfield.getText());
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
    }

    public boolean vercheckbox(){
        boolean locbool=true;
        for(listtableimp tb : listtableview.getItems()){
            if(tb.getListselect().isSelected()){
                locbool=true;
            }
            else {
                locbool=false;
            }
        }
        return locbool;
    }

    public void delete(ActionEvent event){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete prompt");
                alert.setHeaderText("Delete confirmation");
                alert.setContentText("Are you sure you want to delete?");
                if(vercheckbox()){
                Optional<ButtonType> result=alert.showAndWait();
                for (listtableimp tb : listtableview.getItems()){
                    if(tb.getListselect().isSelected()){
                        if(result.isPresent()&&result.get()==ButtonType.OK){
                            Platform.runLater(()->{
                                String locid=tb.getListid();
                                try {
                                    mainscreendao.deletemem(locid);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                listtableview.getItems().remove(tb);
                            });
                        }else {
                            alert.close();
                        }
                    }
                }}


    }

    public void editmem(ActionEvent event){
        for (listtableimp tb : listtableview.getItems()){
            if(tb.getListselect().isSelected()){
                Platform.runLater(()->{
                    try {
                        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("editmember.fxml"));
                        root=fxmlLoader.load();
                        editmembercontroller editmembercontroller1=fxmlLoader.getController();
                        editmembercontroller1.setall(tb.getListid(),tb.getListidnumber(),tb.getListname(),tb.getListlastname(),tb.getListcompanyname(),tb.getListpricereduction(),tb.getListstartdate(),tb.getListenddate());
                        Stage popups = new Stage();
                        Scene scene = new Scene(root);
                        popups.setScene(scene);
                        popups.show();
                    }
                    catch (IOException e){
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
    public void switchpassscene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("changepass-screen.fxml"));
        root=fxmlLoader.load();
        //Changepasscontroller changepasscontroller=fxmlLoader.getController();
        Stage popups = new Stage();
        Scene scene = new Scene(root);
        popups.centerOnScreen();
        popups.setResizable(false);
        popups.setTitle("Password change");
        popups.setScene(scene);
        popups.show();
    }
    public void switchmusic(ActionEvent event){
        dashboardpanel.setVisible(false);
        memberslistpanel.setVisible(false);
        addmemberpane.setVisible(false);
        musicpanel.setVisible(true);
    }
    public void editmusic(ActionEvent event){

    }



    /*public void delete(ActionEvent event) throws SQLException{
        for (listtableimp tb : listtableview.getItems()){
            if(tb.getListselect().isSelected()){
                Platform.runLater(()->{
                    try {
                        Stage popups = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verifdel.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        popups.setScene(scene);
                        popups.show();
                    }
                    catch (IOException e){
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }*/

}
