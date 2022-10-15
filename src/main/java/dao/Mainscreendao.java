package dao;

import com.example.mini_projet_java.bdconnection;
import com.example.mini_projet_java.listtableimp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Mainscreendao {
    Statement statement=null;




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
            observ.add(new listtableimp(resultSet.getString("idnumber"), resultSet.getString("name"),resultSet.getString("lastname")
                            , resultSet.getString("companyname"), resultSet.getString("paymentreduction"), resultSet.getString("startdate"), resultSet.getString("enddate")));

        }
        return observ;
    }

}
