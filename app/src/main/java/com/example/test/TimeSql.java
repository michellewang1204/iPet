package com.example.test;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class TimeSql {

    // 資料庫定義
    String mysql_ip = "140.127.220.78";
    int mysql_port = 3306; // Port 預設為 3306
    String db_name = "project";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    String db_user = "Den";
    String db_password = "ipet2022";

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public ArrayList<int[]> getAlarm(String type,int petid) {
        ArrayList<int[]> alarm = new ArrayList<>();
        String sql = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            if(Objects.equals(type, "food")){
                sql = "SELECT * FROM feedtime_remind WHERE pid = '"+petid+"'";
            }else if(Objects.equals(type, "poop")){
                sql = "SELECT * FROM exctime_remind WHERE pid = '"+petid+"'";
            }else if(Objects.equals(type, "medicine")){
                sql = "SELECT * FROM medtime_remind WHERE pid = '"+petid+"'";
            }
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                int hour = rs.getInt("hour");
                int minute = rs.getInt("minute");
                int checked = rs.getInt("tocheck");
                int[] time = {hour,minute,checked};
                alarm.add(time);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alarm;
    }
    public ArrayList<String> getDate(String type,int petid) {
        ArrayList<String> alarm = new ArrayList<>();
        String sql = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            if(Objects.equals(type, "deworm")){
                sql = "SELECT * FROM dewtime_remind WHERE pid = '"+petid+"'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    Date date = rs.getDate("dtime");
                    String cat = rs.getString("category");
                    alarm.add(date.toString());
                    alarm.add(cat);
                }
                st.close();
            }else if(Objects.equals(type, "vaccine")){
                sql = "SELECT * FROM vactime_remind WHERE pid = '"+petid+"'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next())
                {
                    Date date = rs.getDate("mtime");
                    String cat = rs.getString("category");
                    alarm.add(date.toString());
                    alarm.add(cat);
                }
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alarm;
    }

    public int getPid(String userid){
        int pid = 0;
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM pet WHERE email = '"+userid+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                pid = rs.getInt("pid");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pid;
    }
    public String getPassword(String userid){
        String password = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT * FROM person WHERE email = '"+userid+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                password = rs.getString("password");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }
    public void setAlarm(String type,int pid,int hour,int minute){
        String h = String.valueOf(hour);
        String m = String.valueOf(minute);
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            if(type=="food"){
                sql = "INSERT INTO feedtime_remind (pid, ftime, tocheck, hour, minute)" +
                        " VALUES("+pid+",'"+h+":"+m+":00',"+0+","+h+","+m+")";
            }else if(type=="medicine"){
                sql = "INSERT INTO medtime_remind (pid, mtime, tocheck, hour, minute)" +
                        " VALUES("+pid+",'"+h+":"+m+":00',"+0+","+h+","+m+")";
            }else if(type=="poop"){
                sql = "INSERT INTO exctime_remind (pid, etime, tocheck, hour, minute)" +
                        " VALUES("+pid+",'"+h+":"+m+":00',"+0+","+h+","+m+")";
            }
            System.out.println(sql);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setDate(String date,int pid,String cat,String type){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            if(type=="vaccine"){
                sql = "INSERT INTO vactime_remind (pid, category, mtime, tocheck)"+
                "VALUES ("+pid+",'"+cat+"','"+date+"','t')";
            }else if(type=="deworm"){
                sql = "INSERT INTO dewtime_remind (pid, category, dtime, tocheck)"+
                        "VALUES ("+pid+",'"+cat+"','"+date+"','t')";
            }
            System.out.println(sql);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteDate(String date,int pid,String type){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            if(type=="vaccine"){
                sql = "DELETE FROM vactime_remind WHERE vactime_remind.pid = "+pid+" AND vactime_remind.mtime = '"+date+"'";
            }else if(type=="deworm"){
                sql = "DELETE FROM dewtime_remind WHERE dewtime_remind.pid = "+pid+" AND dewtime_remind.dtime = '"+date+"'";
            }
            System.out.println(sql);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    INSERT INTO `vactime_remind` (`pid`, `category`, `mtime`, `tocheck`) VALUES ('4', '蚤不到', '2022-11-02', 't');
    public void updateAlarm(String type,int pid,int ori_hour,int ori_minute,int hour,int minute,int check){
        String h = String.valueOf(hour);
        String m = String.valueOf(minute);
        String oh = String.valueOf(ori_hour);
        String om = String.valueOf(ori_minute);
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            if(type=="food"){
                sql = "UPDATE feedtime_remind SET tocheck='"+check+"',ftime='"+h+":"+m+":00',hour='"+h+"',minute='"+m+"'" +
                        "WHERE pid='"+pid+"' and hour = '"+oh+"' and minute = '"+om+"'";
            }else if(type=="medicine"){
                sql = "UPDATE medtime_remind SET tocheck='"+check+"',mtime='"+h+":"+m+":00',hour='"+h+"',minute='"+m+"'" +
                        "WHERE pid='"+pid+"' and hour = '"+oh+"' and minute = '"+om+"'";
            }else if(type=="poop"){
                sql = "UPDATE exctime_remind SET tocheck='"+check+"',etime='"+h+":"+m+":00',hour='"+h+"',minute='"+m+"'" +
                        "WHERE pid='"+pid+"' and hour = '"+oh+"' and minute = '"+om+"'";
            }
            System.out.println(sql);

            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAlarm(String type,int pid,String time){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            if(type=="food"){
                sql = "DELETE FROM feedtime_remind WHERE feedtime_remind.pid = "+pid+" AND feedtime_remind.ftime = '"+time+"'";
            }else if(type=="medicine"){
                sql = "DELETE FROM medtime_remind WHERE medtime_remind.pid = "+pid+" AND medtime_remind.mtime = '"+time+"'";
            }else if(type=="poop"){
                sql = "DELETE FROM exctime_remind WHERE exctime_remind.pid = "+pid+" AND exctime_remind.etime = '"+time+"'";
            }
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAccount(String email,String password){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            sql = "INSERT INTO person(email,password) VALUES ('"+email+"','"+password+"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changeAccount(String password,String userid){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            sql = "UPDATE person SET password='"+password+"' WHERE email='"+userid+"'";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changePetName(String petname,int pid){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            sql = "UPDATE pet SET petname='"+petname+"' WHERE pid="+pid;
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String[] getResult(String type){
        String[] result = {"","","","",""};
        String personality = "";
        String no1 = "";
        String no2 = "";
        String no3 = "";
        String ca = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT m.Personality,m.No1,m.No2,m.No3,m.Category FROM MBTI_analysis m WHERE MBTI = '"+type+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                personality = rs.getString("Personality");
                no1 = rs.getString("No1");
                no2 = rs.getString("No2");
                no3 = rs.getString("No3");
                ca = rs.getString("Category");
                result[0] = personality;
                result[1] = no1;result[2] = no2;result[3] = no3;result[4]=ca;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String[] getInfo(int pid){
        String[] result = {"","","",""};
        String name = "";
        String gender = "";
        String bdate = "";
        String uri = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT m.petname,m.sex,m.birthday,m.uri FROM pet m WHERE pid = '"+pid+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                name = rs.getString("petname");
                gender = rs.getString("sex");
                bdate = rs.getString("birthday");
                uri = rs.getString("uri");
                result[0] = name;
                result[1] = gender;result[2] = bdate;result[3] = uri;
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean checkUsername(String username){
        boolean check=false;
        int cou;
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            sql = "SELECT count(*) as cou  FROM person P WHERE P.email='"+username+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                System.out.println(rs.getInt("cou"));
                cou = rs.getInt("cou");
                if(cou==0){check = true;}else{check = false;}
            }
//            if(rs.next()){
//                check=true;
//            }else{
//                check=false;
//            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
    public int getMaxPid(){
        int  pid = -1;
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "SELECT max(p.pid) as age FROM pet p";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                pid = rs.getInt("age");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pid+1;
    }
    public void addPetInfo(String email,String petname,String birthday,String variety,int pid,String gender,String uri){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "";
            sql = "INSERT INTO pet(email,petname,birthday,variety,pid,sex,uri) VALUES ('"+email+"'," +
                    "'"+petname+"','"+birthday+"','"+variety+"','"+pid+"','"+gender+"','"+uri+"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setUri(int pid,String uri){
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            String sql = "UPDATE pet SET uri = '"+uri+"' WHERE pet.pid = "+pid;
            System.out.println(sql);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}