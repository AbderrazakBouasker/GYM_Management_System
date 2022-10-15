package dao;

import com.example.mini_projet_java.bdconnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Creatuserdao {
    Statement statement=null;

    public Creatuserdao() throws SQLException {
        Connection connection= bdconnection.connecter();
        if(connection!=null){
            statement= connection.createStatement();

        }
    }

    public void add(String varname,String varpass,String varq1,String varq2) throws SQLException {
        String query="INSERT INTO `miniprojdb`.`logininfo` (`id`, `username`, `password`, `qmovie`, `qmusic`) VALUES (null, \'"+varname+"\', \'"+varpass+"\', \'"+varq1+"\', \'"+varq2+"\')";
        statement.execute(query);


    }




}
