package com.example.mini_projet_java;

import javafx.scene.control.CheckBox;

import java.sql.Array;

public class listtableimp {

    private String listid;
    private String listidnumber;
    private String listname;
    private String listlastname;
    private String listcompanyname;
    private String listpricereduction;
    private String liststartdate;
    private String listenddate;
    private CheckBox listselect;


    public listtableimp(String listid,String listidnumber, String listname, String listlastname, String listcompanyname, String listpricereduction, String liststartdate, String listenddate) {
        this.listid = listid;
        this.listidnumber = listidnumber;
        this.listname = listname;
        this.listlastname = listlastname;
        this.listcompanyname = listcompanyname;
        this.listpricereduction = listpricereduction;
        this.liststartdate = liststartdate;
        this.listenddate = listenddate;
        this.listselect = new CheckBox();
    }


    public String getListidnumber() {
        return listidnumber;
    }

    public String getListname() {
        return listname;
    }

    public String getListlastname() {
        return listlastname;
    }

    public String getListcompanyname() {
        return listcompanyname;
    }

    public String getListpricereduction() {
        return listpricereduction;
    }

    public String getListstartdate() {
        return liststartdate;
    }

    public String getListenddate() {
        return listenddate;
    }

    public CheckBox getListselect() {return listselect;}

    public String getListid() {return listid;}
}
