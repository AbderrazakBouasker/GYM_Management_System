package dao;

import com.example.mini_projet_java.bdconnection;
import com.example.mini_projet_java.listtableimp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Mainscreendao {
    Statement statement=null;
    String idnumber;
    String name;
    String lastname;
    String company;
    String reduction;
    String startdate;
    String enddate;




    public Mainscreendao() throws SQLException {
        Connection connection= bdconnection.connecter();
        if(connection!=null){
            statement= connection.createStatement();

        }

    }


    public void insertnew(String varidnumber,String varname,String varlastname,String varcompany,String varpayment,String varstartdate,String varenddate) throws SQLException {
        String query="insert into members values (id,"+varidnumber+",\""+varname+"\",\""+varlastname+"\",\""+varcompany+"\","+varpayment+",\""+varstartdate+"\",\""+varenddate+"\")";
        statement.execute(query);


    }

    public ObservableList<listtableimp> getinfo() throws SQLException {
        ResultSet resultSet=statement.executeQuery("select * from members");
        ObservableList<listtableimp> observ = FXCollections.observableArrayList();
        while (resultSet.next()){

            /*idnumber=resultSet.getString("idnumber");
            name=resultSet.getString("name");
            lastname=resultSet.getString("lastname");
            company=resultSet.getString("companyname");
            reduction=resultSet.getString("paymentreduction");
            startdate=resultSet.getString("startdate");
            enddate=resultSet.getString("enddate");*/
            observ.add(new listtableimp(resultSet.getString("idnumber"), resultSet.getString("name"),resultSet.getString("lastname")
                            , resultSet.getString("companyname"), resultSet.getString("paymentreduction"), resultSet.getString("startdate"), resultSet.getString("enddate")));

        }
        return observ;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getReduction() {
        return reduction;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }
}
