package dao;

import com.example.mini_projet_java.bdconnection;
import com.example.mini_projet_java.dashtableimp;
import com.example.mini_projet_java.listtableimp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class Mainscreendao {
    Statement statement=null;





    public Mainscreendao() throws SQLException {
        Connection connection= bdconnection.connecter();
        if(connection!=null){
            statement= connection.createStatement();

        }

    }

    public ObservableList<dashtableimp> dashtablefill() throws SQLException, ParseException {
        ObservableList<dashtableimp> obser = FXCollections.observableArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String query="select * from members";
        ResultSet resultSet= statement.executeQuery(query);
        String name;
        String lastname;
        String startdate;
        String enddate;
        while (resultSet.next()){
            name=resultSet.getString("name");
            lastname=resultSet.getString("lastname");
            startdate=resultSet.getString("startdate");
            enddate=resultSet.getString("enddate");
            Date d1 = sdf.parse(startdate);
            Date d2 = sdf.parse(enddate);
            long difference_In_Time = d2.getTime() - d1.getTime();
            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
            if (difference_In_Days<=3){
                obser.add(new dashtableimp(name,lastname,enddate));
            }
        }
        return obser;
    }


    public String getmemcount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select count(id) from members");

        String membersnumber = null;
        while (resultSet.next()) {
            membersnumber = resultSet.getString("count(id)");

        }
        return membersnumber;
    }

    public String avgincome() throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int income=0;
        int comparemonth=0;
        int compareday=0;
        int compareyear=0;
        LocalDate datelocalDate=LocalDate.now();
        LocalDate dateenddate;
        String enddate;
        String query="select * from members";
        ResultSet resultSet= statement.executeQuery(query);
        while (resultSet.next()){
            enddate=resultSet.getString("enddate");
            dateenddate= LocalDate.parse(enddate);
            compareyear=Year.of(dateenddate.getYear()).compareTo(Year.of(datelocalDate.getYear()));
            comparemonth=dateenddate.getMonth().compareTo(datelocalDate.getMonth());


            if(dateenddate.getDayOfMonth()>=datelocalDate.getDayOfMonth()){
                compareday=1;
            }
            else {
                compareday=-1;
            }


            if(compareyear>0){
                income+=60;
            } else if (compareyear==0) {
                if (comparemonth>0){
                    income+=60;
                }
                else if (comparemonth==0){
                    if (compareday>0){
                        income+=60;
                    }
                }
            }

        }


        return String.valueOf(income);
    }


    public void insertnew(String varidnumber,String varname,String varlastname,String varcompany,String varpayment,String varstartdate,String varenddate) throws SQLException {
        String query="insert into members values (id,"+varidnumber+",\""+varname+"\",\""+varlastname+"\",\""+varcompany+"\","+varpayment+",\""+varstartdate+"\",\""+varenddate+"\")";
        statement.execute(query);


    }

    public ObservableList<listtableimp> getinfo() throws SQLException {
        ResultSet resultSet=statement.executeQuery("select * from members");
        ObservableList<listtableimp> observ = FXCollections.observableArrayList();
        while (resultSet.next()){
            observ.add(new listtableimp(resultSet.getString("id"),resultSet.getString("idnumber"), resultSet.getString("name"),resultSet.getString("lastname")
                            , resultSet.getString("companyname"), resultSet.getString("paymentreduction"), resultSet.getString("startdate"), resultSet.getString("enddate")));

        }
        return observ;
    }

    public void deletemem(String id) throws SQLException {
        String query="delete from members where id="+ id ;
        statement.execute(query);
    }

    public void editmeminfo(String id,String idnumber,String name,String lastname,String company,String paymentred,String startdate,String enddate) throws SQLException {
        String query="UPDATE members SET id ="+id+ ", idnumber = "+idnumber+", name ='"+name+"' , lastname ='"+lastname+"', companyname ='"+company+"', paymentreduction =" +paymentred+", startdate ='"+startdate+"', enddate = '"+enddate+"' WHERE id =" +id;
        statement.execute(query);
    }

}
