package com.example.mini_projet_java;

import java.sql.Array;

public class listtableimp {

    private String listidnumber;
    private String listname;
    private String listlastname;
    private String listcompanyname;
    private String listpricereduction;
    private String liststartdate;
    private String listenddate;


    public listtableimp(String listidnumber, String listname, String listlastname, String listcompanyname, String listpricereduction, String liststartdate, String listenddate) {
        this.listidnumber = listidnumber;
        this.listname = listname;
        this.listlastname = listlastname;
        this.listcompanyname = listcompanyname;
        this.listpricereduction = listpricereduction;
        this.liststartdate = liststartdate;
        this.listenddate = listenddate;
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
}
