package dao;

import com.example.mini_projet_java.bdconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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



}
