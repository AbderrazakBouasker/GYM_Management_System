package com.example.mini_projet_java;

import dao.Logindao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.sql.SQLException;

public class Changepasscontroller {
    @FXML
    private PasswordField newpassid;

    @FXML
    private PasswordField oldpassid;

    @FXML
    private Button submitbtn;
    
    Logindao logindao=new Logindao();

    public Changepasscontroller() throws SQLException {
    }

    @FXML
    void changepassfnc(ActionEvent event) throws SQLException {
        if (oldpassid.getText().isEmpty()||newpassid.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        }
        else if (oldpassid.getText().equals(newpassid.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input Error");
            alert.setContentText("Old password and new password should be different");
            alert.showAndWait();
            newpassid.setText("");
        } else if (logindao.changepass(oldpassid.getText(),newpassid.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inform Dialog");
            alert.setHeaderText("Success");
            alert.setContentText("Password was changed successfully");
            alert.showAndWait();
            submitbtn.setDisable(true);
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input Error");
            alert.setContentText("Wrong old password given");
            alert.showAndWait();
            oldpassid.setText("");
            newpassid.setText("");
        }
    }
}
