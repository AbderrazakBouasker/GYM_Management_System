package dao;

import com.example.mini_projet_java.bdconnection;
import javafx.scene.control.TextField;

import java.sql.*;

public class Logindao {
    Statement statement=null;
    String dbuserpassword;
    String dbusername;
    String dbusermovie;
    String dbusermusic;
    String checkres;




    //establish connection
    public Logindao() throws SQLException {
        Connection connection= bdconnection.connecter();
        if(connection!=null){
            statement= connection.createStatement();

        }
    }

    public void dbgetuserinfo(TextField inputusername) throws SQLException {
        String query="select * from logininfo where username=\""+inputusername.getText().toString()+ "\"";
        ResultSet resultSet=statement.executeQuery(query);

        while (resultSet.next()) {
            dbuserpassword=resultSet.getString("password");
            dbusername=resultSet.getString("username");
            dbusermovie=resultSet.getString("qmovie");
            dbusermusic=resultSet.getString("qmusic");

        }
    }

    public boolean dbcheckexist() throws SQLException {
        String query="select count(*) from logininfo";
        ResultSet resultSet=statement.executeQuery(query);

        while (resultSet.next()) {
            checkres=resultSet.getString("count(*)");
        }
        if(checkres.equals("0")){
            return true;
        }
        return false;
    }

    public String getDbuserpassword() {
        return dbuserpassword;
    }

    public String getDbusername() {
        return dbusername;
    }

    public String getDbusermovie() {
        return dbusermovie;
    }

    public String getDbusermusic() {
        return dbusermusic;
    }
}
