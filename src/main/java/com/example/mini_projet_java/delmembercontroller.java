package com.example.mini_projet_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class delmembercontroller {
    @FXML
    private Button delvernobut;

    @FXML
    private Button delveryesbut;


    public void delmemno(ActionEvent event){
        Stage stage = (Stage) delvernobut.getScene().getWindow();
        stage.close();
    }
    /*public void delmemyes(ActionEvent event){
        mainscreencontroller mainscreencontroller1=new mainscreencontroller();
        mainscreencontroller1.
        for(listtableimp tb:listtableview)
    }*/


}
    /*String locid=tb.getListid();
        try {
                mainscreendao.deletemem(locid);
                } catch (SQLException e) {
                throw new RuntimeException(e);
                }
                listtableview.getItems().remove(tb);*/