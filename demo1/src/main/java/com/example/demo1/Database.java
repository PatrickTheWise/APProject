package com.example.demo1;

import java.sql.*;

public class Database {

    public static Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exchange", "root", null);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean loginCheck(String username, String pass) throws SQLException {
        Statement stmn = connection().createStatement();
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()){
            if (username.equals(rs.getString(2)) && pass.equals(rs.getString(3))){
                return true;
            }
        }
        return false;
    }

    public static void signedUp(String username, String pass, String firstname, String lastname, String email, String phone) throws SQLException {
        Statement stmn = connection().createStatement();
        boolean isUnique = true;
        ResultSet rs = stmn.executeQuery("select * from users");
        while (rs.next()){
            if (username.equals(rs.getString(2)) || email.equals(rs.getString(5)) || phone.equals(rs.getString(4))){
                isUnique = false;
            }
        }
        if (isUnique){
            int count = 0;
            rs = stmn.executeQuery("select walletID from wallet");
            while (rs.next()){
                count = rs.getInt(1);
            }
            String sql = ("insert into users (walletID, username, pass, phone, mail, firstname, lastname)" + "values(?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStmt = connection().prepareStatement(sql);
            preparedStmt.setInt (1, ++count);
            preparedStmt.setString (2, username);
            preparedStmt.setString (3, pass);
            preparedStmt.setString (4, phone);
            preparedStmt.setString (5, email);
            preparedStmt.setString (6, firstname);
            preparedStmt.setString (7, lastname);
            preparedStmt.execute();
        }
    }





}
